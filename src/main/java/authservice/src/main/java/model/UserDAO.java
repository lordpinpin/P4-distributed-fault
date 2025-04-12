package authservice.src.main.java.model;

import authservice.src.main.java.util.DBConnection;

import java.sql.*;

public class UserDAO {

    // Get user by username for login purposes
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Return user details including id, username, password hash, and role
                return new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password_hash"), rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }

    // Other methods for registering users, changing passwords, etc. can be added here
}