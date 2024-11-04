package br.com.fiap.fintechgrandfinale.application.models;

public class FormParticipanteModel {
    private int codigo;
    private int codigoUsuario;
    private int codigoControleFinanceiro;
    private Boolean ativo;
    private Boolean proprietario;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoControleFinanceiro() {
        return codigoControleFinanceiro;
    }

    public void setCodigoControleFinanceiro(int codigoControleFinanceiro) {
        this.codigoControleFinanceiro = codigoControleFinanceiro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getProprietario() {
        return proprietario;
    }

    public void setProprietario(Boolean proprietario) {
        this.proprietario = proprietario;
    }
}
