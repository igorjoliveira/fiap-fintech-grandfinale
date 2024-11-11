package br.com.fiap.fintechgrandfinale.domain.enums;

public enum InstituicaoFinanceira {
    BB(1),
    C6(2),
    XP(3),
    Itau(4),
    Nubank(5),
    Bradesco(6),
    Santander(7);

    private final int value;
    public int getValue() { return this.value; }

    InstituicaoFinanceira(int value) {
        this.value = value;
    }
}