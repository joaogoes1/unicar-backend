package com.unicar;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.unicar.auth.api.LoginController;
import com.unicar.auth.di.AuthModule;
import com.unicar.di.FirebaseModule;
import com.unicar.profile.controller.ProfileController;
import com.unicar.profile.di.ProfileModule;
import com.unicar.ride.controller.RideController;
import com.unicar.ride.di.RideModule;
import com.unicar.util.router.Controller;
import com.unicar.util.log.Logger;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Main {

    private static List<Class<? extends Controller>> getControllers() {
        List<Class<? extends Controller>> list = new ArrayList<>();
        list.add(LoginController.class);
        list.add(ProfileController.class);
        list.add(RideController.class);
        return list;
    }

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(
                    new AuthModule(),
                    new FirebaseModule(),
                    new ProfileModule(),
                    new RideModule()
            );

            init();
            path("/", () -> {
                getControllers().forEach(controller -> injector.getInstance(controller).apis());
            });
            before((req, res) -> {
                Logger.log(req.requestMethod() + " - " + req.pathInfo() + " / " + req.body());
            });
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}
