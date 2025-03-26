package org.example.Repository;

import org.example.model.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    User getUserById(Integer id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Integer id);
}
