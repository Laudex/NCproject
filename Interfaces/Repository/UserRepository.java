package Interfaces.Repository;

import java.util.List;
import Entity.User;
import Interfaces.Specification.UserSpecification;


public interface UserRepository {
    void addUser(User user);
    void removeUser(User user);
    void updateUser(User user);

    List query(UserSpecification specification);
}
