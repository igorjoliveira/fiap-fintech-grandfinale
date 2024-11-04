package br.com.fiap.fintechgrandfinale.domain.enums;

public enum InstituicaoFinanceira {
    C6(1),
    XP(2),
    Itau(3),
    Nubank(4),
    Bradesco(5),
    Santander(6);

    private final int value;
    public int getValue() { return this.value; }

    InstituicaoFinanceira(int value) {
        this.value = value;
    }
}