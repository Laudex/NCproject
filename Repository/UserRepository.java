package Repository;

import DBclasses.DBConnection;
import Entity.User;
import Interfaces.Repository.IUserRepository;
import Interfaces.Specification.UserSpecification;

import java.util.ArrayList;
import java.util.List;


public class UserRepository implements IUserRepository {
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List query(UserSpecification specification) {
        List<User> specificUsers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE " + specification.toSqlClauses();
        DBConnection.selectUsers(sql,specificUsers);
        return specificUsers;
    }
}
