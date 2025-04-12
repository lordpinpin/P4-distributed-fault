
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;

import controller.AuthController;
import util.JWTUtil;
import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost()));
        }).start(7000);

        Handler jwtMiddleware = ctx -> {
            String authHeader = ctx.header("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new UnauthorizedResponse("Missing or invalid Authorization header");
            }
            try {
                JWTUtil.verifyToken(authHeader.replace("Bearer ", ""));
            } catch (Exception e) {
                throw new UnauthorizedResponse("Invalid or expired token");
            }
        };

        app.before("/protected", jwtMiddleware);

        // Use the new routing DSL
        app.routes(() -> {
            post("/login", AuthController::login);
            get("/protected", AuthController::protectedRoute);
        });
    }
}