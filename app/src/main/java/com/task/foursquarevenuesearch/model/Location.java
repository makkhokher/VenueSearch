package com.task.foursquarevenuesearch.model;

public class Location {
    private String address;
    private int distance;

    public Location(String address, int distance) {
        this.address = address;
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public int getDistance() {
        return distance;
    }
}
