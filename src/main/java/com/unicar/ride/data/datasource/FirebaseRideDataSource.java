package com.unicar.ride.data.datasource;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.unicar.ride.domain.model.Ride;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseRideDataSource implements RideDataSource {

    private final Firestore firestore;

    public FirebaseRideDataSource(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void registerRide(String userId, Ride ride) {
        firestore.collection("rides").document(userId).set(ride);
    }

    @Override
    public List<Ride> getRideByDestiny(double latitude, double longitude) {
        final GeoPoint location = new GeoPoint(latitude, longitude);
        try {
            return firestore.collection("rides").whereEqualTo("destiny", location).get().get().toObjects(Ride.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ride> getRideByUserId(String userId) {
        try {
            return firestore.collection("rides").whereArrayContains("passengersId", userId).get().get().toObjects(Ride.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPassenger(String rideId, String passengerId) {
        try {
            final List<String> passengers = firestore.collection("rides").document(rideId).get().get().toObject(Ride.class).getPassengersId();
            if (passengers != null && !passengers.contains(passengerId)) {
                passengers.add(passengerId);
                firestore.collection("rides").document(rideId).update("passengersId", passengerId);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removePassenger(String rideId, String passengerId) {
        try {
            final List<String> passengers = firestore.collection("rides").document(rideId).get().get().toObject(Ride.class).getPassengersId();
            if (passengers != null && passengers.contains(passengerId)) {
                passengers.remove(passengerId);
                firestore.collection("rides").document(rideId).update("passengersId", passengerId);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
