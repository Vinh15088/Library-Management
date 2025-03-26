package org.example.ui;

import org.example.enums.StorageType;
import org.example.model.Category;
import org.example.service.CategoryService;
import org.example.service.impl.CategoryServiceImpl;
import org.example.utils.InputHandler;

import java.util.List;
import java.util.Scanner;

public class BaseCategoryUI {

    private final CategoryService categoryService;
    private final StorageType storageType;
    private final String storageTypeName;

    public BaseCategoryUI(StorageType storageType, String storageTypeName) {
        this.categoryService = new CategoryServiceImpl();
        this.storageType = storageType;
        this.storageTypeName = storageTypeName;
    }

    Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n");
            System.out.println("==============LIBRARY MANAGEMENT=============");
            System.out.println("-------------STORAGE WITH " + storageTypeName + "-----------");
            System.out.println("---------------CATEGORY MANAGEMENT---------------");
            System.out.println("1. Add new category");
            System.out.println("2. Get category by id");
            System.out.println("3. Get all categories");
            System.out.println("4. Update category by id");
            System.out.println("5. Delete category by id");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            try {
                switch(choice) {
                    case 1 -> addCategory();
                    case 2 -> getCategoryById();
                    case 3 -> getAllCategories();
                    case 4 -> updateCategory();
                    case 5 -> deleteCategory();
                    case 6 -> {return;}
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addCategory() {
        System.out.println("======Add new category======");
        Category category = new Category();

        scanner.nextLine();
        category.setName(InputHandler.getStringInput(scanner, "Enter category name: "));
        category.setDescription(InputHandler.getStringInput(scanner, "Enter category description: "));

        categoryService.addCategory(category, storageType);
        System.out.println("Added new category");
    }

    private void getCategoryById() {
        System.out.println("======Get category by id======");

        try {
            scanner.nextLine();
            Integer categoryId = InputHandler.getIntInput(scanner, "Enter category id: ");

            Category category = categoryService.getCategoryById(categoryId, storageType);

            System.out.println(category.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getAllCategories() {
        System.out.println("======Get all categories======");

        List<Category> categories = categoryService.getAllCategories(storageType);

        if(categories == null || categories.isEmpty()) {
            System.out.println("No categories found");
        } else {
            for(Category category : categories) {
                System.out.print(category.toString());
                System.out.println();
            }
        }
    }

    private void updateCategory() {
        System.out.println("======Update category======");

        try {
            scanner.nextLine();
            Integer categoryId = InputHandler.getIntInput(scanner, "Enter category id you want to update: ");

            Category category = categoryService.getCategoryById(categoryId, storageType);

            category.setName(InputHandler.getStringInput(scanner, "Enter category name: "));
            category.setDescription(InputHandler.getStringInput(scanner, "Enter category description: "));

            categoryService.updateCategory(category, storageType);
            System.out.println("Updated category with id: " + categoryId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteCategory() {
        System.out.println("======Delete category======");

        try {
            scanner.nextLine();
            Integer categoryId = InputHandler.getIntInput(scanner, "Enter category id you want to delete: ");

            categoryService.deleteCategory(categoryId, storageType);
            System.out.println("Deleted category with id: " + categoryId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
