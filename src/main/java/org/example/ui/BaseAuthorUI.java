package org.example.ui;

import org.example.enums.StorageType;
import org.example.model.Author;
import org.example.service.AuthorService;
import org.example.service.impl.AuthorServiceImpl;
import org.example.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class BaseAuthorUI {

    private final AuthorService authorService;
    private final StorageType storageType;
    private final String storageTypeName;

    public BaseAuthorUI(StorageType storageType, String storageTypeName) {
        this.authorService = new AuthorServiceImpl();
        this.storageType = storageType;
        this.storageTypeName = storageTypeName;
    }

    Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n");
            System.out.println("==============LIBRARY MANAGEMENT=============");
            System.out.println("-------------STORAGE WITH " + storageTypeName + "-----------");
            System.out.println("---------------AUTHOR MANAGEMENT---------------");
            System.out.println("1. Add new author");
            System.out.println("2. Get author by id");
            System.out.println("3. Get all authors");
            System.out.println("4. Update author by id");
            System.out.println("5. Delete author by id");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch(choice) {
                    case 1 -> addAuthor();
                    case 2 -> getAuthorById();
                    case 3 -> getAllAuthors();
                    case 4 -> updateAuthor();
                    case 5 -> deleteAuthor();
                    case 6 -> {return;}
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addAuthor() {
        System.out.println("======Add new author======");
        Author author = new Author();

        scanner.nextLine();
        author.setName(InputHandler.getStringInput(scanner, "Enter author name: "));
        author.setEmail(InputHandler.getStringInput(scanner, "Enter email: "));
        author.setPhone(InputHandler.getStringInput(scanner, "Enter phone number: "));

        authorService.addAuthor(author, storageType);
        System.out.println("Added new author");
    }

    private void getAuthorById() {
        System.out.println("======Get author by id======");

        try {
            scanner.nextLine();
            Integer authorId = InputHandler.getIntInput(scanner, "Enter author id: ");

            Author author = authorService.getAuthorById(authorId, storageType);

            System.out.println(author.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getAllAuthors() {
        System.out.println("======Get all authors======");

        List<Author> authors = authorService.getAllAuthors(storageType);

        if(authors == null || authors.isEmpty()) {
            System.out.println("Not authors found");
        } else {
            for(Author author : authors) {
                System.out.print(author.toString());
                System.out.println();
            }
        }
    }

    private void updateAuthor() {
        System.out.println("======Update author======");

        try {
            scanner.nextLine();
            Integer categoryId = InputHandler.getIntInput(scanner, "Enter author id you want to update: ");

            Author author = authorService.getAuthorById(categoryId, storageType);

            author.setName(InputHandler.getStringInput(scanner, "Enter author name: "));
            author.setEmail(InputHandler.getStringInput(scanner, "Enter email: "));
            author.setPhone(InputHandler.getStringInput(scanner, "Enter phone number: "));

            authorService.updateAuthor(author, storageType);
            System.out.println("Updated author with id: " + categoryId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteAuthor() {
        System.out.println("======Delete author======");

        try {
            scanner.nextLine();
            Integer authorId = InputHandler.getIntInput(scanner, "Enter author id you want to delete: ");

            authorService.deleteAuthor(authorId, storageType);
            System.out.println("Deleted author with id: " + authorId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
