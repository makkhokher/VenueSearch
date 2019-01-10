package com.task.foursquarevenuesearch.presenter;


import android.location.Location;

import com.task.foursquarevenuesearch.Service.GetVenueApi;
import com.task.foursquarevenuesearch.model.VenueSearchResult;
import com.task.foursquarevenuesearch.view.VenueView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VenuePresenter {
    private VenueView venueView;
    private GetVenueApi api;
    private Call<VenueSearchResult> call;


    public VenuePresenter(VenueView venueView) {
        this.venueView = venueView;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.foursquare.com/v2/venues/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(GetVenueApi.class);
    }

    public void getVenueSearch(String query,Location location){
        if(call!=null){
            call.cancel();
        }
        String url = "search?intent=match&ll="+location.getLatitude()+","+
                location.getLongitude()+"&name="+query+
                "&client_id=RFQKGE04N0DGAZDZWUWQRAU0TT0550OIAW0OEBRDOEJRHHNM&" +
                "client_secret=QF5N4QW3DB3H5BNSI0KQVYFYHVSYOSOAKWJ25VNDYFRCDG4D&v=20190108";
        getVenueResult(url);
    }
    public void getVenues(Location location){
        if(call!=null){
            call.cancel();
        }
        String url = "search?ll="+location.getLatitude()+","+location.getLongitude()+
                "&client_id=RFQKGE04N0DGAZDZWUWQRAU0TT0550OIAW0OEBRDOEJRHHNM&" +
                "client_secret=QF5N4QW3DB3H5BNSI0KQVYFYHVSYOSOAKWJ25VNDYFRCDG4D&v=20190108";
        getVenueResult(url);
    }

    void getVenueResult(String url){
        call = api.getVenue(url);
        call.enqueue(new Callback<VenueSearchResult>() {
            @Override
            public void onResponse(Call<VenueSearchResult> call, Response<VenueSearchResult> response) {
                VenueSearchResult result = response.body();
                if (result != null && result.getResponse() != null)
                    venueView.onVenueResultReturn(result.getResponse().getVenues());

            }

            @Override
            public void onFailure(Call<VenueSearchResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
