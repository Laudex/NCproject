package ru.interfaces.repository;

import java.util.List;
import ru.entity.User;
import ru.interfaces.specification.Specification;


public interface IUserRepository {
    void addUser(User user);
    void removeUser(User user);
    void updateUser(User user);

    List query(Specification specification);
}
