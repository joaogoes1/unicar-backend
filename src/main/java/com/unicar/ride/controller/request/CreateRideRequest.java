package com.unicar.ride.controller.request;

import com.unicar.ride.domain.model.Ride;
import com.unicar.util.parsers.LatLngStringToLatLng;

import java.util.Date;
import java.util.List;


public class CreateRideRequest {
    private final String origin;
    private final String destiny;
    private final long startTime;
    private final int availableSeats;
    private final double price;

    public CreateRideRequest(String origin, String destiny, long startTime, int availableSeats, double price) {
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

    public long getStartTime() {
        return startTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public Ride toRide(String userId) {
        return new Ride(
                null,
                userId,
                LatLngStringToLatLng.parse(origin),
                LatLngStringToLatLng.parse(destiny),
                new Date(startTime),
                availableSeats,
                price,
                List.of()
        );
    }
}
