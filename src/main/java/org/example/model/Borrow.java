package org.example.model;

import java.time.LocalDate;

public class Borrow {

    private Integer id;
    private Integer bookId;
    private Integer userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Borrow() {

    }

    public Borrow(Integer id, Integer bookId, Integer userId, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String toString(String bookTitle, String userFullName) {
        return "Id: " + id +
                " | Book title: " + bookTitle +
                " | By user: " + userFullName +
                " | Borrow date: " + borrowDate +
                " | Return date: " + returnDate;
    }
}
