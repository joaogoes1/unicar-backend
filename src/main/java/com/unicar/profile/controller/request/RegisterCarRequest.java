package com.unicar.profile.controller.request;

public class RegisterCarRequest {
    private final String model;
    private final String color;
    private final String plate;
    private final String driverLicense;

    public RegisterCarRequest(String model, String color, String plate, String driverLicense) {
        this.model = model;
        this.color = color;
        this.plate = plate;
        this.driverLicense = driverLicense;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getPlate() {
        return plate;
    }

    public String getDriverLicense() {
        return driverLicense;
    }
}
