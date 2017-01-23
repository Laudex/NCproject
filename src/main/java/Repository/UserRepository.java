package Repository;

import DBclasses.DBChanger;
import DBclasses.DBConnection;
import Entity.User;
import Interfaces.Repository.IUserRepository;
import Interfaces.Specification.UserSpecification;

import java.util.ArrayList;
import java.util.List;


public class UserRepository implements IUserRepository {


    public void addUser(User user) {
        String sqlQuery = String.format("INSERT INTO users (user_id, is_admin) VALUES (%s,%b);",user.getUserId(),user.getIsAdmin());
        DBChanger.changeEntity(sqlQuery);
    }

    public void removeUser(User user) {
        String sqlQuery = String.format("DELETE FROM users WHERE user_id = %s;",user.getUserId());
        DBChanger.changeEntity(sqlQuery);
    }

    public void updateUser(User user) {
        String sqlQuery = String.format("UPDATE users SET is_admin = %b WHERE user_id = %s;",user.getIsAdmin(),user.getUserId());
        DBChanger.changeEntity(sqlQuery);
    }


    public List query(UserSpecification specification) {
        List<User> specificUsers = new ArrayList<User>();
        String sql = "SELECT * FROM users WHERE " + specification.toSqlClauses();
        DBConnection.selectUsers(sql,specificUsers);
        return specificUsers;
    }
}
