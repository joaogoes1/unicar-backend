package com.unicar.ride.domain.service;

import com.unicar.ride.data.datasource.RideDataSource;
import com.unicar.ride.domain.model.Ride;

import java.util.List;

public class RideServiceImpl implements RideService {

    private final RideDataSource rideDataSource;

    public RideServiceImpl(RideDataSource rideDataSource) {
        this.rideDataSource = rideDataSource;
    }

    @Override
    public void registerRide(String userId, Ride ride) {
        rideDataSource.registerRide(userId, ride);
    }

    @Override
    public List<Ride> getRideByDestiny(double latitude, double longitude) {
        return rideDataSource.getRideByDestiny(latitude, longitude);
    }

    @Override
    public List<Ride> getRideByUserId(String userId) {
        return rideDataSource.getRideByUserId(userId);
    }

    @Override
    public void addPassenger(String rideId, String passengerId) {
        rideDataSource.addPassenger(rideId, passengerId);
    }

    @Override
    public void removePassenger(String rideId, String passengerId) {
        rideDataSource.removePassenger(rideId, passengerId);
    }
}