package br.com.fiap.fintechgrandfinale.application.services;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IUsuarioService;
import br.com.fiap.fintechgrandfinale.application.models.*;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IUsuarioRepository;
import br.com.fiap.fintechgrandfinale.infra.data.repositories.UsuarioRepository;

import java.util.List;

public class UsuarioService implements IUsuarioService {
    private IUsuarioRepository usuarioRepository;

    UsuarioService(){
        this.usuarioRepository = new UsuarioRepository();
    }

    @Override
    public void register(FormUsuarioModel formUsuario) {

    }

    @Override
    public GetUsuarioModel login(FormLoginModel formLogin) {
        return null;
    }

    @Override
    public void logout() {

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
