package DBclasses;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionFactory {
    public static Connection conFactory(){
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/NCproject", "postgres", "1");
            connection.setAutoCommit(false);
            return connection;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
