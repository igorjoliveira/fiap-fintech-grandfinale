package  br.com.fiap.fintechgrandfinale.domain.entities.forma.pagamento;

import br.com.fiap.fintechgrandfinale.domain.enums.TipoPagamento;
import java.time.LocalDateTime;

public abstract class Cartao extends FormaPagamento {

    private String numero;
    private String nome;
    private String dataVencimento;

    public String getNumero() {
        return numero;
    }
    public String getNome() {
        return nome;
    }
    public String getDataVencimento() {
        return dataVencimento;
    }
    public abstract char getTipoCartao();

    public Cartao(int codigoCateiraDigital, String numero, String nome, String dataVencimento) {
        super(codigoCateiraDigital, TipoPagamento.Cartao);

        this.numero = numero;
        this.nome = nome;
        this.dataVencimento = dataVencimento;
        this.setDataHoraCadastro(LocalDateTime.now());
    }
}

