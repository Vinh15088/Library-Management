package org.example.ui.file;

import org.example.ui.BaseMainUI;

public class FileMainUI extends BaseMainUI {
    public FileMainUI() {
        super("FILE");
    }

    @Override
    protected void handleMenuChoice(int choice) {
        FileBookUI fileBookUI = new FileBookUI();
        FileCategoryUI fileCategoryUI = new FileCategoryUI();
        FileAuthorUI fileAuthorUI = new FileAuthorUI();
        FileUserUI fileUserUI = new FileUserUI();
        FileBorrowUI fileBorrowUI = new FileBorrowUI();

        switch (choice) {
            case 1 -> fileBookUI.showMenu();
            case 2 -> fileCategoryUI.showMenu();
            case 3 -> fileAuthorUI.showMenu();
            case 4 -> fileUserUI.showMenu();
            case 5 -> fileBorrowUI.showMenu();
            case 6 -> {return;}
            default -> System.out.println("Invalid choice!");
        }
    }
}
