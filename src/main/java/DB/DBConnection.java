package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection getConnection() throws SQLException{
       Connection connection=DriverManager.getConnection("jdbc:h2:file:./Datenbank");
       return connection;
    }
}