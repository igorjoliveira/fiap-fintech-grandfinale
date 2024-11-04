package fiap.fintech.grandfinale.infra.data.repositories;

import fiap.fintech.grandfinale.domain.entities.Renda;
import fiap.fintech.grandfinale.domain.interfaces.repositories.IRendaRepository;
import java.util.List;

public class RendaRepository extends BaseRepository<Renda> implements IRendaRepository {
    @Override
    public List<Renda> getAll() {
        return List.of();
    }

    @Override
    public Renda getById(int id) {
        return null;
    }

    @Override
    public void insert(Renda renda) {

    }

    @Override
    public void update(Renda renda) {

    }

    @Override
    public void delete(int id) {

    }
}
