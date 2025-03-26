package org.example.service;

import org.example.enums.StorageType;
import org.example.model.Author;

import java.util.List;

public interface AuthorService {

    void addAuthor(Author author, StorageType storageType);
    Author getAuthorById(Integer id, StorageType storageType);
    List<Author> getAllAuthors(StorageType storageType);
    void updateAuthor(Author author, StorageType storageType);
    void deleteAuthor(Integer id, StorageType storageType);
}
