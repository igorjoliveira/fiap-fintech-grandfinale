package br.com.fiap.fintechgrandfinale.application.services;

import br.com.fiap.fintechgrandfinale.application.interfaces.services.IControleFinanceiroService;
import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.ICarteiraDigitalRepository;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IControleFinanceiroRepository;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IParticipanteRepository;
import br.com.fiap.fintechgrandfinale.domain.utils.EnumUtils;
import br.com.fiap.fintechgrandfinale.infra.data.repositories.CarteiraDigitalRepository;
import br.com.fiap.fintechgrandfinale.infra.data.repositories.ControleFinanceiroRepository;
import br.com.fiap.fintechgrandfinale.infra.data.repositories.ParticipanteRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControleFinanceiroService implements IControleFinanceiroService {
    private IParticipanteRepository participanteRepository;
    private ICarteiraDigitalRepository carteiraDigitalRepository;
    private IControleFinanceiroRepository controleFinanceiroRepository;

    public ControleFinanceiroService() {
        this.participanteRepository = new ParticipanteRepository();
        this.carteiraDigitalRepository = new CarteiraDigitalRepository();
        this.controleFinanceiroRepository = new ControleFinanceiroRepository();
    }

    @Override
    public ControleFinanceiro getControleFinanceiro(int codigoUsuario, int codigo) {
        return this.controleFinanceiroRepository.getById(codigo);
    }

    @Override
    public List<ControleFinanceiro> getAllControleFinanceiro(int codigoUsuario, String descricao) {
        var lista = this.controleFinanceiroRepository.getAll(codigoUsuario, descricao);
        return lista != null ? lista : List.of();
    }

    @Override
    public List<Participante> getAllParticipante(int codigoUsuario, int codigoControleFinanceiro, String nome, String email) {
        var lista = this.participanteRepository.getAll(codigoUsuario, codigoControleFinanceiro, nome, email);
        return lista != null ? lista : List.of();
    }

    @Override
    public List<CarteiraDigital> getAllCarteiraDigital(int codigoUsuario, int codigoControleFinanceiro, int codigoInstituicaoFinanceira, String nome, String email) {
        var lista = this.carteiraDigitalRepository.getAll(codigoUsuario, codigoControleFinanceiro, codigoInstituicaoFinanceira, nome,email);
        return lista != null ? lista : List.of();
    }

    @Override
    public List<InstituicaoFinanceira> getAllInstituicoes() {
        return EnumUtils.getAllValues(InstituicaoFinanceira.class);
    }

    @Override
    public void insertControleFinanceiro(int codigoUsuario, ControleFinanceiro form) throws SQLException {
        var codigoControleFinanceiro = this.controleFinanceiroRepository.insert(form);
        var participante = new Participante(codigoUsuario, codigoControleFinanceiro, true, true);
        this.insertParticipante(participante);
    }

    @Override
    public void insertParticipante(Participante form) throws SQLException {
        var codigoParticipante = this.participanteRepository.insert(form);
    }

    @Override
    public void updateControleFinanceiro(ControleFinanceiro form) {

    }

    @Override
    public void updateParticipante(Participante form) {

    }
}
