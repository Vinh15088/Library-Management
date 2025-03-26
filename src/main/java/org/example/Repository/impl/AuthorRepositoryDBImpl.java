package org.example.Repository.impl;

import org.example.Repository.AuthorRepository;
import org.example.database.JDBCConnect;
import org.example.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryDBImpl implements AuthorRepository {

    JDBCConnect conn = new JDBCConnect();
    Connection connection = conn.getConnection();

    @Override
    public void addAuthor(Author author) {
        String sql = "INSERT INTO authors (name, email, phone) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getEmail());
            ps.setString(3, author.getPhone());

            int check = ps.executeUpdate();

            if(check > 0) System.out.println("Add new author successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding new author " + e.getMessage());
        }
    }

    @Override
    public Author getAuthorById(Integer id) {
        String sql = "SELECT * FROM authors WHERE id = ?";

        Author author = new Author();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                author.setId(id);
                author.setName(rs.getString("name"));
                author.setEmail(rs.getString("email"));
                author.setPhone(rs.getString("phone"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting author " + e.getMessage());
        }

        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        String sql = "SELECT * FROM authors";

        List<Author> authors = new ArrayList<Author>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Author author = new Author();

                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setEmail(rs.getString("email"));
                author.setPhone(rs.getString("phone"));

                authors.add(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting authors " + e.getMessage());
        }

        return authors;
    }

    @Override
    public void updateAuthor(Author author) {
        String sql = "UPDATE authors SET name = ?, email = ?, phone = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getEmail());
            ps.setString(3, author.getPhone());
            ps.setInt(4, author.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating author " + e.getMessage());
        }
    }

    @Override
    public void deleteAuthor(Integer id) {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting author " + e.getMessage());
        }
    }
}
