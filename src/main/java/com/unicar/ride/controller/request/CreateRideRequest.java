package com.unicar.ride.controller.request;

import java.util.Date;

public class CreateRideRequest {
    private final double originLatitude;
    private final double originLongitude;
    private final double destinyLatitude;
    private final double destinyLongitude;
    private final Date startTime;
    private final int availableSeats;
    private final double price;

    public CreateRideRequest(double originLatitude, double originLongitude, double destinyLatitude, double destinyLongitude, Date startTime, int availableSeats, double price) {
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinyLatitude = destinyLatitude;
        this.destinyLongitude = destinyLongitude;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public double getOriginLatitude() {
        return originLatitude;
    }

    public double getOriginLongitude() {
        return originLongitude;
    }

    public double getDestinyLatitude() {
        return destinyLatitude;
    }

    public double getDestinyLongitude() {
        return destinyLongitude;
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
