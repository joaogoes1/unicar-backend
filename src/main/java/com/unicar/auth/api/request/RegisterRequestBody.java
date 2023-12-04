package com.unicar.auth.api.request;

public class RegisterRequestBody {
    private final String email;

    private final String password;

    private final String name;

    private final String phone;

    private final String ra;

    RegisterRequestBody(String email, String password, String name, String phone, String ra) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRa() {
        return ra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterRequestBody that = (RegisterRequestBody) o;
        return email.equals(that.email) &&
                password.equals(that.password) &&
                name.equals(that.name) &&
                phone.equals(that.phone) &&
                ra.equals(that.ra);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + email.hashCode();
        hash = hash * 31 + password.hashCode();
        hash = hash * 31 + name.hashCode();
        hash = hash * 31 + phone.hashCode();
        hash = hash * 31 + ra.hashCode();

        if (hash < 0) hash = hash * -1;

        return hash;
    }
}
