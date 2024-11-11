package br.com.fiap.fintechgrandfinale.domain.enums;

public enum Autenticador {
    Interno(1),
    Externo(2);

    private final int value;
    public int getValue() { return this.value; }

    Autenticador(int value) {
        this.value = value;
    }
}