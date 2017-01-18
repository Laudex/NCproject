package DBclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DBChanger {
    public static void changeEntity(String sqlQuery){
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/NCproject", "postgres","1");
            connection.setAutoCommit(false);
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
