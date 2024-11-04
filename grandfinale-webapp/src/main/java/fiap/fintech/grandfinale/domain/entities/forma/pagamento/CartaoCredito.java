package fiap.fintech.grandfinale.domain.entities.forma.pagamento;

import java.time.LocalDateTime;

public class CartaoCredito extends Cartao {

    private String codigoSeguranca;
    private double valorLimite;

    public double getValorLimite() {
        return valorLimite;
    }
    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }
    @Override
    public char getTipoCartao() {
        return 'C';
    }

    public CartaoCredito(int codigoCateiraDigital, String numero, String nome, String dataVencimento, String codigoSeguranca) {
        super(codigoCateiraDigital, numero, nome, dataVencimento);

        this.codigoSeguranca = codigoSeguranca;
    }

    public CartaoCredito atualizarValorLimite(double valorLimite){
        this.valorLimite = valorLimite;
        this.setDataHoraAtualizacao(LocalDateTime.now());
        return this;
    }
}
