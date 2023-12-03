package com.unicar.ride.controller.request;

import java.util.Date;

public class CreateRideRequest {
    private final String origin;
    private final String destiny;
    private final Date startTime;
    private final int availableSeats;
    private final double price;

    public CreateRideRequest(String origin, String destiny, Date startTime, int availableSeats, double price) {
        this.origin = origin;
        this.destiny = destiny;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }
}
