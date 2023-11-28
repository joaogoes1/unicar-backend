package com.unicar.profile.domain.service;

import com.unicar.profile.domain.model.Car;
import com.unicar.profile.domain.model.Profile;
import com.unicar.profile.domain.response.RegisterCarResponse;

public interface ProfileService {

    Profile getProfile(String userId);
    void updateProfile(String userId, Profile profile);
    void updateCar(String userId, Car car);
    Car getCarByUser(String userId);
    RegisterCarResponse registerCar(String userId, Car car);
}
