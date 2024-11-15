package br.com.fiap.fintechgrandfinale.domain.entities;

import br.com.fiap.fintechgrandfinale.domain.entities.forma.pagamento.Conta;
import org.json.JSONObject;

import javax.naming.ldap.Control;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Participante extends BaseModel {
    private int codigoUsuario;
    private int codigoControleFinanceiro;
    private Boolean ativo;
    private Boolean proprietario;
    private List<Renda> rendaLista;
    private List<Despesa> despesaLista;
    private List<CarteiraDigital> carteiraDigitalLista;
    private Usuario usuario;
    private ControleFinanceiro controleFinanceiro;

    public int getCodigoUsuario() {
        return codigoUsuario;
    }
    public int getCodigoControleFinanceiro() {
        return codigoControleFinanceiro;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public Boolean getProprietario() {
        return proprietario;
    }
    public List<Renda> getRendaLista() {
        return rendaLista;
    }
    public List<Despesa> getDespesaLista() {
        return despesaLista;
    }
    public List<CarteiraDigital> getCarteiraDigitalLista() {
        return carteiraDigitalLista;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public ControleFinanceiro getControleFinanceiro() {
        return controleFinanceiro;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    public void setCodigoControleFinanceiro(int codigoControleFinanceiro) {
        this.codigoControleFinanceiro = codigoControleFinanceiro;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public void setProprietario(Boolean proprietario) {
        this.proprietario = proprietario;
    }
    public void setRendaLista(List<Renda> rendaLista) {
        this.rendaLista = rendaLista;
    }
    public void setDespesaLista(List<Despesa> despesaLista) {
        this.despesaLista = despesaLista;
    }
    public void setCarteiraDigitalLista(List<CarteiraDigital> carteiraDigitalLista) {
        this.carteiraDigitalLista = carteiraDigitalLista;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setControleFinanceiro(ControleFinanceiro controleFinanceiro) {
        this.controleFinanceiro = controleFinanceiro;
    }

    public Participante(){
        super();
        this.rendaLista = new ArrayList<>();
        this.despesaLista = new ArrayList<>();
        this.carteiraDigitalLista = new ArrayList<>();
    }
    public Participante(int codigoUsuario, int codigoControleFinanceiro, Boolean ativo, Boolean proprietario) {
        this();
        this.codigoUsuario = codigoUsuario;
        this.codigoControleFinanceiro = codigoControleFinanceiro;
        this.ativo = ativo;
        this.proprietario = proprietario;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public Participante(int codigo, int codigoUsuario, int codigoControleFinanceiro, Boolean ativo, Boolean proprietario) {
        super(codigo);
        this.codigoUsuario = codigoUsuario;
        this.codigoControleFinanceiro = codigoControleFinanceiro;
        this.ativo = ativo;
        this.proprietario = proprietario;
    }

    public void atualizarParticipante(int codigoUsuario, int codigoControleFinanceiro, Boolean ativo, Boolean proprietario) {
        this.codigoUsuario = codigoUsuario;
        this.codigoControleFinanceiro = codigoControleFinanceiro;
        this.ativo = ativo;
        this.proprietario = proprietario;
        this.setDataHoraAtualizacao(LocalDateTime.now());
    }

    public String toJson() {
        JSONObject usuarioJson = new JSONObject();
        usuarioJson.put("codigo", this.getUsuario().getCodigo());
        usuarioJson.put("nome", this.getUsuario().getNome());
        usuarioJson.put("email", this.getUsuario().getEmail());

        JSONObject controleFinanceiroJson = new JSONObject();
        controleFinanceiroJson.put("codigo", this.getControleFinanceiro().getCodigo());
        controleFinanceiroJson.put("descricao", this.getControleFinanceiro().getDescricao());

        JSONObject json = new JSONObject();
        json.put("codigo", this.getCodigo());
        json.put("codigo_controle_financeiro", this.getCodigoControleFinanceiro());
        json.put("codigo_usuario", this.getCodigoUsuario());
        json.put("ativo", this.getAtivo());
        json.put("proprietario", this.getProprietario());
        json.put("usuario", usuarioJson);
        json.put("controle_financeiro", controleFinanceiroJson);

        return json.toString();
    }
}
