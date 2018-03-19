package com.example.tracking;

/**
 * Created by Dabas on 2018-03-18.
 */


//Maybe make a bitcoin price checker
public class Repo {
    public static final String BTC_URL = "https://api.coindesk.com/v1/bpi/currentprice/btc.json";
    private static final String TAG = "URLBUILDER";
    private String mTrackingNum;

    public Repo(String mTrackingNum) {
        this.mTrackingNum = mTrackingNum;
    }

    public String getTrackingDetails(){

        //TODO - restCall for tracking details and set timer
        return "";
    }
}
