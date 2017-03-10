import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.entity.*;
import org.junit.Test;
import ru.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@Transactional
public class UserRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository rep;

    @Test
    public void addUser(){
        User user = new User(200,"Test name","Test password",false);
        rep.addUser(user);
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        List<User> testUser = jdbcTemplate.query(sqlQuery, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setName(rs.getString("name"));
                nextUser.setPassword(rs.getString("password"));
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
        User user = new User(11,"Test name","Test password",false);
        rep.removeUser(user);
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        List<User> testUser = jdbcTemplate.query(sqlQuery, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setName(rs.getString("name"));
                nextUser.setPassword(rs.getString("password"));
                nextUser.setAdmin(rs.getBoolean("is_admin"));
                return nextUser;
            }
        });
        assertEquals(testUser.size(),0);
    }
    
    @Test
    public void updateUser() throws Exception {
        User user = new User(11,"Test name1","Test password",true);
        rep.updateUser(user);
        String sqlQuery = String.format("SELECT * FROM users WHERE user_id = %s",user.getUserId());
        List<User> testUser = jdbcTemplate.query(sqlQuery, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setName(rs.getString("name"));
                nextUser.setPassword(rs.getString("password"));
                nextUser.setAdmin(rs.getBoolean("is_admin"));
                return nextUser;
            }
        });
        assertEquals(user.getUserId(),testUser.get(0).getUserId());
        assertEquals(user.getName(),testUser.get(0).getName());
        assertEquals(user.getIsAdmin(),testUser.get(0).getIsAdmin());
    }

}
