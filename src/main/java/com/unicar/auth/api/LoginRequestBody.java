package com.unicar.auth.api;

import java.util.Objects;

class LoginRequestBody {
    private final String email;
    private final String password;

    LoginRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequestBody that = (LoginRequestBody) o;
        return email.equals(that.email) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + email.hashCode();
        hash = hash * 31 + password.hashCode();

        if (hash < 0) hash = hash * -1;

        return hash;
    }
}
