package com.unicar.ride.domain.model;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.common.base.Objects;
import com.google.type.LatLng;

import java.util.Date;
import java.util.List;

public class Ride {
    private String id;

    private Date arriveDate;

    private LatLng origin;

    private LatLng destiny;

    private Date startDate;

    private int availableSeats;

    private double price;

    private String driverId;

    private List<String> passengersId;

    public Ride(String id, String driverId, LatLng origin, LatLng destiny, Date arriveDate, Date startDate, int availableSeats, double price, List<String> passengersId) {
        this.id = id;
        this.driverId = driverId;
        this.origin = origin;
        this.destiny = destiny;
        this.arriveDate = arriveDate;
        this.startDate = startDate;
        this.availableSeats = availableSeats;
        this.price = price;
        this.passengersId = passengersId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
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

    public List<String> getPassengersId() {
        return passengersId;
    }

    public void setPassengersId(List<String> passengersId) {
        this.passengersId = passengersId;
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
                Objects.equal(arriveDate, ride.arriveDate) &&
                Objects.equal(startDate, ride.startDate) &&
                Objects.equal(driverId, ride.driverId) &&
                Objects.equal(passengersId, ride.passengersId);
    }

    @Override
    public int hashCode() {
        int hashCode = 31;
        hashCode = 31 * hashCode + (id != null ? id.hashCode() : 0);
        hashCode = 31 * hashCode + (arriveDate != null ? arriveDate.hashCode() : 0);
        hashCode = 31 * hashCode + (origin != null ? origin.hashCode() : 0);
        hashCode = 31 * hashCode + (destiny != null ? destiny.hashCode() : 0);
        hashCode = 31 * hashCode + (startDate != null ? startDate.hashCode() : 0);
        hashCode = 31 * hashCode + Double.valueOf(price).hashCode();
        hashCode = 31 * hashCode + (driverId != null ? driverId.hashCode() : 0);
        if (passengersId != null)
            for (String passengerId : passengersId) {
                hashCode = 31 * hashCode + (passengerId != null ? passengerId.hashCode() : 0);
            }
        return hashCode;
    }
}
