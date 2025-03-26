package org.example.service.impl;

import org.example.Repository.UserRepository;
import org.example.Repository.impl.UserRepositoryDBImpl;
import org.example.Repository.impl.UserRepositoryFileImpl;
import org.example.enums.StorageType;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepositoryDB;
    private final UserRepository userRepositoryFile;

    public UserServiceImpl() {
        this.userRepositoryDB = new UserRepositoryDBImpl();
        this.userRepositoryFile = new UserRepositoryFileImpl();
    }

    private UserRepository getRepository(StorageType storageType) {
        return storageType == StorageType.FILE ? userRepositoryFile : userRepositoryDB;
    }

    @Override
    public void addUser(User user, StorageType storageType) {
        getRepository(storageType).addUser(user);
    }

    @Override
    public User getUserById(Integer id, StorageType storageType) {
        User user = getRepository(storageType).getUserById(id);

        if(user == null) {
            throw new RuntimeException("User id " + id + " not exist");
        }

        return user;
    }

    @Override
    public List<User> getAllUsers(StorageType storageType) {
        return getRepository(storageType).getAllUsers();
    }

    @Override
    public void updateUser(User user, StorageType storageType) {
        getUserById(user.getId(), storageType);

        getRepository(storageType).updateUser(user);
    }

    @Override
    public void deleteUser(Integer id, StorageType storageType) {
        getUserById(id, storageType);

        getRepository(storageType).deleteUser(id);
    }
}
