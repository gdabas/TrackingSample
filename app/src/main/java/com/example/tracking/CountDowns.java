package com.example.tracking;

import android.arch.lifecycle.ViewModelProviders;
import android.os.CountDownTimer;

/**
 * Created by Dabas on 2018-03-30.
 */

public class CountDowns extends CountDownTimer {
    private MainActivity mContext;
    private TrackingViewModel viewModel;

    public CountDowns(MainActivity context, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mContext = context;
        viewModel = ViewModelProviders.of(mContext).get(TrackingViewModel.class);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mContext.updateBasicTimer(String.valueOf(millisUntilFinished / 1000));
        viewModel.getMinutes().setValue(String.valueOf(millisUntilFinished / 1000));
    }

    @Override
    public void onFinish() {
        mContext.updateBasicTimer("0");
        viewModel.getMinutes().setValue("0");
    }
}
