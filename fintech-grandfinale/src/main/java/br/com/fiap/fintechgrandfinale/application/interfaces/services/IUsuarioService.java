package br.com.fiap.fintechgrandfinale.application.interfaces.services;

import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import java.sql.SQLException;

public interface IUsuarioService {
    void register(Usuario form) throws SQLException;
    void update(int codigo, Usuario form);
    Usuario login(String username, String password);
    Usuario getUser(int codigo);
    Usuario getUser(String email);
}