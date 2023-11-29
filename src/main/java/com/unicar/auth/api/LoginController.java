package com.unicar.auth.api;

import com.unicar.auth.domain.LoginService;
import com.unicar.auth.domain.VerifyUserResponse;
import com.unicar.util.router.Controller;

import static com.unicar.util.router.BodyParser.bodyTyped;
import static spark.Spark.halt;
import static spark.Spark.post;

public class LoginController implements Controller {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void register() {
        post("/register", (req, res) -> {
            final LoginRequestBody body = bodyTyped(req, LoginRequestBody.class);
            final String email = body.getEmail();
            final String password = body.getPassword();
            loginService.registerUser(email, password);
            res.status(201);
            return "{}";
        });
    }

    public void login() {
        post("/login", (req, res) -> {
            final LoginRequestBody body = bodyTyped(req, LoginRequestBody.class);
            final String email = body.getEmail();
            final String password = body.getPassword();
            if (email == null || password == null) {
                halt(400, "{\"error\": \"email and/or password empty\"}");
            }
            switch (loginService.verifyUser(email, password)) {
                case VerifyUserResponse.Success response -> {
                    res.status(200);
                    return "{\"token\": \"Bearer " + response.token() + "\"}";
                }
                case VerifyUserResponse.Failure error -> {
                    res.status(401);
                    return "{\"error\": \"" + error.message() + "\"}";
                }
                default -> {
                    res.status(500);
                    return "{\"error\": \" Unexpected error\"}";
                }
            }
        });
    }

    @Override
    public void apis() {
        register();
        login();
    }
}

