package com.unicar.auth.domain;

import java.io.InterruptedIOException;

public interface LoginService {
    String hash(String password);

    boolean verifyPassword(String hash, String password);
    String verifyUser(String hash, String password);

    void registerUser(String email, String password, String name, String phone, String ra) throws InterruptedIOException, DomainNotAllowedException;
    String getUserIdFromToken(String token);
}

