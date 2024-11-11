package br.com.fiap.fintechgrandfinale.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.Renda;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IRendaRepository;
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
    public int insert(Renda renda) {
        return 0;
    }

    @Override
    public void update(Renda renda) {

    }

    @Override
    public void delete(int id) {

    }
}
