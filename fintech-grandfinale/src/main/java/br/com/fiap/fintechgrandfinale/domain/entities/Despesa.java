package br.com.fiap.fintechgrandfinale.domain.entities;

import br.com.fiap.fintechgrandfinale.domain.enums.CategoriaDespesa;

import java.time.LocalDateTime;

public class Despesa extends BaseModel {

    private CategoriaDespesa categoriaDespesa;
    private int codigoFormaPagamento;
    private int codigoParticipante;
    private String descricao;
    private double valor;
    private Boolean parcelado;
    private int quantidadeParcela;
    private LocalDateTime dataHoraRealizada;

    public CategoriaDespesa getCategoriaDespesa() {
        return categoriaDespesa;
    }
    public int getCodigoFormaPagamento() {
        return codigoFormaPagamento;
    }
    public int getCodigoParticipante() {
        return codigoParticipante;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getValor() {
        return valor;
    }
    public Boolean getParcelado() {
        return parcelado;
    }
    public int getQuantidadeParcela() {
        return quantidadeParcela;
    }
    public LocalDateTime getDataHoraRealizada() {
        return dataHoraRealizada;
    }

    public Despesa(int codigoParticipante, String descricao, int codigoFormaPagamento, double valor, CategoriaDespesa categoriaDespesa, LocalDateTime dataHoraRealizada) {
        super();
        this.codigoParticipante = codigoParticipante;
        this.codigoFormaPagamento = codigoFormaPagamento;
        this.descricao = descricao;
        this.valor = valor;
        this.categoriaDespesa = categoriaDespesa;
        this.dataHoraRealizada = dataHoraRealizada;
        this.parcelado = false;
        this.setDataHoraCadastro(LocalDateTime.now());
    }
    public Despesa(int codigoParticipante, String descricao, int codigoFormaPagamento, double valor, CategoriaDespesa categoriaDespesa, LocalDateTime dataHoraRealizada, int quantidadeParcela) {
        this(codigoParticipante, descricao, codigoFormaPagamento, valor, categoriaDespesa, dataHoraRealizada);
        this.parcelado = true;
        this.quantidadeParcela = quantidadeParcela;
    }
}

