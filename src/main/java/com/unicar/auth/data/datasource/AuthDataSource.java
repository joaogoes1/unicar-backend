package com.unicar.auth.data.datasource;

import com.unicar.auth.data.model.User;

import java.io.InterruptedIOException;

public interface AuthDataSource {
    User getUserByEmail(String email);
    void createUser(String email, String hash, String name, String phone, String ra) throws InterruptedIOException;
}
