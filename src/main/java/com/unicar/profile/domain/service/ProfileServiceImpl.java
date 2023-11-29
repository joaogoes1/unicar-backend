package com.unicar.profile.domain.service;

import com.google.inject.Inject;
import com.unicar.profile.domain.model.Car;
import com.unicar.profile.data.datasource.ProfileDataSource;
import com.unicar.profile.domain.model.Profile;
import com.unicar.util.log.Logger;

public class ProfileServiceImpl implements ProfileService {

    private final ProfileDataSource dataSource;

    @Inject
    public ProfileServiceImpl(ProfileDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Profile getProfile(String userId) {
        try {
            return dataSource.getProfile(userId);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateProfile(String userId, Profile profile) {
        dataSource.updateProfile(userId, profile);
    }

    @Override
    public Car getCarByUser(String userId) {
        return dataSource.getCar(userId);
    }

    @Override
    public void registerCar(String userId, Car car) {
        dataSource.updateCar(userId, car);
    }

    @Override
    public void updateCar(String userId, Car car) {
        dataSource.updateCar(userId, car);
    }
}
