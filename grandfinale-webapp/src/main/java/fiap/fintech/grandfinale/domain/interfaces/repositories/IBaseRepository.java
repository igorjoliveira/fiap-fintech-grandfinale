package fiap.fintech.grandfinale.domain.interfaces.repositories;

import java.util.List;

public interface IBaseRepository<TEntity> {
    String getConnectionString();
    List<TEntity> getAll();
    TEntity getById(int id);
    void insert(TEntity entity);
    void update(TEntity entity);
    void delete(int id);
}
