package br.com.fiap.fintechgrandfinale.infra.data.repositories;

import br.com.fiap.fintechgrandfinale.domain.interfaces.repositories.IBaseRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseRepository<TEntity> implements IBaseRepository<TEntity> {
    private String user = "RM558132";
    private String password = "230693";

    private Connection connection;

    @Override
    public String getConnectionString() {
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    }

    public abstract List<TEntity> getAll();
    public abstract TEntity getById(int id);
    public abstract int insert(TEntity entity);
    public abstract void update(TEntity entity);
    public abstract void delete(int id);

    protected Connection getConnection(){
        try {
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(getConnectionString(), user, password);
            }

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void closeConnection(){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSequenceCurrentValue(String sequenceName){
        int value = 0;
        try {
            var cnn = getConnection();
            var stm = cnn.prepareStatement(String.format("SELECT %s.CURRVAL FROM dual", sequenceName));
            var resultSet = stm.executeQuery();

            if (resultSet.next()) {
                value = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return value;
    }
}
