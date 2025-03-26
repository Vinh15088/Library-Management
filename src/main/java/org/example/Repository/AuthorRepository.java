package org.example.Repository;

import org.example.model.Author;

import java.util.List;

public interface AuthorRepository {
    void addAuthor(Author author);
    Author getAuthorById(Integer id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthor(Integer id);
}
