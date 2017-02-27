import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.entity.*;
import org.junit.Test;
import ru.repository.UserRepository;
import ru.specifications.EmptySpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@Transactional
public class UserRepositoryTest {
    JdbcTemplate jdbcTemplate;
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    public void addUser(){
        User user = new User(200,"Test name",false);
        String sqlQuery = String.format("INSERT INTO users (user_id, name,is_admin) VALUES (%s,\'%s\',%b);",user.getUserId(),user.getName(),user.getIsAdmin());
        jdbcTemplate.update(sqlQuery);
        String sqlQuery2 = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        List<User> testUser = this.jdbcTemplate.query(sqlQuery2, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setName(rs.getString("name"));
                nextUser.setAdmin(rs.getBoolean("is_admin"));
                return nextUser;
            }
        });
        assertEquals(user.getUserId(),testUser.get(0).getUserId());
        assertEquals(user.getName(),testUser.get(0).getName());
        assertEquals(user.getIsAdmin(),testUser.get(0).getIsAdmin());
    }

    @Test
    public void removeUser() throws Exception {
        User user = new User(11,"Test name",false);
        String sqlQuery = String.format("DELETE FROM users WHERE user_id = %s;",user.getUserId());
        jdbcTemplate.update(sqlQuery);
        String sqlQuery2 = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        List<User> testUser = this.jdbcTemplate.query(sqlQuery2, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setName(rs.getString("name"));
                nextUser.setAdmin(rs.getBoolean("is_admin"));
                return nextUser;
            }
        });
        assertEquals(testUser.size(),0);
    }
    
    @Test
    public void updateUser() throws Exception {
        User user = new User(11,"Test name1",true);
        String sqlQuery = String.format("UPDATE users SET is_admin = %b, name = \'%s\' WHERE user_id = %s;",user.getIsAdmin(),user.getName(),user.getUserId());
        jdbcTemplate.update(sqlQuery);
        String sqlQuery2 = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        List<User> testUser = this.jdbcTemplate.query(sqlQuery2, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setName(rs.getString("name"));
                nextUser.setAdmin(rs.getBoolean("is_admin"));
                return nextUser;
            }
        });
        assertEquals(user.getUserId(),testUser.get(0).getUserId());
        assertEquals(user.getName(),testUser.get(0).getName());
        assertEquals(user.getIsAdmin(),testUser.get(0).getIsAdmin());
    }

}
