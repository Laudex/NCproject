package ru.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
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
        String sqlQuery = String.format("INSERT INTO users (user_id, name, password, is_admin) VALUES (%s,\'%s\',\'%s\',%b);",user.getUserId(),user.getName(),user.getPassword(),user.getIsAdmin());
        jdbcTemplate.update(sqlQuery);
    }

    public void removeUser(User user) {
        String sqlQuery = String.format("DELETE FROM users WHERE user_id = %s;",user.getUserId());
        jdbcTemplate.update(sqlQuery);
    }

    public void updateUser(User user) {
        String sqlQuery = String.format("UPDATE users SET is_admin = %b, name = \'%s\', password = \'%s\' WHERE user_id = %s;",user.getIsAdmin(),user.getName(),user.getPassword(),user.getUserId());
        jdbcTemplate.update(sqlQuery);
    }

    @Transactional
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
