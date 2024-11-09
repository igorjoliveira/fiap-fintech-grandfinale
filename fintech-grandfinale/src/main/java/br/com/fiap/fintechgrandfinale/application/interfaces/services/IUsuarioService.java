package br.com.fiap.fintechgrandfinale.application.interfaces.services;

import br.com.fiap.fintechgrandfinale.application.models.*;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioService {
    void register(FormUsuarioModel formUsuario) throws SQLException;
    GetUsuarioModel login(FormLoginModel formLogin);
    GetControleFinanceiroModel createControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro);
    GetControleFinanceiroModel alterControleFinanceiro(FormControleFinanceiroModel formControleFinanceiro);
    List<GetControleFinanceiroModel> getAllControleFinanceiro();
    GetControleFinanceiroModel getControleFinanceiro();
    void addParticipante(FormParticipanteModel formParticipante);
    void alterParticipante(FormParticipanteModel formParticipante);
    List<GetParticipanteModel> getAllParticipante(FormControleFinanceiroModel formControleFinanceiro);
}