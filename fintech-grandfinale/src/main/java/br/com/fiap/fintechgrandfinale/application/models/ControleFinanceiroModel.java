package br.com.fiap.fintechgrandfinale.application.models;

import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;

import java.util.List;

public class ControleFinanceiroModel {
    private List<ControleFinanceiro> grupos;
    private ParticipanteModel participanteModel;
    private CarteiraDigitalModel carteiraDigitalModel;

    public List<ControleFinanceiro> getGrupos() {
        return grupos;
    }
    public ParticipanteModel getParticipanteModel() {
        return participanteModel;
    }
    public CarteiraDigitalModel getCarteiraDigitalModel() {
        return carteiraDigitalModel;
    }

    public void setGrupos(List<ControleFinanceiro> grupos) {
        this.grupos = grupos;
    }
    public void setParticipanteModel(ParticipanteModel participanteModel) {
        this.participanteModel = participanteModel;
    }
    public void setCarteiraDigitalModel(CarteiraDigitalModel carteiraDigitalModel) {
        this.carteiraDigitalModel = carteiraDigitalModel;
    }
}
