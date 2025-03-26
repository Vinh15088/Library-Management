package org.example.Repository.impl;

import org.example.Repository.BookRepository;
import org.example.model.Book;
import org.example.utils.CSVFileUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepositoryFileImpl implements BookRepository {

    private static final String FILE_PATH = "data/books.csv";
    private static final String CSV_HEADER = "id,code,title,category_id,author_id,quantity";

    public BookRepositoryFileImpl() {
        CSVFileUtils.initCSVFile(FILE_PATH, CSV_HEADER);
    }

    @Override
    public void addBook(Book book) {
        List<Book> books = getAllBooks();

        if(books == null) {
            books = new ArrayList<>();
        }

        int newId = CSVFileUtils.getNextId(books, Book::getId);
        book.setId(newId);

        books.add(book);

        saveToFile(books);
    }

    @Override
    public Book getBookById(Integer id) {
        return getAllBooks().stream().filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> getBooksByCategory(Integer categoryId) {
        return getAllBooks().stream().filter(book -> book.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByAuthor(Integer authorId) {
        return getAllBooks().stream().filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        return getAllBooks().stream().filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return CSVFileUtils.readAllFromCSVFile(FILE_PATH, this::parseBookFromCSV);
    }

    @Override
    public void updateBook(Book book) {
        List<Book> books = getAllBooks();
        boolean check = false;

        for(int i = 0; i<books.size(); i++) {
            if(books.get(i).getId().equals(book.getId())) {
                books.set(i, book);
                check = true;
                break;
            }
        }

        if(check) {
            saveToFile(books);
//            System.out.println("Updated book");
        } else {
            System.out.println("Book id not found");
        }

    }

    @Override
    public void deleteBook(Integer id) {
        List<Book> books = getAllBooks();
        boolean check = books.removeIf(book -> book.getId().equals(id));

        if(check) {
            saveToFile(books);
//            System.out.println("Deleted book");
        } else {
            System.out.println("Book id not found");
        }
    }

    private void saveToFile(List<Book> books) {
        CSVFileUtils.saveToCSV(FILE_PATH, CSV_HEADER, books, this::convertBookToCSV);
    }

    private Book parseBookFromCSV(String line) {
        String[] values = line.split(",");

        Book book = new Book();
        book.setId(Integer.parseInt(values[0].trim()));
        book.setCode(values[1].trim());
        book.setTitle(values[2].trim());
        book.setCategoryId(Integer.parseInt(values[3].trim()));
        book.setAuthorId(Integer.parseInt(values[4].trim()));
        book.setQuantity(Integer.parseInt(values[5].trim()));

        return book;
    }

    private String convertBookToCSV(Book book) {
        return String.join(",",
                String.valueOf(book.getId()),
                book.getCode(),
                book.getTitle(),
                String.valueOf(book.getCategoryId()),
                String.valueOf(book.getAuthorId()),
                String.valueOf(book.getQuantity())
        );
    }

}
