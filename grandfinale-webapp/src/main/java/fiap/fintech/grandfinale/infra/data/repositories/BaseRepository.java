package fiap.fintech.grandfinale.infra.data.repositories;

import fiap.fintech.grandfinale.domain.interfaces.repositories.IBaseRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseRepository<TEntity> implements IBaseRepository<TEntity> {
    private String _user = "rm558132";
    private String _password = "230693";

    @Override
    public String getConnectionString() {
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    }

    public abstract List<TEntity> getAll();
    public abstract TEntity getById(int id);
    public abstract void insert(TEntity entity);
    public abstract void update(TEntity entity);
    public abstract void delete(int id);

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", _user, _password);
    }
}
