package br.com.fiap.fintechgrandfinale.application.models;

import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import java.util.List;

public class CarteiraDigitalModel {
    private List<CarteiraDigital> carteiras;

    public List<CarteiraDigital> getCarteiras() {
        return carteiras;
    }
    public void setCarteiras(List<CarteiraDigital> carteiras) {
        this.carteiras = carteiras;
    }
}
