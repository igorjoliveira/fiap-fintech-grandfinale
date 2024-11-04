package fiap.fintech.grandfinale.domain.entities.forma.pagamento;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(int codigoCateiraDigital, String agencia, String numero, char digito) {
        super(codigoCateiraDigital, agencia, numero, digito);
    }

    @Override
    public char getTipo() {
        return 'P';
    }
}
