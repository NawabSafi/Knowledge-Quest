package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.Login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    // Duration of the splash screen in milliseconds
    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Use a Handler to delay the start of the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to start the next activity (replace MainActivity.class with your desired activity)
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);

                // Close this activity to prevent the user from going back to the splash screen
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
