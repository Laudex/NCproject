package Repository;

import DBclasses.DBConnectionFactory;
import Entity.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;


public class UserRepositoryTest {
    @Test
    public void addUser() throws Exception {
        User user = new User(200,false);
        UserRepository rep = new UserRepository();
        rep.addUser(user);
        User testUser = new User();
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testUser.setUserId(rs.getInt("user_id"));
                testUser.setAdmin(rs.getBoolean("is_admin"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(user.getUserId(),testUser.getUserId());
        assertEquals(user.getisAdmin(),testUser.getisAdmin());
    }

    @Test
    public void removeUser() throws Exception {
        User user = new User(200,false);
        UserRepository rep = new UserRepository();
        rep.removeUser(user);
        User testUser = null;
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testUser = new User();
                testUser.setUserId(rs.getInt("user_id"));
                testUser.setAdmin(rs.getBoolean("is_admin"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertNull(testUser);
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User(10,true);
        UserRepository rep = new UserRepository();
        rep.updateUser(user);
        User testUser = new User();
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testUser.setUserId(rs.getInt("user_id"));
                testUser.setAdmin(rs.getBoolean("is_admin"));
            }
            rs.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        assertEquals(user.getUserId(),testUser.getUserId());
        assertEquals(user.getisAdmin(),testUser.getisAdmin());
    }

}