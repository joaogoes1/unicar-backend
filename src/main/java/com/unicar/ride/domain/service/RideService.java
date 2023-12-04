package com.unicar.ride.domain.service;

import com.google.type.LatLng;
import com.unicar.ride.controller.response.RideSummary;
import com.unicar.ride.domain.model.Ride;

import java.util.List;

public interface RideService {
    void registerRide(String userId, Ride ride);

    List<Ride> getRideByDestiny(double latitude, double longitude);

    List<Ride> getRideByUserId(String userId);

    RideSummary getRideByDriver(String userId);

    void addPassenger(String rideId, String passengerId, LatLng departurePlace);

    void removePassenger(String rideId, String passengerId);
}
