package com.unicar.ride.data.datasource;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.inject.Inject;
import com.google.type.LatLng;
import com.unicar.profile.domain.model.Car;
import com.unicar.profile.domain.model.Profile;
import com.unicar.ride.controller.response.RideSummary;
import com.unicar.ride.domain.model.Passenger;
import com.unicar.ride.domain.model.Ride;
import com.unicar.util.log.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class FirebaseRideDataSource implements RideDataSource {

    private final Firestore firestore;

    @Inject
    public FirebaseRideDataSource(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void registerRide(String userId, Ride ride) {
        firestore.collection("profile").document(userId).set(Map.of("ride", ride));
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
    public void addPassenger(String userId, String passengerId, LatLng departurePlace) {
        try {
            final Ride ride = requireNonNull(firestore.collection("profile").document(userId).get().get().toObject(Profile.class)).getRide();
            final List<Passenger> passengers = ride.getPassengers();
            if (passengers != null && !passengers.stream().map(Passenger::getPassengerId).collect(Collectors.toList()).contains(passengerId)) {
                passengers.add(new Passenger(passengerId, departurePlace));
                firestore.collection("profile").document(userId).update("passengers", passengers);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removePassenger(String userId, String passengerId) {
        try {
            final Ride ride = requireNonNull(firestore.collection("profile").document(userId).get().get().toObject(Profile.class)).getRide();
            final List<Passenger> passengers = ride.getPassengers();
            if (passengers != null && !passengers.stream().map(Passenger::getPassengerId).collect(Collectors.toList()).contains(passengerId)) {
                passengers.removeIf(passenger -> passenger.getPassengerId().equals(passengerId));
                firestore.collection("profile").document(userId).update("passengers", passengers);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RideSummary getRideByDriver(String userId) {
        try {
            final Profile profile = firestore.collection("profile").document(userId).get().get().toObject(Profile.class);
            requireNonNull(profile);
            final Ride ride = profile.getRide();

            final List<RideSummary.Passenger> passengers = new ArrayList<>(List.of());
            if (ride != null && ride.getPassengers() != null && !ride.getPassengers().isEmpty()) {
                for (Passenger passenger : ride.getPassengers()) {
                    final DocumentReference passengerReference = firestore.collection("profile").document(passenger.getPassengerId());
                    final DocumentSnapshot passengerSnapshot = passengerReference.get().get();
                    final Profile passengerProfile = passengerSnapshot.toObject(Profile.class);
                    if (passengerProfile == null) continue;
                    passengers.add(new RideSummary.Passenger(
                            passengerProfile.getName(),
                            passengerProfile.getPhone(),
                            passenger.getDeparturePlace().toString()
                    ));
                }
            }
            return new RideSummary(
                    ride != null ? ride.getStartDate() : null,
                    passengers,
                    ride != null ? ride.getAvailableSeats() : 0
            );
        } catch (Exception e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
