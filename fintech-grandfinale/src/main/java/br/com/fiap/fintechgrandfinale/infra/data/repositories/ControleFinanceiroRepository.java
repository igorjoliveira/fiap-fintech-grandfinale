package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IControleFinanceiroRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControleFinanceiroRepository extends BaseRepository<ControleFinanceiro> implements IControleFinanceiroRepository {

    @Override
    public List<ControleFinanceiro> getAll() {
        return List.of();
    }

    @Override
    public ControleFinanceiro getById(int id) {
        ControleFinanceiro controleFinanceiro = null;
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                var stm = cnn.prepareStatement("select * from controle_financeiro where CODIGO = ?");
                stm.setInt(1, id);
                var result = stm.executeQuery();

                if (result.next()) {
                    var dataHoraAtualizacao = result.getTimestamp("DATA_HORA_ATUALIZACAO");

                    controleFinanceiro = new ControleFinanceiro();
                    controleFinanceiro.setCodigo(result.getInt("CODIGO"));
                    controleFinanceiro.setDescricao(result.getString("DESCRICAO"));
                    controleFinanceiro.setAtivo(result.getBoolean("ATIVO"));
                    controleFinanceiro.setDataHoraCadastro(result.getTimestamp("DATA_HORA_CADASTRO").toLocalDateTime());
                    if(dataHoraAtualizacao != null) {
                        controleFinanceiro.setDataHoraAtualizacao(dataHoraAtualizacao.toLocalDateTime());
                    }
                }
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

        return controleFinanceiro;
    }

    @Override
    public int insert(ControleFinanceiro controleFinanceiro) {

        try {
            var cnn = this.getConnection();
            if(cnn == null)
                return 0;

            var stm = cnn.prepareStatement("INSERT INTO CONTROLE_FINANCEIRO (DESCRICAO, ATIVO, DATA_HORA_CADASTRO) "
                    + "VALUES (?, ?, ?)");

            stm.setString(1, controleFinanceiro.getDescricao());
            stm.setBoolean(2, controleFinanceiro.getAtivo());
            stm.setTimestamp(3, java.sql.Timestamp.valueOf(controleFinanceiro.getDataHoraCadastro()));

            stm.executeUpdate();
            return getSequenceCurrentValue("SQ_CONTROLE_FINANCEIRO");
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void update(ControleFinanceiro controleFinanceiro) {
        var cnn = this.getConnection();
        if (cnn != null) {
            try {
                var stm = cnn.prepareStatement("update controle_financeiro\n" +
                        "set descricao = ?,\n" +
                        "data_hora_atualizacao = ?\n" +
                        "where codigo = ? ");

                stm.setString(1, controleFinanceiro.getDescricao());
                stm.setTimestamp(2, java.sql.Timestamp.valueOf(controleFinanceiro.getDataHoraAtualizacao()));
                stm.setInt(3, controleFinanceiro.getCodigo());
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
    public List<ControleFinanceiro> getAll(int codigoUsuario, String descricao) {
        var list = new ArrayList<ControleFinanceiro>();
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                var query = "select " +
                        "a.codigo                as CONTROLEFINANCEIRO_CODIGO, " +
                        "a.descricao             as CONTROLEFINANCEIRO_DESCRICAO, " +
                        "a.ativo                 as CONTROLEFINANCEIRO_ATIVO, " +
                        "a.data_hora_cadastro    as CONTROLEFINANCEIRO_DATAHORA_CADASTRO, " +
                        "a.data_hora_atualizacao as CONTROLEFINANCEIRO_DATAHORA_ATUALIZACAO, " +
                        "b.codigo                as PARTICIPANTE_CODIGO, " +
                        "b.ativo                 as PARTICIPANTE_ATIVO, " +
                        "b.codigo_usuario        as USUARIO_CODIGO " +
                        "FROM controle_financeiro a " +
                        "inner join participante b on a.codigo = b.codigo_controle_financeiro " +
                        "where b.codigo_usuario = ? ";

                if (descricao != null && !descricao.trim().isEmpty()) {
                    query += "AND UPPER(a.descricao) LIKE UPPER(?) ";
                }

                query += "ORDER BY a.codigo";

                var stm = cnn.prepareStatement(query);
                stm.setInt(1, codigoUsuario);

                if (descricao != null && !descricao.trim().isEmpty()) {
                    stm.setString(2, "%" + descricao + "%");
                }

                var result = stm.executeQuery();
                while (result.next()) {
                    var dataHoraAtualizacao = result.getTimestamp("CONTROLEFINANCEIRO_DATAHORA_ATUALIZACAO");
                    var controleFinanceiro = new ControleFinanceiro(result.getInt("CONTROLEFINANCEIRO_CODIGO"),
                            result.getString("CONTROLEFINANCEIRO_DESCRICAO"),
                            result.getBoolean("CONTROLEFINANCEIRO_ATIVO"),
                            result.getTimestamp("CONTROLEFINANCEIRO_DATAHORA_CADASTRO").toLocalDateTime(),
                            dataHoraAtualizacao != null ? dataHoraAtualizacao.toLocalDateTime() : null);

                    list.add(controleFinanceiro);
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
