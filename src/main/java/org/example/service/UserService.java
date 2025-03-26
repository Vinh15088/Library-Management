package org.example.service;

import org.example.enums.StorageType;
import org.example.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user, StorageType storageType);
    User getUserById(Integer id, StorageType storageType);
    List<User> getAllUsers(StorageType storageType);
    void updateUser(User user, StorageType storageType);
    void deleteUser(Integer id, StorageType storageType);
}
