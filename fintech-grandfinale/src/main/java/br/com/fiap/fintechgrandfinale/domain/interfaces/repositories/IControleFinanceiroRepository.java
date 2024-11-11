package br.com.fiap.fintechgrandfinale.domain.interfaces.repositories;

import br.com.fiap.fintechgrandfinale.domain.entities.ControleFinanceiro;
import java.util.List;

public interface IControleFinanceiroRepository extends IBaseRepository<ControleFinanceiro> {
    List<ControleFinanceiro> getAll(int codigoUsuario, String descricao);
}
