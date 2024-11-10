package br.com.fiap.fintechgrandfinale.application.interfaces.services;

import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;

import java.sql.SQLException;
import java.util.List;

public interface IControleFinanceiroService {
    ControleFinanceiro getControleFinanceiro(int codigoUsuario, int codigo);
    List<ControleFinanceiro> getAllControleFinanceiro(int codigoUsuario);
    List<Participante> getAllParticipante(int codigoUsuario, int codigoControleFinanceiro, String nome, String email);
    List<CarteiraDigital> getAllCarteiraDigital(int codigoUsuario);
    void insertControleFinanceiro(int codigoUsuario, ControleFinanceiro form) throws SQLException;
    void insertParticipante(Participante form) throws SQLException;
    void updateControleFinanceiro(ControleFinanceiro form);
    void updateParticipante(Participante form);
}
