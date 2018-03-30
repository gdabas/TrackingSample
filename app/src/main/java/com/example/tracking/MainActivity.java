package com.example.tracking;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TrackingViewModel mTrackingViewModel;
    private final int counterInMilliSec = 30000;
    EditText trackingId;
    Button findPackageButton;
    TextView liveDataTimerText, timerText, locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackingId = findViewById(R.id.tracking_id_text_view);
        findPackageButton = findViewById(R.id.find_button);
        liveDataTimerText = findViewById(R.id.seconds_textview);
        timerText = findViewById(R.id.timer);
        locationText = findViewById(R.id.locationText);

        trackingId.addTextChangedListener(trackingTextWatcher);

        attachViewModel();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.find_button) {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            startTimers();

            //Find the Parcel
            mTrackingViewModel.trackId(trackingId.getText().toString());
        }
    }

    public void attachViewModel(){
        // Setting ViewModel for this Activity
        mTrackingViewModel = ViewModelProviders.of(this).get(TrackingViewModel.class);

        //Setup Observer to update UI when new changes Observed
        final Observer<String> timeObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                String secondLeft = String.valueOf(s);
                liveDataTimerText.setText(secondLeft);
            }
        };

        //Start Observing
        mTrackingViewModel.getMinutes().observe(this, timeObserver);
    }

    public void startTimers(){
        CountDowns basicCountDown = new CountDowns(this, counterInMilliSec, 1000);
        CountDowns liveDataCountDown = new CountDowns(this, counterInMilliSec, 1000);

        basicCountDown.start();
        liveDataCountDown.start();
    }

    public void updateBasicTimer(String secondLeft){
        timerText.setText(secondLeft);
    }

    public void trackingResult(String loc){
        locationText.setText(loc);
    }

    TextWatcher trackingTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (trackingId.toString().trim().length() > 4) {
                findPackageButton.setEnabled(true);

            } else {
                findPackageButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
