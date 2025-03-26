package org.example.Repository.impl;

import org.example.Repository.UserRepository;
import org.example.database.JDBCConnect;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDBImpl implements UserRepository {

    JDBCConnect conn = new JDBCConnect();
    Connection connection = conn.getConnection();

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (full_name, email, phone, address) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());

            int check = ps.executeUpdate();

            if (check > 0) System.out.println("Added new user successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding new user " + e.getMessage());
        }
    }

    @Override
    public User getUserById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        User user = new User();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                user.setId(id);
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user " + e.getMessage());
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";

        List<User> users = new ArrayList<User>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting users " + e.getMessage());
        }

        return users;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET full_name = ?, email = ?, phone = ?, address = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user " + e.getMessage());
        }

    }

    @Override
    public void deleteUser(Integer id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user " + e.getMessage());
        }
    }
}
