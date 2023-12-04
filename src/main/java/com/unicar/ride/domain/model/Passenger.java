package com.unicar.ride.domain.model;

import com.google.common.base.Objects;
import com.google.type.LatLng;

public class Passenger {
    private final String passengerId;
    private final LatLng departurePlace;

    public Passenger(String passengerId, LatLng departurePlace) {
        this.passengerId = passengerId;
        this.departurePlace = departurePlace;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public LatLng getDeparturePlace() {
        return departurePlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equal(passengerId, passenger.passengerId) &&
                Objects.equal(departurePlace, passenger.departurePlace);
    }

    @Override
    public int hashCode() {
        int ret = 31;
        ret += 31 * ret + (passengerId != null ? passengerId.hashCode() : 0);
        ret += 31 * ret + (departurePlace != null ? departurePlace.hashCode() : 0);
        if (ret < 0) ret = -ret;
        return ret;
    }
}
