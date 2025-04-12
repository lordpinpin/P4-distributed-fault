package courseservice.src.main.java.controller;

import courseservice.src.main.java.model.Course;
import courseservice.src.main.java.model.CourseDAO;
import io.javalin.http.Context;

import java.util.List;

public class CourseController {
    private static CourseDAO courseDAO = new CourseDAO();

    // Get all available courses (requires token verification)
    public static void getAllCourses(Context ctx) {
        List<Course> courses = courseDAO.getAllCourses();
        ctx.json(courses);
    }
}