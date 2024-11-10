package br.com.fiap.fintechgrandfinale.application.services;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IUsuarioService;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IUsuarioRepository;
import br.com.fiap.fintechgrandfinale.infra.data.repositories.UsuarioRepository;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UsuarioService implements IUsuarioService {
    private IUsuarioRepository usuarioRepository;

    public UsuarioService(){
        this.usuarioRepository = new UsuarioRepository();
    }

    @Override
    public void register(Usuario form) throws SQLException {
        this.usuarioRepository.insert(form);
    }

    @Override
    public void update(int codigo, Usuario form) {
        var usuario = this.usuarioRepository.getById(codigo);

        usuario.setNome(form.getNome());
        usuario.setSobreNome(form.getSobreNome());
        usuario.setEmail(form.getEmail());
        usuario.setSexo(form.getSexo());
        usuario.setDataNascimento(form.getDataNascimento());
        usuario.setDataHoraAtualizacao(LocalDateTime.now());

        this.usuarioRepository.update(usuario);

    }

    @Override
    public Usuario login(String email, String senha) {
        var usuario = this.getUser(email);
        return usuario != null && usuario.validarCredencial(senha)
                ? usuario
                : null;
    }

    @Override
    public Usuario getUser(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario getUser(int codigo) {
        var usuario = this.usuarioRepository.getById(codigo);
        return usuario != null ? usuario : null;
    }
}
