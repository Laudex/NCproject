/*import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.dbclasses.DBConnectionFactory;
import ru.entity.*;
import org.junit.Test;
import ru.repository.UserRepository;
import ru.specifications.EmptySpecification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserRepositoryTest {
    @Rollback
    @Test
    public void addUser(){
        User user = new User(200,"Test name",false);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserRepository rep = (UserRepository) context.getBean("userRepository");
        rep.addUser(user);
        String sqlQuery = String.format("WHERE user_id = %s",user.getUserId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<User> testUser = rep.query(spec);
        assertEquals(user.getUserId(),testUser.get(0).getUserId());
        assertEquals(user.getName(),testUser.get(0).getName());
        assertEquals(user.getIsAdmin(),testUser.get(0).getIsAdmin());
    }

    @Rollback
    @Test
    public void removeUser() throws Exception {
        User user = new User(200,"Test name",false);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserRepository rep = (UserRepository)context.getBean("userRepository");
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

    @Rollback
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
*/