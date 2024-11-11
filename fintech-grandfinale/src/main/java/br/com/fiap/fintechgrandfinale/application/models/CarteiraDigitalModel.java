package br.com.fiap.fintechgrandfinale.application.models;

import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.enums.InstituicaoFinanceira;

import java.util.List;

public class CarteiraDigitalModel {
    private List<CarteiraDigital> carteiras;
    private List<ControleFinanceiro> grupos;
    private List<InstituicaoFinanceira> instituicoes;

    public List<CarteiraDigital> getCarteiras() {
        return carteiras;
    }
    public List<ControleFinanceiro> getGrupos() {
        return grupos;
    }
    public List<InstituicaoFinanceira> getInstituicoes() {
        return instituicoes;
    }
    public void setCarteiras(List<CarteiraDigital> carteiras) {
        this.carteiras = carteiras;
    }
    public void setGrupos(List<ControleFinanceiro> grupos) {
        this.grupos = grupos;
    }
    public void setInstituicoes(List<InstituicaoFinanceira> instituicoes) {
        this.instituicoes = instituicoes;
    }
}
