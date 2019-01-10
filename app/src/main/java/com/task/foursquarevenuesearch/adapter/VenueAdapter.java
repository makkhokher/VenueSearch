package com.task.foursquarevenuesearch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.task.foursquarevenuesearch.R;
import com.task.foursquarevenuesearch.model.Venue;

import java.util.List;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.ViewHolder> {

    private List<Venue> venues;

    public VenueAdapter(List<Venue> venues) {
        this.venues = venues;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_venue,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Venue venue = venues.get(i);
        holder.name.setText(venue.getName());
        String distance = venue.getLocation().getDistance()+"m";
        holder.distance.setText(distance);
        holder.address.setText(venue.getLocation().getAddress());
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView distance;
        TextView address;
        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            distance = view.findViewById(R.id.distance);
            address = view.findViewById(R.id.address);
        }
    }
}
