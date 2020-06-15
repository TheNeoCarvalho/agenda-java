package Factory;
import java.sql.*;
public class TestaConexao {
    
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        System.out.println("Deu bom!");
        connection.close();
        
    }
    
}
