import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.entity.*;
import org.junit.Test;
import ru.repository.UserRepository;
import ru.specifications.EmptySpecification;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserRepositoryTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    @Rollback
    @Test
    public void addUser(){
        User user = new User(200,"Test name",false);
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
        UserRepository rep = (UserRepository)context.getBean("userRepository");
        rep.removeUser(user);
        String sqlQuery = String.format("WHERE user_id = %s",user.getUserId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<User> testUser = rep.query(spec);
        assertEquals(testUser.size(),0);
    }

    @Rollback
    @Test
    public void updateUser() throws Exception {
        User user = new User(200,"Test name1",true);
        UserRepository rep = (UserRepository)context.getBean("userRepository");
        rep.updateUser(user);
        String sqlQuery = String.format("WHERE user_id = %s",user.getUserId());
        EmptySpecification spec = new EmptySpecification(sqlQuery);
        List<User> testUser = rep.query(spec);
        assertEquals(user.getUserId(),testUser.get(0).getUserId());
        assertEquals(user.getName(),testUser.get(0).getName());
        assertEquals(user.getIsAdmin(),testUser.get(0).getIsAdmin());
    }

}
