package authservice.src.main.java.controller;

import authservice.src.main.java.util.JWTUtil;
import authservice.src.main.java.model.UserDAO;
import authservice.src.main.java.model.User;
import io.javalin.http.Context;

public class AuthController {

    private static UserDAO authDAO = new UserDAO();

    // Handle user login and token generation
    public static void login(Context ctx) {
        // Get the username and password from the request body
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        if (username == null || password == null) {
            ctx.status(400).result("Missing username or password");
            return;
        }

        // Get the user by username from the database
        User user = authDAO.getUserByUsername(username);

        if (user == null) {
            ctx.status(401).result("Invalid username or password");
            return;
        }

        // Check the password (plain text comparison)
        if (!user.getPassword().equals(password)) {
            ctx.status(401).result("Invalid username or password");
            return;
        }

        // Generate JWT token
        String token = JWTUtil.generateToken(user.getUsername(), user.getRole());

        // Send the token as the response
        ctx.status(200).json("{ \"token\": \"" + token + "\" }");
    }

    // Handle user logout (invalidate JWT - in this case, it's client-side only)
    public static void logout(Context ctx) {
        // Simply remove the token from the client side; nothing to do on the server
        ctx.status(200).result("Logged out successfully");
    }

    // Protected route that requires a valid token (JWT)
    public static void protectedRoute(Context ctx) {
        // If the JWT is valid, this route is accessible
        ctx.status(200).result("You have access to this protected route.");
    }
}