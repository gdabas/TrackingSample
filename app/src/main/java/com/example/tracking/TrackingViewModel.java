package com.example.tracking;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by Dabas on 2018-03-18.
 */

public class TrackingViewModel extends AndroidViewModel {

    public MutableLiveData<Integer> mSeconds;

    public TrackingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getMinutes(){
        if (mSeconds == null){
            mSeconds = new MutableLiveData<>();
        }
        return mSeconds;
    }
}
