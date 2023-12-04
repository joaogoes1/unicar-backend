package com.unicar.ride.controller.request;

public class AddPassengerRequest {
    private final String rideId;
    private final String passengerId;

    private final String departurePlace;

    public AddPassengerRequest(String rideId, String passengerId, String departurePlace) {
        this.rideId = rideId;
        this.passengerId = passengerId;
        this.departurePlace = departurePlace;
    }

    public String getRideId() {
        return rideId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }
}
