package br.com.fiap.fintechgrandfinale.domain.entities.forma.pagamento;

import br.com.fiap.fintechgrandfinale.domain.entities.BaseModel;
import br.com.fiap.fintechgrandfinale.domain.enums.TipoPagamento;

public abstract class FormaPagamento extends BaseModel {
    private int codigoCateiraDigital;
    private TipoPagamento codigoTipoPagamento;

    public int getCodigoCateiraDigital() {
        return codigoCateiraDigital;
    }
    public TipoPagamento getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public FormaPagamento(int codigoCateiraDigital, TipoPagamento codigoTipoPagamento) {
        super();
        this.codigoCateiraDigital = codigoCateiraDigital;
        this.codigoTipoPagamento = codigoTipoPagamento;
    }
}

