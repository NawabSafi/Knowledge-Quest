package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.Forms.FormActivity;
import com.example.semesterproject.UI.CalculatorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGetData = findViewById(R.id.buttonGetData);
        Button buttonCalc = findViewById(R.id.buttonCalculator);
        Button buttonForm = findViewById(R.id.buttonForm); // Assuming you have a button with ID buttonForm

        // Set a click listener for the Calculator button
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click to open CalculatorActivity
                openCalculatorActivity();
            }
        });

        // Set a click listener for the Form button
        Log.e("MainActivity", "Crash here");
        buttonForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click to open DataActivity
                openGetDataActivity();
            }
        });
    }
    public void openGetDataActivity() {
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }

    // Method to open CalculatorActivity
    public void openCalculatorActivity() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);

    }



}
