package org.example.Repository.impl;

import org.example.Repository.BorrowRepository;
import org.example.database.JDBCConnect;
import org.example.model.Borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryDBImpl implements BorrowRepository {

    JDBCConnect conn = new JDBCConnect();
    Connection connection = conn.getConnection();

    @Override
    public void addBorrow(Borrow borrow) {
        String sql = "INSERT INTO borrows (book_id, user_id, borrow_date) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrow.getBookId());
            ps.setInt(2, borrow.getUserId());
            ps.setString(3, borrow.getBorrowDate().toString());

            int check = ps.executeUpdate();

            if(check > 0) System.out.println("Added new borrow successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding new borrow" + e.getMessage());
        }
    }

    @Override
    public Borrow getBorrowById(Integer id) {
        String sql = "SELECT * FROM borrows WHERE id = ?";

        Borrow borrow = new Borrow();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                borrow.setId(id);
                borrow.setBookId(rs.getInt("book_id"));
                borrow.setUserId(rs.getInt("user_id"));
                borrow.setBorrowDate(LocalDate.parse(rs.getString("borrow_date")));

                String returnDate = rs.getString("return_date");
                if(returnDate != null && !returnDate.isEmpty()) {
                    borrow.setReturnDate(LocalDate.parse(returnDate));
                } else {
                    borrow.setReturnDate(null);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting borrow by id" + e.getMessage());
        }

        return borrow;
    }

    @Override
    public List<Borrow> getBorrowsByUser(Integer userId) {
        String sql = "SELECT * FROM borrows WHERE borrows.user_id = ?";

        List<Borrow> borrows = new ArrayList<Borrow>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Borrow borrow = new Borrow();

                borrow.setId(rs.getInt("id"));
                borrow.setBookId(rs.getInt("book_id"));
                borrow.setUserId(rs.getInt("user_id"));
                borrow.setBorrowDate(LocalDate.parse(rs.getString("borrow_date")));

                String returnDate = rs.getString("return_date");
                if(returnDate != null && !returnDate.isEmpty()) {
                    borrow.setReturnDate(LocalDate.parse(returnDate));
                } else {
                    borrow.setReturnDate(null);
                }

                borrows.add(borrow);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting borrows by userId" + e.getMessage());
        }

        return borrows;
    }

    @Override
    public List<Borrow> getBorrowByBook(Integer bookId) {
        String sql = "SELECT * FROM borrows WHERE borrows.book_id = ?";

        List<Borrow> borrows = new ArrayList<Borrow>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Borrow borrow = new Borrow();

                borrow.setId(rs.getInt("id"));
                borrow.setBookId(rs.getInt("book_id"));
                borrow.setUserId(rs.getInt("user_id"));
                borrow.setBorrowDate(LocalDate.parse(rs.getString("borrow_date")));

                String returnDate = rs.getString("return_date");
                if(returnDate != null && !returnDate.isEmpty()) {
                    borrow.setReturnDate(LocalDate.parse(returnDate));
                } else {
                    borrow.setReturnDate(null);
                }

                borrows.add(borrow);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting borrows by bookId" + e.getMessage());
        }

        return borrows;
    }

    @Override
    public List<Borrow> getAllBorrows() {
        String sql = "SELECT * FROM borrows";

        List<Borrow> borrows = new ArrayList<Borrow>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Borrow borrow = new Borrow();

                borrow.setId(rs.getInt("id"));
                borrow.setBookId(rs.getInt("book_id"));
                borrow.setUserId(rs.getInt("user_id"));
                borrow.setBorrowDate(LocalDate.parse(rs.getString("borrow_date")));

                String returnDate = rs.getString("return_date");
                if(returnDate != null && !returnDate.isEmpty()) {
                    borrow.setReturnDate(LocalDate.parse(returnDate));
                } else {
                    borrow.setReturnDate(null);
                }

                borrows.add(borrow);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all borrows" + e.getMessage());
        }

        return borrows;
    }

    @Override
    public void updateBorrow(Borrow borrow) {
        String sql = "UPDATE borrows SET book_id = ?, user_id = ?, borrow_date = ?, return_date = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrow.getBookId());
            ps.setInt(2, borrow.getUserId());
            ps.setString(3, borrow.getBorrowDate().toString());
            ps.setString(4, borrow.getReturnDate().toString());
            ps.setInt(5, borrow.getId());

            int check = ps.executeUpdate();

//            if(check > 0) System.out.println("Updated borrow successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating borrow" + e.getMessage());
        }
    }

    @Override
    public void deleteBorrow(Integer id) {
        String sql = "DELETE FROM borrows WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int check = ps.executeUpdate();

            if(check > 0) System.out.println("Deleted borrow successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting borrow" + e.getMessage());
        }
    }
}
