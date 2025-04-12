package authservice.src.main.java;

import authservice.src.main.java.controller.AuthController;
import authservice.src.main.java.util.DBConnection;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;

import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        // Test database connection using DBConnection
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database!");
        }

        // Start the Javalin app
        Javalin app = Javalin.create().start(7000);

        // Define the routes for the API
        app.routes(() -> {
            ApiBuilder.get("/", ctx -> ctx.result("Welcome to the Enrollment System"));
            ApiBuilder.post("/login", AuthController::login);  // Login route
            ApiBuilder.get("/protected", AuthController::protectedRoute); // Protected route requiring JWT
            ApiBuilder.post("/logout", AuthController::logout);  // Logout route
        });
    }
}