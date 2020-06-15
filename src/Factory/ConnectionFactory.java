package Factory;

import java.sql.*;

public class ConnectionFactory {
        public Connection getConnection(){
        try {       
            return DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
