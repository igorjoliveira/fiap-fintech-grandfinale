package br.com.fiap.fintechgrandfinale.application.models;

import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.entities.Participante;

import java.util.List;

public class ParticipanteModel {
    private List<ControleFinanceiro> grupos;
    private List<Participante> participantes;

    public List<ControleFinanceiro> getGrupos() {
        return grupos;
    }
    public List<Participante> getParticipantes() {
        return participantes;
    }
    public void setGrupos(List<ControleFinanceiro> grupos) {
        this.grupos = grupos;
    }
    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }
}
