package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IParticipanteRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteRepository extends BaseRepository<Participante> implements IParticipanteRepository {
    @Override
    public List<Participante> getAll() {
        return List.of();
    }

    @Override
    public Participante getById(int id) {
        return null;
    }

    @Override
    public int insert(Participante participante) {
        try {
            var cnn = this.getConnection();
            if(cnn == null)
                return 0;

            var stm = cnn.prepareStatement("INSERT INTO PARTICIPANTE (CODIGO_USUARIO, CODIGO_CONTROLE_FINANCEIRO, ATIVO, PROPRIETARIO) "
                    + "VALUES (?, ?, ?, ?)");

            stm.setInt(1, participante.getCodigoUsuario());
            stm.setInt(2, participante.getCodigoControleFinanceiro());
            stm.setBoolean(3, participante.getAtivo());
            stm.setBoolean(4, participante.getProprietario());

            stm.executeUpdate();
            return getSequenceCurrentValue("SQ_PARTICIPANTE");
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void update(Participante participante) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Participante> getAll(int codigoUsuario, int codigoControleFinanceiro, String nome, String email) {

        var list = new ArrayList<Participante>();
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                StringBuilder query = new StringBuilder(
                        "select " +
                        "a.codigo                       as codigo_participante, " +
                        "a.codigo_usuario               as codigo_usuario, " +
                        "a.codigo_controle_financeiro   as codigo_controle_financeiro,"  +
                        "c.descricao                    as descricao_controle_financeiro, " +
                        "b.nome                         as nome_usuario, " +
                        "b.email                        as email_usuario, " +
                        "a.proprietario                 as proprietario_participante, " +
                        "a.ativo                        as ativo_participante " +
                        "from participante a " +
                        "inner join usuario b on a.codigo_usuario = b.codigo " +
                        "inner join controle_financeiro c on a.codigo_controle_financeiro = c.codigo " +
                        "where b.codigo = ? ");

                if (codigoControleFinanceiro > 0) {
                    query.append(" AND a.codigo_controle_financeiro = ?");
                }

                if (nome != null && !nome.isEmpty()) {
                    query.append(" AND UPPER(b.nome) LIKE UPPER(?)");
                }

                if (email != null && !email.isEmpty()) {
                    query.append(" AND UPPER(b.email) LIKE UPPER(?)");
                }

                var stm = cnn.prepareStatement(query.toString());
                stm.setInt(1, codigoUsuario);

                int paramIndex = 2;
                if (codigoControleFinanceiro > 0) {
                    stm.setInt(paramIndex++, codigoControleFinanceiro);
                }
                if (nome != null && !nome.isEmpty()) {
                    stm.setString(paramIndex++, "%" + nome + "%");
                }
                if (email != null && !email.isEmpty()) {
                    stm.setString(paramIndex++, "%" + email + "%");
                }

                var result = stm.executeQuery();
                while (result.next()) {

                    var participante = new Participante(result.getInt("codigo_participante"),
                            result.getInt("codigo_usuario"),
                            result.getInt("codigo_controle_financeiro"),
                            result.getBoolean("ativo_participante"),
                            result.getBoolean("proprietario_participante"));

                    var usuario = new Usuario();
                    usuario.setCodigo(participante.getCodigo());
                    usuario.setNome(result.getString("nome_usuario"));
                    usuario.setEmail(result.getString("email_usuario"));

                    var controleFinanceiro = new ControleFinanceiro();
                    controleFinanceiro.setCodigo(participante.getCodigoControleFinanceiro());
                    controleFinanceiro.setDescricao(result.getString("descricao_controle_financeiro"));

                    participante.setUsuario(usuario);
                    participante.setControleFinanceiro(controleFinanceiro);

                    list.add(participante);
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
