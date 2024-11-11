package br.com.fiap.fintechgrandfinale.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.Despesa;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IDespesaRepository;
import java.util.List;

public class DespesaRepository extends BaseRepository<Despesa> implements IDespesaRepository {
    @Override
    public List<Despesa> getAll() {
        return List.of();
    }

    @Override
    public Despesa getById(int id) {
        return null;
    }

    @Override
    public int insert(Despesa despesa) {
        return 0;
    }

    @Override
    public void update(Despesa despesa) {

    }

    @Override
    public void delete(int id) {

    }
}
