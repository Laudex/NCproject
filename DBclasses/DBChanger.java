package DBclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DBChanger {
    public static void changeEntity(String sqlQuery){
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlQuery);
            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

}
