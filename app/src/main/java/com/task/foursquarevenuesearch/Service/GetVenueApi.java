package com.task.foursquarevenuesearch.Service;

import com.task.foursquarevenuesearch.model.VenueSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetVenueApi {
    @GET
    Call<VenueSearchResult> getVenue(
            @Url String url
    );
}
