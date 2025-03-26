package org.example.service;

import org.example.enums.StorageType;
import org.example.model.Category;

import java.util.List;

public interface CategoryService {

    void addCategory(Category category, StorageType storageType);
    Category getCategoryById(Integer id, StorageType storageType);
    List<Category> getAllCategories(StorageType storageType);
    void updateCategory(Category category, StorageType storageType);
    void deleteCategory(Integer id, StorageType storageType);
}
