package org.example;

import org.example.ui.db.DBMainUI;
import org.example.ui.file.FileMainUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBMainUI dbMainUI = new DBMainUI();
        FileMainUI fileMainUI = new FileMainUI();

        int choice;

        do {
            System.out.println("\n");
            System.out.println("================LIBRARY MANAGEMENT==============");
            System.out.println("Choose storage type: ");
            System.out.println("1. Storage with database");
            System.out.println("2. Storage with file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> dbMainUI.display();
                case 2 -> fileMainUI.display();
                case 3 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 3);
    }
}