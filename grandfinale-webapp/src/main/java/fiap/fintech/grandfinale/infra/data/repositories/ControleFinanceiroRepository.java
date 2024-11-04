package fiap.fintech.grandfinale.infra.data.repositories;

import fiap.fintech.grandfinale.domain.entities.ControleFinanceiro;
import fiap.fintech.grandfinale.domain.interfaces.repositories.IControleFinanceiroRepository;
import java.util.List;

public class ControleFinanceiroRepository extends BaseRepository<ControleFinanceiro> implements IControleFinanceiroRepository {
    @Override
    public List<ControleFinanceiro> getAll() {
        return List.of();
    }

    @Override
    public ControleFinanceiro getById(int id) {
        return null;
    }

    @Override
    public void insert(ControleFinanceiro controleFinanceiro) {

    }

    @Override
    public void update(ControleFinanceiro controleFinanceiro) {

    }

    @Override
    public void delete(int id) {

    }
}
