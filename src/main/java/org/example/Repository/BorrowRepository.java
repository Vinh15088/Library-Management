package org.example.Repository;

import org.example.model.Borrow;

import java.util.List;

public interface BorrowRepository {

    void addBorrow(Borrow borrow);
    Borrow getBorrowById(Integer id);
    List<Borrow> getBorrowsByUser(Integer userId);
    List<Borrow> getBorrowByBook(Integer bookId);
    List<Borrow> getAllBorrows();
    void updateBorrow(Borrow borrow);
    void deleteBorrow(Integer id);
}
