package br.com.fiap.fintechgrandfinale.domain.entities;

import br.com.fiap.fintechgrandfinale.domain.entities.forma.pagamento.*;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarteiraDigital extends BaseModel {
    private int codigoParticipanteControleFinanceiro;
    private InstituicaoFinanceira instituicaoFinanceira;
    private Boolean ativo;
    private List<FormaPagamento> formaPagamentoLista;
    private Participante participante;

    public int getCodigoParticipanteControleFinanceiro() {
        return codigoParticipanteControleFinanceiro;
    }
    public InstituicaoFinanceira getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public List<FormaPagamento> getFormaPagamentoLista() {
        return formaPagamentoLista;
    }
    public Participante getParticipante() {
        return participante;
    }

    public void setCodigoParticipanteControleFinanceiro(int codigoParticipanteControleFinanceiro) {
        this.codigoParticipanteControleFinanceiro = codigoParticipanteControleFinanceiro;
    }
    public void setInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public void setFormaPagamentoLista(List<FormaPagamento> formaPagamentoLista) {
        this.formaPagamentoLista = formaPagamentoLista;
    }
    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public CarteiraDigital(){
        super();
        this.formaPagamentoLista = new ArrayList<>();
    }
    public CarteiraDigital(int codigoParticipanteControleFinanceiro, InstituicaoFinanceira instituicaoFinanceira) {
        this();
        this.codigoParticipanteControleFinanceiro = codigoParticipanteControleFinanceiro;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.setDataHoraCadastro(LocalDateTime.now());
        this.ativo = true;
    }
    public CarteiraDigital(int codigo, LocalDateTime dataHoraCadastro, LocalDateTime dataHoraAtualizacao, int codigoParticipanteControleFinanceiro, InstituicaoFinanceira instituicaoFinanceira, Boolean ativo) {
        super(codigo, dataHoraCadastro, dataHoraAtualizacao);
        this.codigoParticipanteControleFinanceiro = codigoParticipanteControleFinanceiro;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.ativo = ativo;
    }

    public FormaPagamento adicionarCartaoCredito(String numero, String nome, String dataVencimento, String codigoSeguranca){
        return this.adicionarFormaPagamento(new CartaoCredito(this.getCodigo(), numero, nome, dataVencimento, codigoSeguranca));
    }
    public FormaPagamento adicionarCartaoDebito(String numero, String nome, String dataVencimento){
        return this.adicionarFormaPagamento(new CartaoDebito(this.getCodigo(), numero, nome, dataVencimento));
    }
    public FormaPagamento adicionarContaCorrente(String agencia, String numero, char digito){
        return this.adicionarFormaPagamento(new ContaCorrente(this.getCodigo(), agencia, numero, digito));
    }
    public FormaPagamento adicionarContaPoupanca(String agencia, String numero, char digito){
        return this.adicionarFormaPagamento(new ContaPoupanca(this.getCodigo(), agencia, numero, digito));
    }
    public FormaPagamento adicionarCheque(int numeroTalao, int quantidadeFolha){
        return this.adicionarFormaPagamento(new Cheque(this.getCodigo(), numeroTalao, quantidadeFolha));
    }
    private FormaPagamento adicionarFormaPagamento(FormaPagamento formaPagamento){
        this.formaPagamentoLista.add(formaPagamento);
        return formaPagamento;
    }
}

