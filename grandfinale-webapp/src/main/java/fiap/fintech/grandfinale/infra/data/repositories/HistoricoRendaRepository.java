package fiap.fintech.grandfinale.infra.data.repositories;

import fiap.fintech.grandfinale.domain.entities.HistoricoRenda;
import fiap.fintech.grandfinale.domain.interfaces.repositories.IHistoricoRendaRepository;
import java.util.List;

public class HistoricoRendaRepository extends BaseRepository<HistoricoRenda> implements IHistoricoRendaRepository {
    @Override
    public List<HistoricoRenda> getAll() {
        return List.of();
    }

    @Override
    public HistoricoRenda getById(int id) {
        return null;
    }

    @Override
    public int insert(HistoricoRenda historicoRenda) {
        return 0;
    }

    @Override
    public void update(HistoricoRenda historicoRenda) {

    }

    @Override
    public void delete(int id) {

    }
}
