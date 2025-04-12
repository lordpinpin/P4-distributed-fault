package courseservice.src.main.java.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import courseservice.src.main.java.util.DBConnection;

public class CourseDAO {

    // Get all available courses from the courses table
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";  // Simple query to fetch all courses
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Map each result row to a Course object
                Course course = new Course(rs.getInt("id"),
                        rs.getString("name"), rs.getString("description"), rs.getInt("credits"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses; // Return the list of courses
    }

    // Get a course by its ID
    public Course getCourseById(int id) {
        String query = "SELECT * FROM courses WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Return the course object
                return new Course(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getInt("credits"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Course not found
    }

    // Additional methods to add, update, or delete courses can be added here
}