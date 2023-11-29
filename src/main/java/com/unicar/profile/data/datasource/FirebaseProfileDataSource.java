package com.unicar.profile.data.datasource;

import com.google.cloud.firestore.Firestore;
import com.google.inject.Inject;
import com.unicar.profile.domain.model.Car;
import com.unicar.profile.domain.model.Profile;
import com.unicar.util.log.Logger;

import java.util.concurrent.ExecutionException;

public class FirebaseProfileDataSource implements ProfileDataSource {

    private final Firestore firestore;

    @Inject
    public FirebaseProfileDataSource(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Profile getProfile(String userId) throws ExecutionException, InterruptedException {
        return firestore.collection("users").document(userId).get().get().toObject(Profile.class);
    }

    @Override
    public void updateProfile(String userId, Profile profile) {
        firestore.collection("users").document(userId).update("profile", profile.toJson());
    }

    @Override
    public Car getCar(String userId) {
        try {
            return firestore.collection("car").document(userId).get().get().toObject(Car.class);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateCar(String userId, Car car) {
        firestore.collection("profile").document(userId).update("car", car.toJson());
    }
}
