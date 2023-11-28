package com.unicar.profile.data.datasource;

import com.unicar.profile.domain.model.Car;
import com.unicar.profile.domain.model.Profile;

import java.util.concurrent.ExecutionException;

public interface ProfileDataSource {
    Profile getProfile(String userId) throws ExecutionException, InterruptedException;

    void updateProfile(String userId, Profile profile);

    Car getCar(String userId);

    void updateCar(String userId, Car car);
}
