package org.example.service.impl;

import org.example.Repository.CategoryRepository;
import org.example.Repository.impl.CategoryRepositoryDBImpl;
import org.example.Repository.impl.CategoryRepositoryFileImpl;
import org.example.enums.StorageType;
import org.example.model.Category;
import org.example.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepositoryDB;
    private final CategoryRepository categoryRepositoryFile;

    public CategoryServiceImpl() {
        this.categoryRepositoryDB = new CategoryRepositoryDBImpl();
        this.categoryRepositoryFile = new CategoryRepositoryFileImpl();
    }

    private CategoryRepository getRepository(StorageType storageType) {
        return storageType == StorageType.FILE ? this.categoryRepositoryFile : this.categoryRepositoryDB;
    }

    @Override
    public void addCategory(Category category, StorageType storageType) {
        getRepository(storageType).addCategory(category);
    }

    @Override
    public Category getCategoryById(Integer id, StorageType storageType) {
        Category category = getRepository(storageType).getCategoryById(id);

        if(category == null) {
            throw new RuntimeException("Category id " + id + " not exist");
        }

        return category;
    }

    @Override
    public List<Category> getAllCategories(StorageType storageType) {
        return getRepository(storageType).getAllCategories();
    }

    @Override
    public void updateCategory(Category category, StorageType storageType) {
        getCategoryById(category.getId(), storageType);

        getRepository(storageType).updateCategory(category);
    }

    @Override
    public void deleteCategory(Integer id, StorageType storageType) {
        getCategoryById(id, storageType);

        getRepository(storageType).deleteCategory(id);
    }
}
