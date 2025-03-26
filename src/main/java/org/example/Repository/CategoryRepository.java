package org.example.Repository;

import org.example.model.Category;

import java.util.List;

public interface CategoryRepository {
    void addCategory(Category category);
    Category getCategoryById(Integer id);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(Integer id);
}
