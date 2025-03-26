package org.example.ui;

import org.example.ui.db.DBBookUI;

import java.util.Scanner;

public abstract class BaseMainUI {

    private final String storageTypeName;

    public BaseMainUI(String storageTypeName) {
        this.storageTypeName = storageTypeName;
    }

    Scanner scanner = new Scanner(System.in);

    public void display() {
        DBBookUI bookUI = new DBBookUI();

        int choice;

        do {
            System.out.println("\n");
            System.out.println("===============LIBRARY MANAGEMENT=============");
            System.out.println("------------STORAGE WITH " + storageTypeName + "-----------");
            System.out.println("1. Book management");
            System.out.println("2. Category management");
            System.out.println("3. Author management");
            System.out.println("4. User management");
            System.out.println("5. Borrowed/Return management");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            handleMenuChoice(choice);
        } while (choice != 6);
    }

    protected abstract void handleMenuChoice(int choice);
}
