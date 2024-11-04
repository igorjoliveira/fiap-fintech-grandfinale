package fiap.fintech.grandfinale.domain.enums;

public enum Sexo {
    Masculino,
    Feminino;

    public static Sexo Converter(int item){
        return switch (item) {
            case 1 -> Sexo.Masculino;
            case 2 -> Sexo.Feminino;
            default -> null;
        };
    }
}
