package com.unicar.auth.domain;

import java.io.InterruptedIOException;

public interface LoginService {
    String hash(String password);

    boolean verifyPassword(String hash, String password);
    VerifyUserResponse verifyUser(String hash, String password);

    RegisterUserResponse registerUser(String email, String password) throws InterruptedIOException;
    String getUserIdFromToken(String token);
}

