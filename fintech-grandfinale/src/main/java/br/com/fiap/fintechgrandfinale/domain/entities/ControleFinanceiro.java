package br.com.fiap.fintechgrandfinale.domain.entities;

import br.com.fiap.fintechgrandfinale.domain.exceptions.EntradaDadoInvalidaException;

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

    public ControleFinanceiro adicionarParticipante(int codigoUsuario, Boolean proprietario) throws EntradaDadoInvalidaException {
        for (var participante : this.participanteLista){
            if(participante.getCodigoUsuario() == codigoUsuario){
                throw new EntradaDadoInvalidaException("Usuario já faz parte do controle financeiro!");
            }
        }

        participanteLista.add(new Participante(codigoUsuario, this.getCodigo(), true, proprietario));
        return this;
    }
    public Participante obterParticipante(int codigoUsuario) throws EntradaDadoInvalidaException {
        for (var item : this.participanteLista){
            if(item.getCodigoUsuario() == codigoUsuario)
                return item;
        }

        throw new EntradaDadoInvalidaException(String.format("Não foi encontrado nenhum participante para o código %d", codigoUsuario));
    }
}
