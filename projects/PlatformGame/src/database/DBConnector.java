package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;


public class DBConnector {

    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public DBConnector(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public void open() throws SQLException{
        if(connection == null || connection.isClosed()){
            connection = dataSource.getConnection();
        }
    }
    
    public void close() throws SQLException{
        if(resultSet != null){
            resultSet.close();
        }
        if(statement != null){
            statement.close();
        }
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }
    
    public ResultSet executeQuery(String sql) throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        
        return resultSet;
    }
    
    public void executeUpdate(String sql) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    
    public PreparedStatement preparedStatement(String sql) throws SQLException{
        return connection.prepareStatement(sql);
    } 
}
