package com.task.foursquarevenuesearch.model;

public class VenueSearchResult {

    private Response response;

    public VenueSearchResult(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
