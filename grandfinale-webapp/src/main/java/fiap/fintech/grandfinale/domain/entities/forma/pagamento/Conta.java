package fiap.fintech.grandfinale.domain.entities.forma.pagamento;

import fiap.fintech.grandfinale.domain.enums.TipoPagamento;

public abstract class Conta extends FormaPagamento {

    private String agencia;
    private String numero;
    private char digito;

    public String getAgencia() {
        return agencia;
    }
    public String getNumero() {
        return numero;
    }
    public char getDigito() {
        return digito;
    }
    public abstract char getTipo();

    public Conta(int codigoCateiraDigital, String agencia, String numero, char digito) {
        super(codigoCateiraDigital, TipoPagamento.Transferencia);

        this.agencia = agencia;
        this.numero = numero;
        this.digito = digito;
    }
}

