package org.example.Repository.impl;

import org.example.Repository.CategoryRepository;
import org.example.database.JDBCConnect;
import org.example.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryDBImpl implements CategoryRepository {

    JDBCConnect conn = new JDBCConnect();
    Connection connection = conn.getConnection();

    @Override
    public void addCategory(Category category) {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());

            int check = ps.executeUpdate();

            if(check > 0) System.out.println("Added new category successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding new category " + e.getMessage());
        }
    }

    @Override
    public Category getCategoryById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";

        Category category = new Category();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                category.setId(id);
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting category " + e.getMessage());
        }

        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories";

        List<Category> categories = new ArrayList<Category>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Category category = new Category();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));

                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting categories " + e.getMessage());
        }

        return categories;
    }

    @Override
    public void updateCategory(Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating category " + e.getMessage());
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category " + e.getMessage());
        }
    }
}
