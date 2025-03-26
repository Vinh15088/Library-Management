package org.example.service.impl;

import org.example.Repository.BorrowRepository;
import org.example.Repository.impl.BorrowRepositoryDBImpl;
import org.example.Repository.impl.BorrowRepositoryFileImpl;
import org.example.enums.StorageType;
import org.example.model.Book;
import org.example.model.Borrow;
import org.example.model.User;
import org.example.service.BookService;
import org.example.service.BorrowService;
import org.example.service.UserService;

import java.time.LocalDate;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepositoryDB;
    private final BorrowRepository borrowRepositoryFile;
    private final BookService bookService;
    private final UserService userService;

    public BorrowServiceImpl() {
        this.borrowRepositoryDB = new BorrowRepositoryDBImpl();
        this.borrowRepositoryFile = new BorrowRepositoryFileImpl();
        this.bookService = new BookServiceImpl();
        this.userService = new UserServiceImpl();
    }

    private BorrowRepository getRepository(StorageType storageType) {
        return storageType == StorageType.FILE ? this.borrowRepositoryFile : this.borrowRepositoryDB;
    }

    @Override
    public void borrowBook(Integer bookId, Integer userId, StorageType storageType) {
        Book book = bookService.getBookById(bookId, storageType);

        if(book == null) {
            throw new RuntimeException("Book id " + bookId + " not exist");
        }

        // check quantity book available
        if(book.getQuantity() <= 0) {
            throw new RuntimeException("Book with id " + bookId + " is out of stock");
        }

        User user = userService.getUserById(userId, storageType);

        if(user == null) {
            throw new RuntimeException("User id " + userId + " not exist");
        }

        Borrow borrow = new Borrow();
        borrow.setBookId(bookId);
        borrow.setUserId(userId);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(null);

        // decrease quantity book in stock
        book.setQuantity(book.getQuantity() - 1);
        bookService.updateBook(book, storageType);

        getRepository(storageType).addBorrow(borrow);
    }

    @Override
    public void returnBook(Integer borrowId, StorageType storageType) {
        Borrow borrow = getBorrowById(borrowId, storageType);

        if(borrow.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        borrow.setReturnDate(LocalDate.now());
        getRepository(storageType).updateBorrow(borrow);

        // increase quantity book in stock
        Book book = bookService.getBookById(borrow.getBookId(), storageType);
        book.setQuantity(book.getQuantity() + 1);
        bookService.updateBook(book, storageType);
    }

    @Override
    public Borrow getBorrowById(Integer id, StorageType storageType) {
        Borrow borrow = getRepository(storageType).getBorrowById(id);

        if(borrow == null) {
            throw new RuntimeException("Borrow id " + id + " not exist");
        }

        return borrow;
    }

    @Override
    public List<Borrow> getBorrowsByBookId(Integer bookId, StorageType storageType) {
        return getRepository(storageType).getBorrowByBook(bookId);
    }

    @Override
    public List<Borrow> getBorrowsByUserId(Integer userId, StorageType storageType) {
        return getRepository(storageType).getBorrowsByUser(userId);
    }

    @Override
    public List<Borrow> getAllBorrows(StorageType storageType) {
        return getRepository(storageType).getAllBorrows();
    }

    @Override
    public String getBorrowDetails(Borrow borrow, StorageType storageType) {
        Book book = bookService.getBookById(borrow.getBookId(), storageType);
        User user = userService.getUserById(borrow.getUserId(), storageType);

        return borrow.toString(book.getTitle(), user.getFullName());
    }
}
