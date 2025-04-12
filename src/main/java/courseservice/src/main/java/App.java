package courseservice.src.main.java;

import courseservice.src.main.java.controller.CourseController;
import courseservice.src.main.java.util.JWTUtil;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;

import com.auth0.jwt.interfaces.DecodedJWT;

public class App {

    public static void main(String[] args) {

        // Start Javalin server
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public"); // Serve from src/main/resources/public
        }).start(7002);

        // JWT Middleware for protecting routes
        Handler jwtMiddleware = ctx -> {
            String authHeader = ctx.header("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new UnauthorizedResponse("Missing or invalid Authorization header");
            }

            String token = authHeader.replace("Bearer ", "");
            DecodedJWT jwt = JWTUtil.verifyToken(token);
            ctx.attribute("username", jwt.getClaim("username").asString());
            ctx.attribute("role", jwt.getClaim("role").asString());
        };

        // Register routes using ApiBuilder
        app.routes(() -> {
            ApiBuilder.before("/api/*", jwtMiddleware); // Apply middleware to all /api/* routes

            ApiBuilder.path("api", () -> {
                ApiBuilder.get("courses", CourseController::getAllCourses);
                // You can add more here: post("enroll", CourseController::enrollInCourse), etc.
            });

            // Public route
            ApiBuilder.get("/", ctx -> ctx.redirect("/index.html"));
        });
    }
}