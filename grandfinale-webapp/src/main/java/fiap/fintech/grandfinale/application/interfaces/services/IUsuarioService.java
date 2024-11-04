package fiap.fintech.grandfinale.application.interfaces.services;

import fiap.fintech.grandfinale.application.models.*;
import java.util.List;

public interface IUsuarioService {
    public void register(FormUsuarioModel formUsuario);
    public GetUsuarioModel login(FormLoginModel formLogin);
    public void logout();
    public GetControleFinanceiroModel createControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro);
    public GetControleFinanceiroModel alterControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro);
    public List<GetControleFinanceiroModel> getAllControleFinanceiro();
    public GetControleFinanceiroModel getControleFinanceiro();
    public void addParticipante(FormParticipanteModel formParticipante);
    public void alterParticipante(FormParticipanteModel formParticipante);
    public List<GetParticipanteModel> getAllParticipante(FormControleFinanceiroModel formControleFinanceiro);
}
