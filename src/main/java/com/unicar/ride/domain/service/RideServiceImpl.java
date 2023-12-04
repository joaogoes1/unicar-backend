package com.unicar.ride.domain.service;

import com.google.inject.Inject;
import com.google.type.LatLng;
import com.unicar.ride.controller.response.RideSummary;
import com.unicar.ride.data.datasource.RideDataSource;
import com.unicar.ride.domain.model.Ride;

import java.util.List;

public class RideServiceImpl implements RideService {

    private final RideDataSource rideDataSource;

    @Inject
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
    public RideSummary getRideByDriver(String userId) {
        return rideDataSource.getRideByDriver(userId);
    }

    @Override
    public void addPassenger(String rideId, String passengerId, LatLng departurePlace) {
        rideDataSource.addPassenger(rideId, passengerId, departurePlace);
    }

    @Override
    public void removePassenger(String rideId, String passengerId) {
        rideDataSource.removePassenger(rideId, passengerId);
    }
}
