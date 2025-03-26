package org.example.Repository.impl;

import org.example.Repository.CategoryRepository;
import org.example.model.Category;
import org.example.utils.CSVFileUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryFileImpl implements CategoryRepository {

    private static final String FILE_PATH = "data/categories.csv";
    private static final String CSV_HEADER = "id,name,description";

    public CategoryRepositoryFileImpl() {
        CSVFileUtils.initCSVFile(FILE_PATH, CSV_HEADER);
    }

    @Override
    public void addCategory(Category category) {
        List<Category> categories = getAllCategories();

        if(categories == null) {
            categories = new ArrayList<>();
        }

        int newId = CSVFileUtils.getNextId(categories, Category::getId);
        category.setId(newId);

        categories.add(category);

        saveToFile(categories);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return getAllCategories().stream().filter(category -> category.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return CSVFileUtils.readAllFromCSVFile(FILE_PATH, this::parseCategoryFromCSV);
    }

    @Override
    public void updateCategory(Category category) {
        List<Category> categories = getAllCategories();
        boolean check = false;

        for(int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId().equals(category.getId())) {
                categories.set(i, category);
                check = true;
                break;
            }
        }

        if(check) {
            saveToFile(categories);
        } else {
            System.out.println("Category id not found");
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        List<Category> categories = getAllCategories();
        boolean check = categories.removeIf(category -> category.getId().equals(id));

        if(check) {
            saveToFile(categories);
        } else {
            System.out.println("CategoryId not found");
        }
    }

    private void saveToFile(List<Category> categories) {
        CSVFileUtils.saveToCSV(FILE_PATH, CSV_HEADER, categories, this::convertCategoryToCSV);
    }

    private String convertCategoryToCSV(Category category) {
        return String.join(",",
                String.valueOf(category.getId()),
                category.getName(),
                category.getDescription()
        );
    }

    private Category parseCategoryFromCSV(String line) {
        String[] values = line.split(",");

        Category category = new Category();
        category.setId(Integer.parseInt(values[0].trim()));
        category.setName(values[1].trim());
        category.setDescription(values[2].trim());

        return category;
    }
}
