package fiap.fintech.grandfinale.infra.data.repositories;

import fiap.fintech.grandfinale.domain.entities.Despesa;
import fiap.fintech.grandfinale.domain.interfaces.repositories.IDespesaRepository;
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
