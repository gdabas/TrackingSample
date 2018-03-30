package com.example.tracking;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by Dabas on 2018-03-18.
 */

public class TrackingViewModel extends AndroidViewModel {

    private Repo repo;
    public MutableLiveData<String> mSeconds;
    public MutableLiveData<String> mSummary;

    public TrackingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getMinutes(){
        if (mSeconds == null){
            mSeconds = new MutableLiveData<>();
        }
        return mSeconds;
    }

    public void trackId(String trackingId){
        //Call the API
        repo = new Repo(this.getApplication(), trackingId);
        repo.getTrackingDetails();
    }

}
