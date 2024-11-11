package br.com.fiap.fintechgrandfinale.domain.entities.forma.pagamento;

import br.com.fiap.fintechgrandfinale.domain.enums.TipoPagamento;
import java.time.LocalDateTime;

public class Cheque extends FormaPagamento {
    private int numeroTalao;
    private int quantidadeFolha;
    private Boolean bloqueado;

    public int getNumeroTalao() { return numeroTalao; }
    public int getQuantidadeFolha() { return quantidadeFolha; }
    public Boolean getBloqueado() { return bloqueado; }

    public Cheque(int codigoCateiraDigital, int numeroTalao, int quantidadeFolha) {
        super(codigoCateiraDigital, TipoPagamento.Cheque);

        this.numeroTalao = numeroTalao;
        this.quantidadeFolha = quantidadeFolha;
        this.bloqueado = true;
    }

    public Cheque habilitarTalao(){
        bloqueado = false;
        this.setDataHoraAtualizacao(LocalDateTime.now());
        return this;
    }
}
