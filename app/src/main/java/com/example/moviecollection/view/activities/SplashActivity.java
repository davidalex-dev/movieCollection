package com.example.moviecollection.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.moviecollection.R;

public class SplashActivity extends AppCompatActivity {

    private static final int timeout = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent (SplashActivity.this, MainMenuActivity.class);
                startActivity(i);

                finish();
            }
        }, timeout);
    }
}