package fiap.fintech.grandfinale.domain.enums;

public enum Sexo {
    Masculino(1),
    Feminino(2);

    private final int value;
    public int getValue() { return this.value; }

    Sexo(int value) {
        this.value = value;
    }
}