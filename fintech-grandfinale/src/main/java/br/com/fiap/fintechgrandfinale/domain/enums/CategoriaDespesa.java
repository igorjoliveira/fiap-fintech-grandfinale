package br.com.fiap.fintechgrandfinale.domain.enums;

public enum CategoriaDespesa {
    Alimentacao(1),
    Transporte(2),
    Estudo(3),
    Investimento(4),
    Diversao(5),
    Outros(6);

    private final int value;
    public int getValue() { return this.value; }

    CategoriaDespesa(int value) {
        this.value = value;
    }
}