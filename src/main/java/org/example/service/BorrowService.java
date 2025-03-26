package org.example.service;

import org.example.enums.StorageType;
import org.example.model.Borrow;

import java.util.List;

public interface BorrowService {

    void borrowBook(Integer bookId, Integer userId, StorageType storageType);
    void returnBook(Integer borrowId, StorageType storageType);
    Borrow getBorrowById(Integer id, StorageType storageType);
    List<Borrow> getBorrowsByBookId(Integer bookId, StorageType storageType);
    List<Borrow> getBorrowsByUserId(Integer userId, StorageType storageType);
    List<Borrow> getAllBorrows(StorageType storageType);
    String getBorrowDetails(Borrow borrow, StorageType storageType);
}
