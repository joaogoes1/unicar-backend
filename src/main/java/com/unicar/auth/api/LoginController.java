package com.unicar.auth.api;

import com.unicar.auth.api.request.LoginRequestBody;
import com.unicar.auth.api.request.RegisterRequestBody;
import com.unicar.auth.domain.DomainNotAllowedException;
import com.unicar.auth.domain.LoginService;
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
            final RegisterRequestBody body = bodyTyped(req, RegisterRequestBody.class);
            final String email = body.getEmail();
            final String password = body.getPassword();
            final String name = body.getName();
            final String phone = body.getPhone();
            final String ra = body.getRa();

            try {
                loginService.registerUser(email, password, name, phone, ra);
                res.status(201);
            } catch (IllegalStateException | DomainNotAllowedException e) {
                halt(400, "{\"message\": \"" + e.getMessage() + "\"}");
            } catch (Exception e) {
                halt(500, "{\"message\": \"internal server error\"}");
            }
            return "";
        });
    }

    public void login() {
        post("/login", (req, res) -> {
            final LoginRequestBody body = bodyTyped(req, LoginRequestBody.class);
            final String email = body.getEmail();
            final String password = body.getPassword();
            if (email == null || password == null) {
                halt(400, "{\"message\": \"email and/or password empty\"}");
            }
            final String token = loginService.verifyUser(email, password);
            if (token != null) {
                    res.status(200);
                    return "{\"token\": \"Bearer " + token + "\"}";
            } else {
                halt(401, "{\"message\": \"email and/or password incorrect\"}");
            }
            return "{}";
        });
    }

    @Override
    public void apis() {
        register();
        login();
    }
}

