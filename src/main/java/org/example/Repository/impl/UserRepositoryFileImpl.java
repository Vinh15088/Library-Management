package org.example.Repository.impl;

import org.example.Repository.UserRepository;
import org.example.model.User;
import org.example.utils.CSVFileUtils;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryFileImpl implements UserRepository {

    private static final String FILE_PATH = "data/users.csv";
    private static final String CSV_HEADER = "id,fullName,email,phone,address";

    public UserRepositoryFileImpl() {
        CSVFileUtils.initCSVFile(FILE_PATH, CSV_HEADER);
    }

    @Override
    public void addUser(User user) {
        List<User> users = getAllUsers();

        if(users == null) {
            users = new ArrayList<User>();
        }

        int newId = CSVFileUtils.getNextId(users, User::getId);
        user.setId(newId);

        users.add(user);

        saveToFile(users);
    }

    @Override
    public User getUserById(Integer id) {
        return getAllUsers().stream().filter(user -> user.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return CSVFileUtils.readAllFromCSVFile(FILE_PATH, this::parseUserFromCSV);
    }

    @Override
    public void updateUser(User user) {
        List<User> users = getAllUsers();
        boolean check = false;

        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                check = true;
                break;
            }
        }

        if(check) {
            saveToFile(users);
        } else {
            System.out.println("User id not found");
        }
    }

    @Override
    public void deleteUser(Integer id) {
        List<User> users = getAllUsers();
        boolean check = users.removeIf(user -> user.getId().equals(id));

        if(check) {
            saveToFile(users);
        } else {
            System.out.println("User id not found");
        }
    }

    private void saveToFile(List<User> users) {
        CSVFileUtils.saveToCSV(FILE_PATH, CSV_HEADER, users, this::convertUserToCSV);
    }

    private String convertUserToCSV(User user) {
        return String.join(",",
                String.valueOf(user.getId()),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress()
        );
    }

    private User parseUserFromCSV(String line) {
        String[] values = line.split(",");

        User user = new User();
        user.setId(Integer.parseInt(values[0].trim()));
        user.setFullName(values[1].trim());
        user.setEmail(values[2].trim());
        user.setPhone(values[3].trim());
        user.setAddress(values[4].trim());

        return user;
    }
}
