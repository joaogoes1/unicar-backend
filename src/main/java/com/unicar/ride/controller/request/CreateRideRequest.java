package com.unicar.ride.controller.request;

import com.google.api.client.util.DateTime;
import com.google.cloud.Timestamp;
import com.unicar.ride.domain.model.Ride;
import com.unicar.util.parsers.LatLngStringToLatLng;

import java.time.Instant;
import java.util.Date;
import java.util.List;


public class CreateRideRequest {
    private final String origin;
    private final String destiny;
    private final int startMilliseconds;
    private final int availableSeats;
    private final double price;

    public CreateRideRequest(String origin, String destiny, int startMilliseconds, int availableSeats, double price) {
        this.origin = origin;
        this.destiny = destiny;
        this.startMilliseconds = startMilliseconds;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public int getStartMilliseconds() {
        return startMilliseconds;
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
                new Date(startMilliseconds),
                availableSeats,
                price,
                List.of()
        );
    }
}
