package com.unicar.profile.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.unicar.auth.domain.LoginService;
import com.unicar.profile.domain.model.Car;
import com.unicar.profile.domain.model.Profile;
import com.unicar.profile.domain.service.ProfileService;
import com.unicar.profile.controller.request.RegisterCarRequest;
import com.unicar.util.log.Logger;
import com.unicar.util.router.Controller;

import static com.unicar.util.router.AuthenticatedRoutes.*;
import static com.unicar.util.router.BodyParser.bodyTyped;

public class ProfileController implements Controller {
    private final ProfileService profileService;
    private final LoginService loginService;

    @Inject
    public ProfileController(ProfileService profileService, LoginService loginService) {
        this.profileService = profileService;
        this.loginService = loginService;
    }

    public void getProfile() {
        getAuthenticated("/profile", (req, res) -> {
            final Profile profile = profileService.getProfile(getUserId(req, loginService));
            if (profile == null) {
                res.status(404);
                return "";
            }
            res.status(200);
            return new Gson().toJson(profile);
        });
    }

    public void updateProfile() {
        putAuthenticated("/profile", (req, res) -> {
            final String authorizationToken = req.headers("Authorization").replace("Bearer ", "");
            final String userId = loginService.getUserIdFromToken(authorizationToken);
            final Profile body = bodyTyped(req, Profile.class);
            profileService.updateProfile(userId, body);
            res.status(200);
            return "{}";
        });
    }

    public void getCar() {
        getAuthenticated("/car", (req, res) -> {
            final String authorizationToken = req.headers("Authorization").replace("Bearer ", "");
            final String userId = loginService.getUserIdFromToken(authorizationToken);
            final Car car = profileService.getCarByUser(userId);
            if (car == null) {
                res.status(404);
                return "{}";
            }
            res.status(200);
            return car;
        });
    }

    public void registerCar() {
        postAuthenticated("/car", (req, res) -> {
            final RegisterCarRequest body = bodyTyped(req, RegisterCarRequest.class);
            final String userId = getUserId(req, loginService);

            final Car car = new Car(body.getModel(), body.getPlate(), body.getColor());
            try {
                profileService.registerCar(userId, car);
                res.status(201);
                return "{}";
            } catch (Exception e) {
                Logger.error(e.getMessage());
                res.status(400);
                return "{}";
            }
        });
    }

    @Override
    public void apis() {
        getProfile();
        updateProfile();
        getCar();
        registerCar();
    }
}
