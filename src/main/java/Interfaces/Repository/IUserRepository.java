package Interfaces.Repository;

import java.util.List;
import Entity.User;
import Interfaces.Specification.Specification;


public interface IUserRepository {
    void addUser(User user);
    void removeUser(User user);
    void updateUser(User user);

    List query(Specification specification);
}
