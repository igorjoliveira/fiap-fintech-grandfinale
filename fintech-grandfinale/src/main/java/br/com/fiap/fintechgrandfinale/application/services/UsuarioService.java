package br.com.fiap.fintechgrandfinale.application.services;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IUsuarioService;
import br.com.fiap.fintechgrandfinale.application.models.*;
import br.com.fiap.fintechgrandfinale.domain.entities.Usuario;
import br.com.fiap.fintechgrandfinale.domain.enums.Autenticador;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IUsuarioRepository;
import br.com.fiap.fintechgrandfinale.infra.data.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService implements IUsuarioService {
    private IUsuarioRepository usuarioRepository;

    public UsuarioService(){
        this.usuarioRepository = new UsuarioRepository();
    }

    @Override
    public void register(FormUsuarioModel formUsuario) throws SQLException {

        var usuario = new Usuario(formUsuario.getNome(), formUsuario.getSobreNome(), formUsuario.getSexo(), formUsuario.getDataNascimento(), formUsuario.getEmail());
        usuario.definirAutenticador(Autenticador.Interno, formUsuario.getSenha());

        this.usuarioRepository.insert(usuario);
    }

    @Override
    public GetUsuarioModel login(FormLoginModel formLogin) {
        var usuario = this.usuarioRepository.findByEmail(formLogin.getEmail());
        if(usuario != null && usuario.getSenha().equals(formLogin.getSenha())){
            var model = new GetUsuarioModel();
            model.setCodigo(usuario.getCodigo());
            model.setNome(usuario.getNome());
            model.setSobreNome(usuario.getSobreNome());
            model.setEmail(usuario.getEmail());
            model.setSexo(usuario.getSexo());
            model.setAtivo(usuario.getAtivo());
            model.setAutenticador(usuario.getAutenticador());
            model.setDataNascimento(usuario.getDataNascimento());
            model.setDataHoraCadastro(usuario.getDataHoraCadastro());
            model.setDataHoraAtualizacao(usuario.getDataHoraAtualizacao());

            return model;
        }
        return null;
    }

    @Override
    public GetControleFinanceiroModel createControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro) {
        return null;
    }

    @Override
    public GetControleFinanceiroModel alterControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro) {
        return null;
    }

    @Override
    public List<GetControleFinanceiroModel> getAllControleFinanceiro() {
        return List.of();
    }

    @Override
    public GetControleFinanceiroModel getControleFinanceiro() {
        return null;
    }

    @Override
    public void addParticipante(FormParticipanteModel formParticipante) {

    }

    @Override
    public void alterParticipante(FormParticipanteModel formParticipante) {

    }

    @Override
    public List<GetParticipanteModel> getAllParticipante(FormControleFinanceiroModel formControleFinanceiro) {
        return List.of();
    }
}
