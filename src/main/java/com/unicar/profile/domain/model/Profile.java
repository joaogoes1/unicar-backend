package com.unicar.profile.domain.model;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private String name;
    private String imagePath;
    private String email;
    private String ra;
    private String phone;
    private String university;
    private String age;
    private String driverLicense;
    private Car car;

    public Profile(String name, String imagePath, String email, String ra, String phone, String university, String age, String driverLicense, Car car) {
        this.name = name;
        this.imagePath = imagePath;
        this.email = email;
        this.ra = ra;
        this.phone = phone;
        this.university = university;
        this.age = age;
        this.driverLicense = driverLicense;
        this.car = car;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equal(name, profile.name) &&
                Objects.equal(email, profile.email) &&
                Objects.equal(ra, profile.ra) &&
                Objects.equal(phone, profile.phone) &&
                Objects.equal(university, profile.university) &&
                Objects.equal(age, profile.age) &&
                Objects.equal(driverLicense, profile.driverLicense) &&
                Objects.equal(imagePath, profile.imagePath) &&
                Objects.equal(car, profile.car);
    }

    @Override
    public int hashCode() {
        int ret = 31;
        ret = 31 * ret + (name != null ? name.hashCode() : 0);
        ret = 31 * ret + (email != null ? email.hashCode() : 0);
        ret = 31 * ret + (ra != null ? ra.hashCode() : 0);
        ret = 31 * ret + (phone != null ? phone.hashCode() : 0);
        ret = 31 * ret + (university != null ? university.hashCode() : 0);
        ret = 31 * ret + (age != null ? age.hashCode() : 0);
        ret = 31 * ret + (driverLicense != null ? driverLicense.hashCode() : 0);
        ret = 31 * ret + (imagePath != null ? imagePath.hashCode() : 0);
        ret = 31 * ret + (car != null ? car.hashCode() : 0);

        if (ret < 0) ret = -ret;

        return ret;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ra='" + ra + '\'' +
                ", phone='" + phone + '\'' +
                ", university='" + university + '\'' +
                ", age='" + age + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", car=" + car +
                '}';
    }

    public Map<String, Object> toJson() {
        final Map<String, Object> json = new HashMap<>();
        if (name != null) json.put("name", name);
        if (email != null) json.put("email", email);
        if (ra != null) json.put("ra", ra);
        if (phone != null) json.put("phone", phone);
        if (university != null) json.put("university", university);
        if (age != null) json.put("age", age);
        if (car != null) json.put("car", car.toJson());
        return json;
    }
}
