package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.HistoricoRenda;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IHistoricoRendaRepository;
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
