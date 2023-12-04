package com.unicar.ride.controller;

import com.google.inject.Inject;
import com.unicar.auth.domain.LoginService;
import com.unicar.ride.controller.request.AddPassengerRequest;
import com.unicar.ride.controller.request.CreateRideRequest;
import com.unicar.ride.controller.request.RemovePassengerRequest;
import com.unicar.ride.controller.response.RideSummary;
import com.unicar.ride.domain.service.RideService;
import com.unicar.util.parsers.LatLngStringToLatLng;
import com.unicar.util.router.Controller;

import static com.unicar.util.router.AuthenticatedRoutes.*;
import static com.unicar.util.router.BodyParser.bodyTyped;
import static spark.Spark.halt;

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
            rideService.registerRide(getUserId(req, loginService), body.toRide(getUserId(req, loginService)));
            res.status(201);
            return "{}";
        });
    }

    void getRidesByDestiny() {
        getAuthenticated("/ride/destiny/:latitude/:longitude", (req, res) -> {
            final double latitude = Double.parseDouble(req.params(":latitude"));
            final double longitude = Double.parseDouble(req.params(":longitude"));
            return rideService.getRideByDestiny(latitude, longitude);
        });
    }

    void getRideByDriver() {
        getAuthenticated("/ride", (req, res) -> {
            final RideSummary result = rideService.getRideByDriver(getUserId(req, loginService));
            if (result == null) {
                halt(404);
                return "";
            }
            return result.toJson();
        });
    }

    void postPassenger() {
        postAuthenticated("/ride/passenger", (req, res) -> {
            final AddPassengerRequest body = bodyTyped(req, AddPassengerRequest.class);
            rideService.addPassenger(body.getRideId(), body.getPassengerId(), LatLngStringToLatLng.parse(body.getDeparturePlace()));
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
        getRidesByDestiny();
        getRideByDriver();
        postPassenger();
        deletePassenger();
    }
}
