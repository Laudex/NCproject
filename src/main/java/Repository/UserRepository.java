package Repository;

import DBclasses.DBChanger;
import DBclasses.DBConnection;
import Entity.User;
import Interfaces.Repository.IUserRepository;
import Interfaces.Specification.Specification;


import java.util.ArrayList;
import java.util.List;


public class UserRepository implements IUserRepository {


    public void addUser(User user) {
        String sqlQuery = String.format("INSERT INTO users (user_id, name,is_admin) VALUES (%s,\'%s\',%b);",user.getUserId(),user.getName(),user.getIsAdmin());
        DBChanger.changeEntity(sqlQuery);
    }

    public void removeUser(User user) {
        String sqlQuery = String.format("DELETE FROM users WHERE user_id = %s;",user.getUserId());
        DBChanger.changeEntity(sqlQuery);
    }

    public void updateUser(User user) {
        String sqlQuery = String.format("UPDATE users SET is_admin = %b, name = \'%s\' WHERE user_id = %s;",user.getIsAdmin(),user.getName(),user.getUserId());
        DBChanger.changeEntity(sqlQuery);
    }


    public List query(Specification specification) {
        List<User> specificUsers = new ArrayList<User>();
        String sql = "SELECT * FROM users " + specification.toSqlClauses();
        DBConnection.selectUsers(sql,specificUsers);
        return specificUsers;
    }
}
