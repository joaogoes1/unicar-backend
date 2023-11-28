package com.unicar.profile.domain.service;

import com.unicar.profile.domain.model.Car;
import com.unicar.profile.data.datasource.ProfileDataSource;
import com.unicar.profile.domain.model.Profile;
import com.unicar.profile.domain.response.RegisterCarResponse;
import com.unicar.util.log.Logger;

public class ProfileServiceImpl implements ProfileService {

    private final ProfileDataSource dataSource;

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
    public RegisterCarResponse registerCar(String userId, Car car) {
        try {
            dataSource.updateCar(userId, car);
            return new RegisterCarResponse.Success();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return new RegisterCarResponse.Error();
        }
    }

    @Override
    public void updateCar(String userId, Car car) {
        dataSource.updateCar(userId, car);
    }
}
