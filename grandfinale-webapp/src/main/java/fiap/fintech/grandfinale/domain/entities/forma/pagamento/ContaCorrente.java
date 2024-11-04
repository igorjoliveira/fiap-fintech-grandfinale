package fiap.fintech.grandfinale.domain.entities.forma.pagamento;

public class ContaCorrente extends Conta {

    public ContaCorrente(int codigoCateiraDigital, String agencia, String numero, char digito) {
        super(codigoCateiraDigital, agencia, numero, digito);
    }

    @Override
    public char getTipo() {
        return 'C';
    }
}
