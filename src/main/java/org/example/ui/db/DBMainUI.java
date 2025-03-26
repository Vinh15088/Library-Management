package org.example.ui.db;

import org.example.ui.BaseMainUI;

public class DBMainUI extends BaseMainUI {
    public DBMainUI() {
        super("DATABASE");
    }

    @Override
    protected void handleMenuChoice(int choice) {
        DBBookUI dbBookUI = new DBBookUI();
        DBCategoryUI dbCategoryUI = new DBCategoryUI();
        DBAuthorUI dbAuthorUI = new DBAuthorUI();
        DBUserUI dbUserUI = new DBUserUI();
        DBBorrowUI dbBorrowUI = new DBBorrowUI();

        switch (choice) {
            case 1 -> dbBookUI.showMenu();
            case 2 -> dbCategoryUI.showMenu();
            case 3 -> dbAuthorUI.showMenu();
            case 4 -> dbUserUI.showMenu();
            case 5 -> dbBorrowUI.showMenu();
            case 6 -> {return;}
            default -> System.out.println("Invalid choice!");
        }
    }
}
