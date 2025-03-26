package org.example.ui;

import org.example.enums.StorageType;
import org.example.model.Borrow;
import org.example.service.BorrowService;
import org.example.service.impl.BorrowServiceImpl;
import org.example.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class BaseBorrowUI {

    private final BorrowService borrowService;
    private final StorageType storageType;
    private final String storageTypeName;

    public BaseBorrowUI(StorageType storageType, String storageTypeName) {
        this.borrowService = new BorrowServiceImpl();
        this.storageType = storageType;
        this.storageTypeName = storageTypeName;
    }

    Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n");
            System.out.println("==============LIBRARY MANAGEMENT=============");
            System.out.println("-------------STORAGE WITH " + storageTypeName + "-----------");
            System.out.println("---------------BORROW MANAGEMENT---------------");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Get borrow record by id");
            System.out.println("4. Get all borrow record by book");
            System.out.println("5. Get all borrow record by user");
            System.out.println("6. Get all borrow record");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> borrowBook();
                    case 2 -> returnBook();
                    case 3 -> getBorrowRecordById();
                    case 4 -> getAllBorrowRecordByBook();
                    case 5 -> getAllBorrowRecordByUser();
                    case 6 -> getAllBorrowRecord();
                    case 7 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void borrowBook() {
        System.out.println("========Borrow Book========");

        try {
            scanner.nextLine();
            Integer bookId = InputHandler.getIntInput(scanner, "Enter book id: ");
            Integer userId = InputHandler.getIntInput(scanner, "Enter user id: ");

            borrowService.borrowBook(bookId, userId, storageType);
            System.out.println("Borrowed book successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void returnBook() {
        System.out.println("========Return Book========");
        Borrow borrow = new Borrow();

        try {
            scanner.nextLine();
            Integer borrowId = InputHandler.getIntInput(scanner, "Enter borrow id: ");

            borrowService.returnBook(borrowId, storageType);
            System.out.println("Returned book successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void getBorrowRecordById() {
        System.out.println("========Get Borrow record by id========");

        try {
            scanner.nextLine();
            Integer borrowId = InputHandler.getIntInput(scanner, "Enter borrow id: ");

            Borrow borrow = borrowService.getBorrowById(borrowId, storageType);

            System.out.println(borrowService.getBorrowDetails(borrow, storageType));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getAllBorrowRecordByBook() {
        System.out.println("========Get all borrow record by book========");

        scanner.nextLine();
        Integer bookId = InputHandler.getIntInput(scanner, "Enter book id: ");

        List<Borrow> borrows = borrowService.getBorrowsByBookId(bookId, storageType);

        if(borrows == null || borrows.isEmpty()) {
            System.out.println("Not borrow record by book found");
        } else {
            for (Borrow borrow : borrows) {
                System.out.print(borrowService.getBorrowDetails(borrow, storageType));
                System.out.println();
            }
        }
    }

    private void getAllBorrowRecordByUser() {
        System.out.println("========Get all borrow record by user========");

        scanner.nextLine();
        Integer userId = InputHandler.getIntInput(scanner, "Enter user id: ");

        List<Borrow> borrows = borrowService.getBorrowsByUserId(userId, storageType);

        if(borrows == null || borrows.isEmpty()) {
            System.out.println("Not borrow record by user found");
        } else {
            for (Borrow borrow : borrows) {
                System.out.print(borrowService.getBorrowDetails(borrow, storageType));
                System.out.println();
            }
        }
    }

    private void getAllBorrowRecord() {
        System.out.println("========Get all borrow record========");

        List<Borrow> borrows = borrowService.getAllBorrows(storageType);

        if(borrows == null || borrows.isEmpty()) {
            System.out.println("Not borrow record by book found");
        } else {
            for (Borrow borrow : borrows) {
                System.out.print(borrowService.getBorrowDetails(borrow, storageType));
                System.out.println();
            }
        }
    }
}
