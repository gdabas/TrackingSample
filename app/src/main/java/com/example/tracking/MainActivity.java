package com.example.tracking;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private final int counterInMilliSec = 10000;
    private Repo repo;
    EditText trackingId;
    Button findPackageButton;
    TextView secondTextView, refreshTextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackingId = findViewById(R.id.tracking_id_text_view);
        findPackageButton = findViewById(R.id.find_button);
        secondTextView = findViewById(R.id.seconds_textview);
        refreshTextView = findViewById(R.id.refresh_textview);

        trackingId.addTextChangedListener(trackingTextWatcher);

        mTrackingViewModel = ViewModelProviders.of(this).get(TrackingViewModel.class);
        final Observer<Integer> timeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                String secondLeft = String.valueOf(integer);
                secondTextView.setText(secondLeft);
            }
        };

        mTrackingViewModel.getMinutes().observe(this, timeObserver);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.find_button) {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            repo = new Repo(trackingId.toString());
            repo.getTrackingDetails();

            startCountDown(counterInMilliSec);
        }
    }


    public void startCountDown(int millisec){
        new CountDownTimer(millisec, 1000) {
            public void onTick(long millisUntilFinished) {
                mTrackingViewModel.getMinutes().setValue((int) millisUntilFinished / 1000);
            }

            public void onFinish() {
                secondTextView.setText("0");
                //TODO - Call the network again
            }
        }.start();
    }
}
