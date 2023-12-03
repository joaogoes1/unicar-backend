package com.unicar.ride.controller.request;

import com.google.type.LatLng;
import com.unicar.ride.domain.model.Ride;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        Pattern latitudePattern = Pattern.compile("lat: ([0-9\\.\\-]+)");
        Pattern longitudePattern = Pattern.compile("lng: ([0-9\\.\\-]+)");
        final Matcher latitudeOriginMatcher = latitudePattern.matcher(origin);
        final Matcher longitudeOriginMatcher = longitudePattern.matcher(origin);
        final Matcher latitudeDestinyMatcher = latitudePattern.matcher(origin);
        final Matcher longitudeDestinyMatcher = longitudePattern.matcher(origin);
        LatLng origin = null;
        LatLng destiny = null;
        if (latitudeOriginMatcher.find() && longitudeOriginMatcher.find()) {
            origin = LatLng
                    .newBuilder()
                    .setLatitude(Double.parseDouble(latitudeOriginMatcher.group(1)))
                    .setLongitude(Double.parseDouble(longitudeOriginMatcher.group(1)))
                    .build();
        }
        if (latitudeDestinyMatcher.find() && longitudeDestinyMatcher.find()) {
            destiny = LatLng
                    .newBuilder()
                    .setLatitude(Double.parseDouble(latitudeDestinyMatcher.group(1)))
                    .setLongitude(Double.parseDouble(latitudeDestinyMatcher.group(1)))
                    .build();
        }
        return new Ride(
                null,
                userId,
                origin,
                destiny,
                Date.from(Instant.ofEpochMilli(startMilliseconds)),
                Date.from(Instant.ofEpochMilli(startMilliseconds)),
                availableSeats,
                price,
                List.of()
        );
    }
}
