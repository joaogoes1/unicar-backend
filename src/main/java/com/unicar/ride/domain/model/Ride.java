package com.unicar.ride.domain.model;

import com.google.common.base.Objects;

import java.util.Date;
import java.util.List;

public class Ride {
    private String id;

    private Date arriveDate;

    private double originLatitude;

    private double originLongitude;

    private double destinyLatitude;

    private double destinyLongitude;

    private Date startDate;

    private int availableSeats;

    private double price;

    private String driverId;

    private List<String> passengersId;

    public Ride(String id, String driverId, Date arriveDate, double originLatitude, double originLongitude, double destinyLatitude, double destinyLongitude, Date startDate, int availableSeats, double price, List<String> passengersId) {
        this.id = id;
        this.driverId = driverId;
        this.arriveDate = arriveDate;
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinyLatitude = destinyLatitude;
        this.destinyLongitude = destinyLongitude;
        this.startDate = startDate;
        this.availableSeats = availableSeats;
        this.price = price;
        this.passengersId = passengersId;
    }

    public double getDestinyLatitude() {
        return destinyLatitude;
    }

    public void setDestinyLatitude(double destinyLatitude) {
        this.destinyLatitude = destinyLatitude;
    }

    public double getDestinyLongitude() {
        return destinyLongitude;
    }

    public void setDestinyLongitude(double destinyLongitude) {
        this.destinyLongitude = destinyLongitude;
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

    public double getOriginLatitude() {
        return originLatitude;
    }

    public void setOriginLatitude(double originLatitude) {
        this.originLatitude = originLatitude;
    }

    public double getOriginLongitude() {
        return originLongitude;
    }

    public void setOriginLongitude(double originLongitude) {
        this.originLongitude = originLongitude;
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
        return Double.compare(originLatitude, ride.originLatitude) == 0 &&
                Double.compare(originLongitude, ride.originLongitude) == 0 &&
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
        hashCode = 31 * hashCode + (int) originLatitude;
        hashCode = 31 * hashCode + (int) originLongitude;
        hashCode = 31 * hashCode + (startDate != null ? startDate.hashCode() : 0);
        hashCode = 31 * hashCode + (int) price;
        hashCode = 31 * hashCode + (driverId != null ? driverId.hashCode() : 0);
        if (passengersId != null)
            for (String passengerId : passengersId) {
                hashCode = 31 * hashCode + (passengerId != null ? passengerId.hashCode() : 0);
            }
        return hashCode;
    }
}
