package com.task.foursquarevenuesearch.view;

import com.task.foursquarevenuesearch.model.Venue;

import java.util.List;

public interface VenueView {
    void onVenueResultReturn(List<Venue> venues);
}
