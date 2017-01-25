import DBclasses.DBConnectionFactory;
import Entity.*;
import org.junit.Test;
import Repository.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;


public class UserRepositoryTest {
    @Test
    public void addUser() throws Exception {
        User user = new User(200,"Test name",false);
        UserRepository rep = new UserRepository();
        rep.addUser(user);
        User testUser = new User();
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testUser.setUserId(rs.getInt("user_id"));
                testUser.setName(rs.getString("name"));
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
        assertEquals(user.getName(),testUser.getName());
        assertEquals(user.getIsAdmin(),testUser.getIsAdmin());
    }

    @Test
    public void removeUser() throws Exception {
        User user = new User(200,"Test name",false);
        UserRepository rep = new UserRepository();
        rep.removeUser(user);
        User testUser = null;
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testUser = new User();
                testUser.setUserId(rs.getInt("user_id"));
                testUser.setName(rs.getString("name"));
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
        User user = new User(10,"Test name1",true);
        UserRepository rep = new UserRepository();
        rep.updateUser(user);
        User testUser = new User();
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        Connection connection;
        Statement stmt;
        try {
            connection = DBConnectionFactory.conFactory();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                testUser.setUserId(rs.getInt("user_id"));
                testUser.setName(rs.getString("name"));
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
        assertEquals(user.getName(),testUser.getName());
        assertEquals(user.getIsAdmin(),testUser.getIsAdmin());
    }

}