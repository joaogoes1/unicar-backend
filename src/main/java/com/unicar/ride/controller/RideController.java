package com.unicar.ride.controller;

import com.google.inject.Inject;
import com.unicar.auth.domain.LoginService;
import com.unicar.ride.controller.request.CreateRideRequest;
import com.unicar.ride.controller.request.RemovePassengerRequest;
import com.unicar.ride.domain.model.Ride;
import com.unicar.ride.domain.service.RideService;
import com.unicar.util.log.Logger;
import com.unicar.util.router.Controller;

import static com.unicar.util.router.AuthenticatedRoutes.*;
import static com.unicar.util.router.BodyParser.bodyTyped;

public class RideController implements Controller {

    private final RideService rideService;
    private final LoginService loginService;

    @Inject
    public RideController(RideService rideService, LoginService loginService) {
        this.rideService = rideService;
        this.loginService = loginService;
    }

    void postRide() {
        postAuthenticated("/ride", (req, res) -> {
            final CreateRideRequest body = bodyTyped(req, CreateRideRequest.class);
            rideService.registerRide(body.toRide(getUserId(req, loginService)));
            res.status(201);
            return "{}";
        });
    }

    void getRideByDestiny() {
        getAuthenticated("/ride/destiny/:latitude/:longitude", (req, res) -> {
            final double latitude = Double.parseDouble(req.params(":latitude"));
            final double longitude = Double.parseDouble(req.params(":longitude"));
            return rideService.getRideByDestiny(latitude, longitude);
        });
    }

    void getRideByUserId() {
        getAuthenticated("/ride/user/:userId", (req, res) -> {
            final String userId = req.params(":userId");
            return rideService.getRideByUserId(userId);
        });
    }

    void postPassenger() {
        postAuthenticated("/ride/passenger", (req, res) -> {
            final RemovePassengerRequest body = bodyTyped(req, RemovePassengerRequest.class);
            rideService.addPassenger(body.getRideId(), body.getPassengerId());
            res.status(200);
            return "{}";
        });
    }

    void deletePassenger() {
        deleteAuthenticated("/ride/passenger", (req, res) -> {
            final RemovePassengerRequest body = bodyTyped(req, RemovePassengerRequest.class);
            rideService.removePassenger(body.getRideId(), body.getPassengerId());
            res.status(200);
            return "{}";
        });
    }

    @Override
    public void apis() {
        postRide();
        getRideByDestiny();
        getRideByUserId();
        postPassenger();
        deletePassenger();
    }
}
