package com.unicar.ride.controller.request;

public class RemovePassengerRequest {
    private final String rideId;
    private final String passengerId;

    public RemovePassengerRequest(String rideId, String passengerId) {
        this.rideId = rideId;
        this.passengerId = passengerId;
    }

    public String getRideId() {
        return rideId;
    }

    public String getPassengerId() {
        return passengerId;
    }
}
