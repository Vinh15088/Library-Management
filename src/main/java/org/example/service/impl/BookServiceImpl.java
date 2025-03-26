package org.example.service.impl;

import org.example.Repository.BookRepository;
import org.example.Repository.impl.BookRepositoryDBImpl;
import org.example.Repository.impl.BookRepositoryFileImpl;
import org.example.enums.StorageType;
import org.example.model.Book;
import org.example.service.AuthorService;
import org.example.service.BookService;
import org.example.service.CategoryService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepositoryDB;
    private final BookRepository bookRepositoryFile;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public BookServiceImpl() {
        this.bookRepositoryDB = new BookRepositoryDBImpl();
        this.bookRepositoryFile = new BookRepositoryFileImpl();
        this.categoryService = new CategoryServiceImpl();
        this.authorService = new AuthorServiceImpl();
    }

    private BookRepository getRepository(StorageType storageType) {
        return storageType == StorageType.FILE ? this.bookRepositoryFile : this.bookRepositoryDB;
    }

    @Override
    public void addBook(Book book, StorageType storageType) {
        categoryService.getCategoryById(book.getCategoryId(), storageType);

        authorService.getAuthorById(book.getAuthorId(), storageType);

        getRepository(storageType).addBook(book);
    }

    @Override
    public Book getBookById(Integer id, StorageType storageType) {
        Book book =  getRepository(storageType).getBookById(id);

        if(book == null) {
            throw new RuntimeException("Book id " + id + " not exist");
        }

        return book;
    }

    @Override
    public List<Book> getBooksByCategory(Integer categoryId, StorageType storageType) {
        return getRepository(storageType).getBooksByCategory(categoryId);
    }

    @Override
    public List<Book> getBooksByAuthor(Integer authorId, StorageType storageType) {
        return getRepository(storageType).getBooksByAuthor(authorId);
    }

    @Override
    public List<Book> searchBooksByTitle(String title, StorageType storageType) {
        return getRepository(storageType).searchBookByTitle(title);
    }

    @Override
    public List<Book> getAllBooks(StorageType storageType) {
        return getRepository(storageType).getAllBooks();
    }

    @Override
    public void updateBook(Book book, StorageType storageType) {
        getBookById(book.getId(), storageType);

        categoryService.getCategoryById(book.getCategoryId(), storageType);

        authorService.getAuthorById(book.getAuthorId(), storageType);

        getRepository(storageType).updateBook(book);
    }

    @Override
    public void deleteBook(Integer id, StorageType storageType) {
        getBookById(id, storageType);

        getRepository(storageType).deleteBook(id);
    }

    @Override
    public String getBookDetails(Book book, StorageType storageType) {
        String categoryName = categoryService.getCategoryById(book.getCategoryId(), storageType).getName();
        String authorName = authorService.getAuthorById(book.getAuthorId(), storageType).getName();

        return book.toString(categoryName, authorName);
    }
}
