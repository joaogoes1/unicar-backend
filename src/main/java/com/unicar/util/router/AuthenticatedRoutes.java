package com.unicar.util.router;

import com.unicar.auth.domain.LoginService;
import spark.Request;
import spark.Route;

import static com.unicar.auth.domain.LoginServiceImpl.verifyToken;
import static spark.Spark.*;
import static spark.Spark.halt;

public class AuthenticatedRoutes {

    public static void getAuthenticated(final String path, final Route route) {
        verifyAuthentication(path);
        get(path, route);
    }

    public static void postAuthenticated(final String path, final Route route) {
        verifyAuthentication(path);
        post(path, route);
    }

    public static void putAuthenticated(final String path, final Route route) {
        verifyAuthentication(path);
        put(path, route);
    }

    public static void deleteAuthenticated(final String path, final Route route) {
        verifyAuthentication(path);
        delete(path, route);
    }

    public static String getUserId(Request req, LoginService loginService) {
        final String token = req.headers("Authorization").replace("Bearer ", "");
        return loginService.getUserIdFromToken(token);
    }

    private static void verifyAuthentication(String path) {
        before(path, (req, res) -> {
            String token = req.headers("Authorization");
            if (token == null || !verifyToken(token)) {
                halt(401, "Acesso não autorizado.");
            }
        });
    }
}