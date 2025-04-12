package authservice.src.main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/auth_db"; // Change DB URL as needed
    private static final String USER = "db_user"; // Database user
    private static final String PASSWORD = "db_password"; // Database password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}