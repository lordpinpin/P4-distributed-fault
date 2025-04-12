package controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.javalin.http.Context;
import util.JWTUtil;
import model.User;

import java.util.Map;

public class AuthController {

    // For demo purposes. Replace with real DB query later.
    private static final User dummyUser = new User("student", "pass123", "student");

    public static void login(Context ctx) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        if (username == null || password == null) {
            ctx.status(400).result("Username and password required.");
            return;
        }

        if (username.equals(dummyUser.getUsername()) && password.equals(dummyUser.getPassword())) {
            String token = JWTUtil.generateToken(dummyUser.getUsername(), dummyUser.getRole());
            ctx.json(Map.of("token", token));
        } else {
            ctx.status(401).result("Invalid credentials.");
        }
    }

    public static void protectedRoute(Context ctx) {
        DecodedJWT jwt = JWTUtil.verifyToken(ctx.header("Authorization").replace("Bearer ", ""));
        ctx.result("Hello, " + jwt.getClaim("username").asString() + "! Your role is: " + jwt.getClaim("role").asString());
    }
}