package br.com.fiap.fintechgrandfinale.domain.interfaces.repositories;

import java.sql.SQLException;
import java.util.List;

public interface IBaseRepository<TEntity> {
    String getConnectionString();
    List<TEntity> getAll();
    TEntity getById(int id);
    int insert(TEntity entity) throws SQLException;
    void update(TEntity entity);
    void delete(int id);
    int getSequenceCurrentValue(String sequenceName);
}
