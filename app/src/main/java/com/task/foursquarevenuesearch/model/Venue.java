package com.task.foursquarevenuesearch.model;

public class Venue {
    private String name;
    private Location location;

    public Venue(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
