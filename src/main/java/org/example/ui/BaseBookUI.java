package org.example.ui;

import org.example.enums.StorageType;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;
import org.example.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class BaseBookUI {

    private final BookService bookService;
    private final StorageType storageType;
    private final String storageTypeName;

    public BaseBookUI(StorageType storageType, String storageTypeName) {
        this.bookService = new BookServiceImpl();
        this.storageType = storageType;
        this.storageTypeName = storageTypeName;
    }

    Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while(true) {
            System.out.println("\n");
            System.out.println("==============LIBRARY MANAGEMENT=============");
            System.out.println("-------------STORAGE WITH " + storageTypeName + "-----------");
            System.out.println("---------------BOOK MANAGEMENT---------------");
            System.out.println("1. Add new book");
            System.out.println("2. Get book by id");
            System.out.println("3. Get books by category");
            System.out.println("4. Get books by author");
            System.out.println("5. Search books by title");
            System.out.println("6. Get all books");
            System.out.println("7. Update book by id");
            System.out.println("8. Delete book by id");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch(choice) {
                    case 1 -> addBook();
                    case 2 -> getBookById();
                    case 3 -> getBooksByCategory();
                    case 4 -> getBooksByAuthor();
                    case 5 -> searchBooksByTitle();
                    case 6 -> getAllBooks();
                    case 7 -> updateBook();
                    case 8 -> deleteBook();
                    case 9 -> {return;}
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addBook() {
        System.out.println("=======Add new book======");
        Book book = new Book();

        try {
            scanner.nextLine();
            book.setCode(InputHandler.getStringInput(scanner, "Enter book code: "));
            book.setTitle(InputHandler.getStringInput(scanner, "Enter book title: "));
            book.setCategoryId(InputHandler.getIntInput(scanner, "Enter book category id: "));
            book.setAuthorId(InputHandler.getIntInput(scanner,  "Enter book author id: "));
            book.setQuantity(InputHandler.getIntInput(scanner, "Enter book quantity: "));

            bookService.addBook(book, storageType);
            System.out.println("Added New Book");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getBookById() {
        System.out.println("=======Get book by id======");

        try {
            scanner.nextLine();
            Integer bookId = InputHandler.getIntInput(scanner, "Enter Book Id: ");

            Book book = bookService.getBookById(bookId, storageType);

            System.out.println(bookService.getBookDetails(book, storageType));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getBooksByCategory() {
        System.out.println("=======Get books by category id======");

        scanner.nextLine();
        Integer categoryId = InputHandler.getIntInput(scanner, "Enter category id: ");

        List<Book> books = bookService.getBooksByCategory(categoryId, storageType);

        if(books == null || books.isEmpty()) {
            System.out.println("Not books found");
        } else {
            for (Book book : books) {
                System.out.print(bookService.getBookDetails(book, storageType));
                System.out.println();
            }
        }
    }

    private void getBooksByAuthor() {
        System.out.println("=======Get books by author id======");

        scanner.nextLine();
        Integer authorId = InputHandler.getIntInput(scanner, "Enter author id: ");

        List<Book> books = bookService.getBooksByAuthor(authorId, storageType);

        if(books == null || books.isEmpty()) {
            System.out.println("Not books found");
        } else {
            for (Book book : books) {
                System.out.print(bookService.getBookDetails(book, storageType));
                System.out.println();
            }
        }
    }

    private void searchBooksByTitle() {
        System.out.println("=======Search books by title======");

        scanner.nextLine();
        String title = InputHandler.getStringInput(scanner, "Enter book title: ");

        List<Book> books = bookService.searchBooksByTitle(title, storageType);

        if(books == null || books.isEmpty()) {
            System.out.println("Not books found");
        } else {
            for (Book book : books) {
                System.out.print(bookService.getBookDetails(book, storageType));
                System.out.println();
            }
        }
    }

    private void getAllBooks() {
        System.out.println("=======Get all books======");

        List<Book> books = bookService.getAllBooks(storageType);

        if(books == null || books.isEmpty()) {
            System.out.println("Not books found");
        } else {
            for (Book book : books) {
                System.out.print(bookService.getBookDetails(book, storageType));
                System.out.println();
            }
        }
    }

    private void updateBook() {
        System.out.println("=======Update book======");

        try {
            scanner.nextLine();
            Integer bookId = InputHandler.getIntInput(scanner, "Enter book id you want to update: ");

            Book book = bookService.getBookById(bookId, storageType);

            book.setCode(InputHandler.getStringInput(scanner, "Enter book code: "));
            book.setTitle(InputHandler.getStringInput(scanner, "Enter book title: "));
            book.setCategoryId(InputHandler.getIntInput(scanner, "Enter category id: "));
            book.setAuthorId(InputHandler.getIntInput(scanner, "Enter book author id: "));
            book.setQuantity(InputHandler.getIntInput(scanner, "Enter book quantity: "));

            bookService.updateBook(book, storageType);
            System.out.println("Updated book with id: " + bookId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteBook() {
        System.out.println("=======Delete book======");

        try {
            scanner.nextLine();
            Integer bookId = InputHandler.getIntInput(scanner, "Enter book id you want to delete: ");

            bookService.deleteBook(bookId, storageType);
            System.out.println("Deleted book with id: " + bookId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
