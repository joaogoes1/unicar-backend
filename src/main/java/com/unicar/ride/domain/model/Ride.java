package com.unicar.ride.domain.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.Exclude;
import com.google.common.base.Objects;
import com.google.type.DateTime;
import com.google.type.LatLng;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNullElseGet;

public class Ride {
    private String id;

    private LatLng origin;

    private LatLng destiny;

    private Date startDate;

    private int availableSeats;

    private double price;

    private String driverId;

    private List<Passenger> passengers;

    public Ride(String id, String driverId, LatLng origin, LatLng destiny, Date startDate, int availableSeats, double price, List<Passenger> passengers) {
        this.id = id;
        this.driverId = driverId;
        this.origin = origin;
        this.destiny = destiny;
        this.startDate = startDate;
        this.availableSeats = availableSeats;
        this.price = price;
        setPassengers(passengers);
    }

    public Ride() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public LatLng getOrigin() {
        return origin;
    }

    public void setOrigin(LatLng origin) {
        this.origin = origin;
    }

    @Exclude
    public LatLng getDestiny() {
        return destiny;
    }

    public void setDestiny(LatLng destiny) {
        this.destiny = destiny;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public List<Passenger> getPassengers() {
        return requireNonNullElseGet(passengers, List::of);
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = requireNonNullElseGet(passengers, List::of);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return Objects.equal(origin, ride.origin) &&
                Objects.equal(destiny, ride.destiny) &&
                Double.compare(price, ride.price) == 0 &&
                Objects.equal(id, ride.id) &&
                Objects.equal(startDate, ride.startDate) &&
                Objects.equal(driverId, ride.driverId) &&
                Objects.equal(passengers, ride.passengers);
    }

    @Override
    public int hashCode() {
        int hashCode = 31;
        hashCode = 31 * hashCode + (id != null ? id.hashCode() : 0);
        hashCode = 31 * hashCode + (origin != null ? origin.hashCode() : 0);
        hashCode = 31 * hashCode + (destiny != null ? destiny.hashCode() : 0);
        hashCode = 31 * hashCode + (startDate != null ? startDate.hashCode() : 0);
        hashCode = 31 * hashCode + Double.valueOf(price).hashCode();
        hashCode = 31 * hashCode + (driverId != null ? driverId.hashCode() : 0);
        if (passengers != null)
            for (Passenger passengerId : passengers) {
                hashCode = 31 * hashCode + (passengerId != null ? passengerId.hashCode() : 0);
            }
        return hashCode;
    }

    public Object toJson() {
        final Map<String, Object> json = new HashMap<>();
        if (origin != null) json.put("origin", origin);
        if (destiny != null) json.put("destiny", destiny);
        if (startDate != null) json.put("startDate", startDate);
        json.put("availableSeats", availableSeats);
        json.put("price", price);
        if (driverId != null) json.put("driverId", driverId);
        if (passengers != null) json.put("passengersId", passengers);
        return json;
    }
}
