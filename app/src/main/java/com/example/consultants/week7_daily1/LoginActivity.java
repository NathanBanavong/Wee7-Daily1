package com.example.consultants.week7_daily1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName() + "_TAG";
    private TextView tvLogin;
    private TextView tvTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onBindViews();
        Log.d(TAG, "onCreate: ");
        Intent intentSignUp = getIntent();
        tvLogin.setText(intentSignUp.getStringExtra("Email"));
        Log.d(TAG, "onCreate: " + tvLogin.getText().toString());

        setTimer();
    }

    public void onBindViews() {
        tvLogin = findViewById(R.id.tvLogin);
        tvTimer = findViewById(R.id.tvTimer);

    }

    public void setTimer() {
        Log.d(TAG, "setTimer: ");
        tvTimer.setText("60");
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int time = Integer.parseInt(tvTimer.getText().toString());
                                time--;
                                tvTimer.setText(String.valueOf(time));
                                if(time == 0){
                                    finish();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();
    }

}
