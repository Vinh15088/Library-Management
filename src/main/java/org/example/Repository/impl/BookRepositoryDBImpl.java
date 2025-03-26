package org.example.Repository.impl;

import org.example.Repository.BookRepository;
import org.example.database.JDBCConnect;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryDBImpl implements BookRepository {
    JDBCConnect conn = new JDBCConnect();
    Connection connection = conn.getConnection();

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books (code, title, category_id, author_id, quantity) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getCode());
            ps.setString(2, book.getTitle());
            ps.setInt(3, book.getCategoryId());
            ps.setInt(4, book.getAuthorId());
            ps.setInt(5, book.getQuantity());

            int check = ps.executeUpdate();

            if (check > 0) System.out.println("Added successfully");

        } catch (SQLException e) {
            throw new RuntimeException("Error adding a book: " + e.getMessage());
        }
    }

    @Override
    public Book getBookById(Integer id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        Book book = new Book();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                book.setId(id);
                book.setCode(rs.getString("code"));
                book.setTitle(rs.getString("title"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setAuthorId(rs.getInt("author_id"));
                book.setQuantity(rs.getInt("quantity"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting book by id: " + e.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> getBooksByCategory(Integer categoryId) {
        String sql = "SELECT * FROM books WHERE books.category_id = ?";

        List<Book> books = new ArrayList<Book>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setCode(rs.getString("code"));
                book.setTitle(rs.getString("title"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setAuthorId(rs.getInt("author_id"));
                book.setQuantity(rs.getInt("quantity"));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting books with category: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(Integer authorId) {
        String sql = "SELECT * FROM books WHERE books.author_id = ?";

        List<Book> books = new ArrayList<Book>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, authorId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setCode(rs.getString("code"));
                book.setTitle(rs.getString("title"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setAuthorId(rs.getInt("author_id"));
                book.setQuantity(rs.getInt("quantity"));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting books with author: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        String sql = "SELECT * FROM books WHERE books.title LIKE ?";

        List<Book> books = new ArrayList<Book>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + title + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setCode(rs.getString("code"));
                book.setTitle(rs.getString("title"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setAuthorId(rs.getInt("author_id"));
                book.setQuantity(rs.getInt("quantity"));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a book: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";

        List<Book> books = new ArrayList<Book>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setCode(rs.getString("code"));
                book.setTitle(rs.getString("title"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setAuthorId(rs.getInt("author_id"));
                book.setQuantity(rs.getInt("quantity"));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting a book: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET code = ?, title = ?, category_id = ?, author_id = ?, quantity = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getCode());
            ps.setString(2, book.getTitle());
            ps.setInt(3, book.getCategoryId());
            ps.setInt(4, book.getAuthorId());
            ps.setInt(5, book.getQuantity());
            ps.setInt(6, book.getId());

            int check = ps.executeUpdate();

//            if (check > 0) System.out.println("Updated successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(Integer id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int check = ps.executeUpdate();

//            if (check > 0) System.out.println("Deleted successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book: " + e.getMessage());
        }
    }
}
