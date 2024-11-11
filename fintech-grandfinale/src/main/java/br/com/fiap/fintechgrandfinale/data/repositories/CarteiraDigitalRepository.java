package br.com.fiap.fintechgrandfinale.data.repositories;

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

        CarteiraDigital carteiraDigital = null;
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                var stm = cnn.prepareStatement("select * from carteira_digital where CODIGO = ?");
                stm.setInt(1, id);
                var result = stm.executeQuery();

                if (result.next()) {
                    var dataHoraAtualizacao = result.getTimestamp("DATA_HORA_ATUALIZACAO");
                    var instituicaoFinanceira = result.getInt("CODIGO_INSTITUICAO_FINANCEIRA");

                    carteiraDigital = new CarteiraDigital();
                    carteiraDigital.setCodigo(result.getInt("CODIGO"));
                    carteiraDigital.setCodigoParticipanteControleFinanceiro(result.getInt("CODIGO_PARTICIPANTE"));
                    carteiraDigital.setInstituicaoFinanceira(EnumUtils.fromValue(InstituicaoFinanceira.class, instituicaoFinanceira));
                    carteiraDigital.setAtivo(result.getBoolean("ATIVO"));
                    carteiraDigital.setDataHoraCadastro(result.getTimestamp("DATA_HORA_CADASTRO").toLocalDateTime());

                    if(dataHoraAtualizacao != null) {
                        carteiraDigital.setDataHoraAtualizacao(dataHoraAtualizacao.toLocalDateTime());
                    }
                }
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

        return carteiraDigital;
    }

    @Override
    public int insert(CarteiraDigital carteiraDigital) {

        try {
            var cnn = this.getConnection();
            if(cnn == null)
                return 0;

            var stm = cnn.prepareStatement("insert into carteira_digital (codigo_participante, codigo_instituicao_financeira, ativo, data_hora_cadastro)\n" +
                    "values (?, ?, ?, ?)");

            stm.setInt(1, carteiraDigital.getCodigoParticipanteControleFinanceiro());
            stm.setInt(2, carteiraDigital.getInstituicaoFinanceira().getValue());
            stm.setBoolean(3, carteiraDigital.getAtivo());
            stm.setTimestamp(4, java.sql.Timestamp.valueOf(carteiraDigital.getDataHoraCadastro()));

            stm.executeUpdate();
            return getSequenceCurrentValue("SQ_CARTEIRA_DIGITAL");
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void update(CarteiraDigital carteiraDigital) {
        var cnn = this.getConnection();
        if (cnn != null) {
            try {
                var stm = cnn.prepareStatement("update carteira_digital\n" +
                        "set codigo_participante = ?,\n" +
                        "codigo_instituicao_financeira = ?,\n" +
                        "ativo = ?,\n" +
                        "data_hora_atualizacao = ?\n" +
                        "where codigo = ?");

                stm.setInt(1, carteiraDigital.getCodigoParticipanteControleFinanceiro());
                stm.setInt(2, carteiraDigital.getInstituicaoFinanceira().getValue());
                stm.setBoolean(3, carteiraDigital.getAtivo());
                stm.setTimestamp(4, java.sql.Timestamp.valueOf(carteiraDigital.getDataHoraAtualizacao()));
                stm.setInt(5, carteiraDigital.getCodigo());

                stm.executeUpdate();
            } catch(RuntimeException | SQLException e){
                throw new RuntimeException(e);
            } finally{
                this.closeConnection();
            }
        }
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
