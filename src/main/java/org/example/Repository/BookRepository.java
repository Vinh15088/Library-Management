package org.example.Repository;

import org.example.model.Book;

import java.util.List;

public interface BookRepository {
    void addBook(Book book);
    Book getBookById(Integer id);
    List<Book> getBooksByCategory(Integer categoryId);
    List<Book> getBooksByAuthor(Integer authorId);
    List<Book> searchBookByTitle(String title);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(Integer id);
}
