package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {

    private static final String url = "jdbc:mysql://localhost:3306/library_management";
    private static final String username = "root";
    private static final String password = "Vl121620.";

    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

//            System.out.println("Connected to database");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to database");

            throw new RuntimeException("Error connection: ", e);
        }

        return conn;
    }

    public void closeConnection(Connection conn) {
        try {
            conn.close();

            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Error closing connection");

            throw new RuntimeException("Error closing connection: ", e);
        }
    }

}
