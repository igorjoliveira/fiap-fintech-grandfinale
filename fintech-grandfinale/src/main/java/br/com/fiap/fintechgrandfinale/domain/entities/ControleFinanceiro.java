package br.com.fiap.fintechgrandfinale.domain.entities;

import br.com.fiap.fintechgrandfinale.domain.exceptions.EntradaDadoInvalidaException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControleFinanceiro extends BaseModel {

    private String descricao;
    private Boolean ativo;
    private List<Participante> participanteLista;

    public ControleFinanceiro(){
        super();
        this.participanteLista = new ArrayList<>();
    }
    public ControleFinanceiro(String descricao) {
        this();
        this.descricao = descricao;
        this.ativo = true;
        this.setDataHoraCadastro(LocalDateTime.now());
    }
    public ControleFinanceiro(int codigo, String descricao, Boolean ativo, LocalDateTime dataHoraCadastro, LocalDateTime dataHoraAtualizacao) {
        super(codigo, dataHoraCadastro, dataHoraAtualizacao);
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Boolean getAtivo() { return ativo; }
    public String getDescricao() {
        return descricao;
    }
    public List<Participante> getParticipanteLista() { return participanteLista; }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public void setParticipanteLista(List<Participante> participanteLista) {
        this.participanteLista = participanteLista;
    }

    public void atualizarControleFinanceiro(String descricao, boolean ativo) {
        this.descricao = descricao;
        this.ativo = ativo;
        this.setDataHoraAtualizacao(LocalDateTime.now());
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("descricao", this.getDescricao());
        json.put("codigo", this.getCodigo());
        json.put("ativo", this.getAtivo());

        return json.toString();
    }
}
