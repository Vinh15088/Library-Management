package org.example.utils;

import java.util.Scanner;

public class InputHandler {

    public static String getStringInput(Scanner scanner, String line) {
        System.out.print(line);
        return scanner.nextLine().trim();
    }

    public static int getIntInput(Scanner scanner, String line) {
        System.out.print(line);
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
