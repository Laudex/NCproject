package ru.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.entity.User;
import ru.interfaces.repository.IUserRepository;
import ru.interfaces.specification.Specification;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserRepository implements IUserRepository {


    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users (user_id, name, password, is_admin) VALUES (?,?,?,?);",user.getUserId(),user.getName(),user.getPassword(),user.getIsAdmin());
    }

    public void removeUser(User user) {
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?;",user.getUserId());
    }

    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users SET is_admin = ?, name = ?, password = ? WHERE user_id = ?;",user.getIsAdmin(),user.getName(),user.getPassword(),user.getUserId());
    }

    public List query(Specification specification) {
        String sql = "SELECT * FROM users " + specification.toSqlClauses();
        List<User> specificUsers = jdbcTemplate.query(sql, new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum) throws SQLException{
                User nextUser = new User();
                nextUser.setUserId(rs.getInt("user_id"));
                nextUser.setName(rs.getString("name"));
                nextUser.setPassword(rs.getString("password"));
                nextUser.setAdmin(rs.getBoolean("is_admin"));
                return nextUser;
            }
        });
        return specificUsers;
    }
}
