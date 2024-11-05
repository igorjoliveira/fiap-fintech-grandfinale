package br.com.fiap.fintechgrandfinale.application.interfaces.services;

import br.com.fiap.fintechgrandfinale.application.models.*;
import java.util.List;

public interface IUsuarioService {
    void register(FormUsuarioModel formUsuario);
    GetUsuarioModel login(FormLoginModel formLogin);
    GetControleFinanceiroModel createControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro);
    GetControleFinanceiroModel alterControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro);
    List<GetControleFinanceiroModel> getAllControleFinanceiro();
    GetControleFinanceiroModel getControleFinanceiro();
    void addParticipante(FormParticipanteModel formParticipante);
    void alterParticipante(FormParticipanteModel formParticipante);
    List<GetParticipanteModel> getAllParticipante(FormControleFinanceiroModel formControleFinanceiro);
}