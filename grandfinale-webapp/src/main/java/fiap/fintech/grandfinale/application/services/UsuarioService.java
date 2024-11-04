package fiap.fintech.grandfinale.application.services;

import fiap.fintech.grandfinale.application.interfaces.services.IUsuarioService;
import fiap.fintech.grandfinale.application.models.*;
import fiap.fintech.grandfinale.domain.interfaces.repositories.IUsuarioRepository;
import fiap.fintech.grandfinale.infra.data.repositories.UsuarioRepository;

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
