package com.example.tracking;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dabas on 2018-03-24.
 */

public interface TrackingAPI {
    public static final String TRACKING_URL = "https://soa-gw.canadapost.ca/vis/track/pin/%s/";

    @GET("summary")
    Call<Summary> getTrackingSummary();
}
