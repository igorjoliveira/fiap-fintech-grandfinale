package br.com.fiap.fintechgrandfinale.domain.enums;

public enum TipoPagamento {
    Cartao(1),
    Cheque(2),
    Transferencia(3),
    Outros(4);

    private final int value;
    public int getValue() { return this.value; }

    TipoPagamento(int value) {
        this.value = value;
    }
}