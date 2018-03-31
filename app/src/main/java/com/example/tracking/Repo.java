package com.example.tracking;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Dabas on 2018-03-18.
 */


public class Repo implements Callback<Summary>{
    private String mTrackingNum;
    private Context mContext;

    public Repo(Context context, String trackingNum) {
        mContext = context;
        mTrackingNum = trackingNum;
    }

    public void getTrackingDetails() {

        String username = "c2c0380cf31db14c";
        String pass = "f7055d29ecde79a208e45e";

        OkHttpClient client = createAuthclient(username, pass);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.format(TrackingAPI.TRACKING_URL, mTrackingNum))
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        TrackingAPI trackingAPI = retrofit.create(TrackingAPI.class);
        Call<Summary> summaryCall = trackingAPI.getTrackingSummary();
        summaryCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Summary> call, retrofit2.Response<Summary> response) {
        if(response.isSuccessful()){
            if (response.body() != null){
                String eventType = response.body().getEventType();
                String expectedDate = response.body().getExpectedDate();
                String deliveryDate = response.body().getDeliveryDate();
                String eventLoc = response.body().getEventLocation();

                Toast.makeText(mContext, eventType + " On " + deliveryDate + " at " + eventLoc, Toast.LENGTH_LONG).show();
            }

        } else {
            //TODO - Maybe use SnackBar
            Toast.makeText(mContext, "Package Tracking ID not found", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onFailure(Call<Summary> call, Throwable t) {
        Toast.makeText(mContext, "The Call FAILED!!!", Toast.LENGTH_SHORT ).show();
        System.out.println(t.toString());
    }

    private static OkHttpClient createAuthclient(final String username, final String pass) {
        OkHttpClient httpClient = new OkHttpClient().newBuilder().authenticator(new Authenticator() {
            @Nullable
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, pass);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
        return httpClient;
    }
}
