package com.unicar.ride.data.datasource;

import com.unicar.ride.domain.model.Ride;

import java.util.List;

public interface RideDataSource {
    void registerRide(Ride ride);

    List<Ride> getRideByDestiny(double latitude, double longitude);

    List<Ride> getRideByUserId(String userId);

    void addPassenger(String rideId, String passengerId);

    void removePassenger(String rideId, String passengerId);
}
