package database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DataSource {
    
     private MysqlDataSource dataSource = new MysqlDataSource();

    public DataSource() {
        dataSource.setServerName("159.89.99.250");
        dataSource.setPort(3306);
        dataSource.setUser("perlt");
        dataSource.setPassword("admin");
        dataSource.setDatabaseName("platform_db");
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }
}
