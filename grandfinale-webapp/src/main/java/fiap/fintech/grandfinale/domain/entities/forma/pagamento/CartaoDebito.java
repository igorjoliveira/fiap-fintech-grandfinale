package fiap.fintech.grandfinale.domain.entities.forma.pagamento;

public class CartaoDebito extends Cartao {
    public CartaoDebito(int codigoCateiraDigital, String numero, String nome, String dataVencimento) {
        super(codigoCateiraDigital, numero, nome, dataVencimento);
    }

    @Override
    public char getTipoCartao() {
        return 'D';
    }
}
