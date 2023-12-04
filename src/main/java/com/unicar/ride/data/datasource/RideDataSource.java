package com.unicar.ride.data.datasource;

import com.google.type.LatLng;
import com.unicar.ride.controller.response.RideSummary;
import com.unicar.ride.domain.model.Ride;

import java.util.List;

public interface RideDataSource {
    void registerRide(String userId, Ride ride);

    List<Ride> getRideByDestiny(double latitude, double longitude);

    List<Ride> getRideByUserId(String userId);

    void addPassenger(String userId, String passengerId, LatLng departurePlace);

    void removePassenger(String rideId, String passengerId);

    RideSummary getRideByDriver(String userId);
}
