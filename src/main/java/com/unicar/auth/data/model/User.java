package com.unicar.auth.data.model;

public class User {
    private final String email;
    private final String userId;
    private final String password;

    public User(String email, String userId, String password) {
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && password.equals(user.password);
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
