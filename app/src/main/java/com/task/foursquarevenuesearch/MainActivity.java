package com.task.foursquarevenuesearch;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.task.foursquarevenuesearch.adapter.VenueAdapter;
import com.task.foursquarevenuesearch.model.Venue;
import com.task.foursquarevenuesearch.presenter.VenuePresenter;
import com.task.foursquarevenuesearch.view.VenueView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener, VenueView {

    private static final int LOCATION_REQUEST_CODE = 1;
    SearchView search;

    VenueAdapter adapter;
    List<Venue> venues;

    VenuePresenter presenter;

    LocationManager manager;
    Location location;

    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        bar = findViewById(R.id.progress_bar);

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setIconified(false);
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(location!=null) {
                    if(query.isEmpty())
                        presenter.getVenues(location);
                    else
                        presenter.getVenueSearch(query, location);
                    if(bar.getVisibility()==View.GONE){
                        bar.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });

        presenter = new VenuePresenter(this);

        venues = new ArrayList<>();
        adapter = new VenueAdapter(venues);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }else
            manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
    }

    @Override
    public void onVenueResultReturn(List<Venue> venues) {
        bar.setVisibility(View.GONE);
        this.venues.clear();
        this.venues.addAll(venues);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==LOCATION_REQUEST_CODE){
            if(grantResults.length>0){
                manager.requestSingleUpdate(
                        LocationManager.NETWORK_PROVIDER,
                        MainActivity.this,
                        null);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        presenter.getVenues(location);
        this.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
