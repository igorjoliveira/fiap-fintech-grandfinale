package br.com.fiap.fintechgrandfinale.application.interfaces.services;

import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;

import java.sql.SQLException;
import java.util.List;

public interface IControleFinanceiroService {
    ControleFinanceiro getControleFinanceiro(int codigoUsuario, int codigo);
    List<ControleFinanceiro> getAllControleFinanceiro(int codigoUsuario, String descricao);
    List<Participante> getAllParticipante(int codigoUsuario, int codigoControleFinanceiro, String nome, String email);
    List<CarteiraDigital> getAllCarteiraDigital(int codigoUsuario, int codigoControleFinanceiro, int codigoInstituicaoFinanceira, String nome, String email);
    List<InstituicaoFinanceira> getAllInstituicoes();
    void insertControleFinanceiro(int codigoUsuario, ControleFinanceiro form) throws SQLException;
    void insertParticipante(int codigoUsuario, Participante form) throws SQLException;
    void insertCarteira(int codigoUsuario, CarteiraDigital form) throws SQLException;
    void updateControleFinanceiro(int codigoUsuario, ControleFinanceiro form);
    void updateParticipante(int codigoUsuario, Participante form);
    void updateCarteira(int codigoUsuario, CarteiraDigital form) throws SQLException;
}
