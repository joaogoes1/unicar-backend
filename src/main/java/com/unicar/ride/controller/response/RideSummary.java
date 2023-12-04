package com.unicar.ride.controller.response;

import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

import static java.util.Objects.requireNonNullElseGet;

public class RideSummary {

    private Date departureTime;

    private List<Passenger> passengers;

    private int maximumSeats;

    public RideSummary(Date departureTime, List<Passenger> passengers, int maximumSeats) {
        this.departureTime = departureTime;
        this.passengers = requireNonNullElseGet(passengers, List::of);
        this.maximumSeats = maximumSeats;
    }

    public RideSummary() {}

    public Date getDepartureTime() {
        return departureTime;
    }

    public List<Passenger> getPassengers() {
        return requireNonNullElseGet(passengers, List::of);
    }

    public int getMaximumSeats() {
        return maximumSeats;
    }

    public static class Passenger {
        private String name;
        private String phone;
        private String departurePlace;

        public Passenger(String name, String phone, String departurePlace) {
            this.name = name;
            this.phone = phone;
            this.departurePlace = departurePlace;
        }

        public Passenger() {}

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getDeparturePlace() {
            return departurePlace;
        }
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
