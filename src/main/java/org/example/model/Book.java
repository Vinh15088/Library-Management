package org.example.model;

public class Book {

    private Integer id;
    private String code;
    private String title;
    private Integer categoryId;
    private Integer authorId;
    private int quantity;

    public Book() {
    }

    public Book(Integer id, String code, String title, Integer categoryId, Integer authorId, int quantity) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString(String categoryName, String authorName) {
        return "Id: " + id +
                " | Code: " + code +
                " | Title: " + title +
                " | Category: " + categoryName +
                " | Author: " + authorName +
                " | Quantity: " + quantity;
    }
}
