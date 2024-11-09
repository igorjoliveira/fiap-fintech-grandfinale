package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.enums.Autenticador;
import br.com.fiap.fintechgrandfinale.domain.enums.Sexo;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IUsuarioRepository;
import br.com.fiap.fintechgrandfinale.domain.utils.EnumUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository extends BaseRepository<Usuario> implements IUsuarioRepository {
    @Override
    public List<Usuario> getAll() {
        var usuarios = new ArrayList<Usuario>();
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                var stm = cnn.prepareStatement("select * from usuario");
                var result = stm.executeQuery();

                while (result.next()) {
                    usuarios.add(readerUsuario(result));
                }
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

        return usuarios;
    }

    @Override
    public Usuario getById(int id) {
        Usuario usuario = null;
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                var stm = cnn.prepareStatement("select * from usuario where CODIGO = ?");
                stm.setInt(1, id);
                var result = stm.executeQuery();

                if (result.next()) {
                    return readerUsuario(result);
                }
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

        return usuario;
    }

    @Override
    public int insert(Usuario usuario){
        try {
            var cnn = this.getConnection();
            if(cnn == null)
                return 0;

            var stm = cnn.prepareStatement("INSERT INTO USUARIO (NOME, SOBRENOME, SEXO_CODIGO, DATA_NASCIMENTO, EMAIL, ATIVO, AUTENTICADOR, SENHA, DATA_HORA_CADASTRO) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            createParams(usuario, stm);
            stm.setBoolean(6, usuario.getAtivo());
            stm.setInt(7, usuario.getAutenticador().getValue());
            stm.setString(8, usuario.getSenha());
            stm.setTimestamp(9, java.sql.Timestamp.valueOf(usuario.getDataHoraCadastro()));

            stm.executeUpdate();
            return getSequenceCurrentValue("sq_usuario");
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void update(Usuario usuario) {

        var cnn = this.getConnection();
        if (cnn != null) {
            try {
                var stm = cnn.prepareStatement("UPDATE USUARIO SET " +
                        "NOME=?, " +
                        "SOBRENOME=?, " +
                        "SEXO_CODIGO=?, " +
                        "DATA_NASCIMENTO=?, " +
                        "EMAIL=?, " +
                        "DATA_HORA_ATUALIZACAO=? " +
                        "WHERE CODIGO=?");

                createParams(usuario, stm);
                stm.setTimestamp(6, java.sql.Timestamp.valueOf(usuario.getDataHoraAtualizacao()));
                stm.setInt(7, usuario.getCodigo());
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
        try {
            var cnn = this.getConnection();
            if(cnn != null) {
                var stm = cnn.prepareStatement("DELETE FROM USUARIO WHERE CODIGO = ?");
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

    }

    private Usuario readerUsuario(ResultSet result) throws SQLException {

        var sexo = EnumUtils.fromValue(Sexo.class, result.getInt("SEXO_CODIGO"));
        var autenticador = EnumUtils.fromValue(Autenticador.class, result.getInt("AUTENTICADOR"));
        var dataHoraAtualizacao = result.getTimestamp("DATA_HORA_ATUALIZACAO");

        return new Usuario(result.getInt("CODIGO"),
                result.getString("NOME"),
                result.getString("SOBRENOME"),
                sexo,
                result.getDate("DATA_NASCIMENTO").toLocalDate(),
                result.getString("EMAIL"),
                result.getBoolean("ATIVO"),
                autenticador,
                result.getString("SENHA"),
                result.getTimestamp("DATA_HORA_CADASTRO").toLocalDateTime(),
                dataHoraAtualizacao != null ? dataHoraAtualizacao.toLocalDateTime() : null);
    }

    private void createParams(Usuario usuario, PreparedStatement stm) throws SQLException {
        stm.setString(1, usuario.getNome());
        stm.setString(2, usuario.getSobreNome());
        stm.setInt(3, usuario.getSexo().getValue());
        stm.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
        stm.setString(5, usuario.getEmail());
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = null;
        try{
            var cnn = this.getConnection();
            if(cnn != null) {

                var stm = cnn.prepareStatement("select * from usuario where EMAIL = ?");
                stm.setString(1, email);
                var result = stm.executeQuery();

                if (result.next()) {
                    return readerUsuario(result);
                }
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeConnection();
        }

        return usuario;
    }
}
