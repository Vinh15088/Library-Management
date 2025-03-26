package org.example.ui;

import org.example.enums.StorageType;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.example.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class BaseUserUI {

    private final UserService userService;
    private final StorageType storageType;
    private final String storageTypeName;

    public BaseUserUI(StorageType storageType, String storageTypeName) {
        this.userService = new UserServiceImpl();
        this.storageType = storageType;
        this.storageTypeName = storageTypeName;
    }

    Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n");
            System.out.println("==============LIBRARY MANAGEMENT=============");
            System.out.println("-------------STORAGE WITH " + storageTypeName + "-----------");
            System.out.println("---------------USER MANAGEMENT---------------");
            System.out.println("1. Add new user");
            System.out.println("2. Get user by id");
            System.out.println("3. Get all users");
            System.out.println("4. Update user by id");
            System.out.println("5. Delete user by id");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch(choice) {
                    case 1 -> addUser();
                    case 2 -> getUserById();
                    case 3 -> getAllUsers();
                    case 4 -> updateUser();
                    case 5 -> deleteUser();
                    case 6 -> {return;}
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addUser() {
        System.out.println("======Add new user======");
        User user = new User();

        scanner.nextLine();
        user.setFullName(InputHandler.getStringInput(scanner, "Enter full name: "));
        user.setEmail(InputHandler.getStringInput(scanner, "Enter email: "));
        user.setPhone(InputHandler.getStringInput(scanner, "Enter phone number: "));
        user.setAddress(InputHandler.getStringInput(scanner, "Enter address: "));

        userService.addUser(user, storageType);
        System.out.println("Added new user");
    }

    private void getUserById() {
        System.out.println("======Get user by id======");

        try {
            scanner.nextLine();
            Integer userId = InputHandler.getIntInput(scanner, "Enter user id: ");

            User user = userService.getUserById(userId, storageType);

            System.out.println(user.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getAllUsers() {
        System.out.println("======Get all users======");

        List<User> users = userService.getAllUsers(storageType);

        for(User user : users) {
            System.out.print(user.toString());
            System.out.println();
        }
    }

    private void updateUser() {
        System.out.println("======Update user======");

        try {
            scanner.nextLine();
            Integer userId = InputHandler.getIntInput(scanner, "Enter user id you want to update: ");

            User user = userService.getUserById(userId, storageType);

            user.setFullName(InputHandler.getStringInput(scanner, "Enter full name: "));
            user.setEmail(InputHandler.getStringInput(scanner, "Enter email: "));
            user.setPhone(InputHandler.getStringInput(scanner, "Enter phone number: "));
            user.setAddress(InputHandler.getStringInput(scanner, "Enter address: "));

            userService.updateUser(user, storageType);
            System.out.println("Updated author with id: " + userId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.println("======Delete user======");

        try {
            scanner.nextLine();
            Integer userId = InputHandler.getIntInput(scanner, "Enter user id you want to delete: ");

            userService.deleteUser(userId, storageType);
            System.out.println("Deleted author with id: " + userId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
