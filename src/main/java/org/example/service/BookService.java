package org.example.service;


import org.example.enums.StorageType;
import org.example.model.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book, StorageType storageType);
    Book getBookById(Integer id, StorageType storageType);
    List<Book> getBooksByCategory(Integer categoryId, StorageType storageType);
    List<Book> getBooksByAuthor(Integer authorId, StorageType storageType);
    List<Book> searchBooksByTitle(String title, StorageType storageType);
    List<Book> getAllBooks(StorageType storageType);
    void updateBook(Book book, StorageType storageType);
    void deleteBook(Integer id, StorageType storageType);
    String getBookDetails(Book book, StorageType storageType);
}
