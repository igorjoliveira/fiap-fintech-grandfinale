package br.com.fiap.fintechgrandfinale.domain.enums;

public enum TipoRenda {
    Salario(1),
    ValeAlimentacao(2),
    ValeRestaurante(3),
    Outros(4);

    private final int value;
    public int getValue() { return this.value; }

    TipoRenda(int value) {
        this.value = value;
    }
}