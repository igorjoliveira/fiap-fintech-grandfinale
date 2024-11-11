package br.com.fiap.fintechgrandfinale.domain.entities;

import br.com.fiap.fintechgrandfinale.domain.entities.forma.pagamento.*;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;
import org.json.JSONObject;

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

    public void atualizarCarteiraDigital(int codigoParticipanteControleFinanceiro, InstituicaoFinanceira instituicaoFinanceira, Boolean ativo) {
        this.setCodigoParticipanteControleFinanceiro(codigoParticipanteControleFinanceiro);
        this.setInstituicaoFinanceira(instituicaoFinanceira);
        this.setAtivo(ativo);
        this.setDataHoraAtualizacao(LocalDateTime.now());
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

    public String toJson() {
        var usuarioJson = new JSONObject();
        usuarioJson.put("codigo", this.getParticipante().getUsuario().getCodigo());
        usuarioJson.put("nome", this.getParticipante().getUsuario().getNome());
        usuarioJson.put("email", this.getParticipante().getUsuario().getEmail());

        var controleFinanceiroJson = new JSONObject();
        controleFinanceiroJson.put("codigo", this.getParticipante().getControleFinanceiro().getCodigo());
        controleFinanceiroJson.put("descricao", this.getParticipante().getControleFinanceiro().getDescricao());

        var participanteJson = new JSONObject();
        participanteJson.put("codigo", this.getParticipante().getCodigo());
        participanteJson.put("usuario", usuarioJson);
        participanteJson.put("controle_financeiro", controleFinanceiroJson);

        var json = new JSONObject();
        json.put("codigo", this.getCodigo());
        json.put("codigo_instituicao_financeira", this.getInstituicaoFinanceira().getValue());
        json.put("ativo", this.getAtivo());
        json.put("participante", participanteJson);

        return json.toString();
    }
}

