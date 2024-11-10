package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.ICarteiraDigitalRepository;
import br.com.fiap.fintechgrandfinale.domain.utils.EnumUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarteiraDigitalRepository extends BaseRepository<CarteiraDigital> implements ICarteiraDigitalRepository {
    @Override
    public List<CarteiraDigital> getAll() {
        return List.of();
    }

    @Override
    public CarteiraDigital getById(int id) {
        return null;
    }

    @Override
    public int insert(CarteiraDigital carteiraDigital) {
        return 0;
    }

    @Override
    public void update(CarteiraDigital carteiraDigital) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public List<CarteiraDigital> getAll(int codigoUsuario, int codigoControleFinanceiro, int codigoInstituicaoFinanceira, String nome, String email) {
        var list = new ArrayList<CarteiraDigital>();
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                StringBuilder query = new StringBuilder(
                        "select " +
                                "a.codigo                        as codigo_carteira_digital, " +
                                "a.ativo                         as ativo_carteira_digital, " +
                                "a.data_hora_cadastro            as data_hora_cadastro_carteira_digital, " +
                                "a.data_hora_atualizacao         as data_hora_atualizacao_carteira_digital, " +
                                "a.codigo_instituicao_financeira as codigo_instituicao_financeira, " +
                                "e.nome                          as nome_instituicao_financeira, " +
                                "b.codigo                        as codigo_participante, " +
                                "c.codigo                        as codigo_controle_financeiro, " +
                                "c.descricao                     as descricao_controle_financeiro, " +
                                "d.codigo                        as codigo_usuario, " +
                                "d.email                         as email_usuario, " +
                                "d.nome                          as nome_usuario " +
                                "from carteira_digital a " +
                                "inner join participante b on a.codigo_participante = b.codigo " +
                                "inner join controle_financeiro c on b.codigo_controle_financeiro = c.codigo " +
                                "inner join usuario d on b.codigo_usuario = d.codigo " +
                                "inner join instituicao_financeira e on a.codigo_instituicao_financeira = e.codigo " +
                                "where c.codigo in (select codigo_controle_financeiro from participante where codigo_usuario = ?)"
                );

                List<Object> parameters = new ArrayList<>();
                parameters.add(codigoUsuario);

                if (codigoControleFinanceiro > 0) {
                    query.append(" and c.codigo = ?");
                    parameters.add(codigoControleFinanceiro);
                }

                if (codigoInstituicaoFinanceira > 0) {
                    query.append(" and a.codigo_instituicao_financeira = ?");
                    parameters.add(codigoInstituicaoFinanceira);
                }

                if (nome != null && !nome.trim().isEmpty()) {
                    query.append(" and upper(d.nome) like upper(?)");
                    parameters.add("%" + nome.toUpperCase() + "%");
                }

                if (email != null && !email.trim().isEmpty()) {
                    query.append(" and upper(d.email) like upper(?)");
                    parameters.add("%" + email.toUpperCase() + "%");
                }

                var stm = cnn.prepareStatement(query.toString());
                for (int i = 0; i < parameters.size(); i++) {
                    stm.setObject(i + 1, parameters.get(i));
                }

                var result = stm.executeQuery();
                while (result.next()) {
                    var instituicaoFinanceira = EnumUtils.fromValue(InstituicaoFinanceira.class, result.getInt("codigo_instituicao_financeira"));
                    var dataHoraAtualizacao = result.getTimestamp("data_hora_atualizacao_carteira_digital");

                    var carteira = new CarteiraDigital(result.getInt("codigo_carteira_digital"),
                            result.getTimestamp("data_hora_cadastro_carteira_digital").toLocalDateTime(),
                            dataHoraAtualizacao != null ? dataHoraAtualizacao.toLocalDateTime() : null,
                            result.getInt("codigo_controle_financeiro"),
                            instituicaoFinanceira,
                            result.getBoolean("ativo_carteira_digital"));

                    var participante = new Participante();
                    participante.setCodigo(result.getInt("codigo_participante"));

                    var controleFinanceiro = new ControleFinanceiro();
                    controleFinanceiro.setCodigo(result.getInt("codigo_controle_financeiro"));
                    controleFinanceiro.setDescricao(result.getString("descricao_controle_financeiro"));

                    var usuario = new Usuario();
                    usuario.setCodigo(result.getInt("codigo_usuario"));
                    usuario.setNome(result.getString("nome_usuario"));
                    usuario.setEmail(result.getString("email_usuario"));

                    participante.setControleFinanceiro(controleFinanceiro);
                    participante.setUsuario(usuario);
                    carteira.setParticipante(participante);

                    list.add(carteira);
                }
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
        return list;
    }
}
