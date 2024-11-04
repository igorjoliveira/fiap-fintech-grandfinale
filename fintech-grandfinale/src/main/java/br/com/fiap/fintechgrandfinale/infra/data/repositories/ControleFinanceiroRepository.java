package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IControleFinanceiroRepository;
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
    public int insert(ControleFinanceiro controleFinanceiro) {
        return 0;
    }

    @Override
    public void update(ControleFinanceiro controleFinanceiro) {

    }

    @Override
    public void delete(int id) {

    }
}
