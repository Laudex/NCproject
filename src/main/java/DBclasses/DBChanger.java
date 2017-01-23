package DBclasses;

import java.sql.Connection;
import java.sql.Statement;


public class DBChanger {
    public static void changeEntity(String sqlQuery){
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlQuery);
            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
