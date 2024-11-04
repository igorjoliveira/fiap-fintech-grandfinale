package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.CarteiraDigital;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.ICarteiraDigitalRepository;
import java.util.List;

public class CarteiraDigitalRepository extends BaseRepository<CarteiraDigital> implements ICarteiraDigitalRepository {
    @Override
    public List<CarteiraDigital> getAll() {
        return List.of();
    }

    @Override
    public CarteiraDigital getById(int id) {
        return null;
    }

    @Override
    public int insert(CarteiraDigital carteiraDigital) {
        return 0;
    }

    @Override
    public void update(CarteiraDigital carteiraDigital) {

    }

    @Override
    public void delete(int id) {

    }
}
