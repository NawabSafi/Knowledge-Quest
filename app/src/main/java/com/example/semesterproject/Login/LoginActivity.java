package com.example.semesterproject.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.MainActivity;
import com.example.semesterproject.R;
import com.example.semesterproject.SignUp.AuthenticationManager;
import com.example.semesterproject.SignUp.SignupActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextRollNumber;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextRollNumber = findViewById(R.id.editTextRollNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve entered credentials
                String enteredUsername = editTextRollNumber.getText().toString();
                String enteredPassword = editTextPassword.getText().toString();

                // Authenticate using AuthenticationManager
                if (AuthenticationManager.authenticate(enteredUsername, enteredPassword)) {
                    // Login successful
                    // Navigate to the main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Finish the LoginActivity to prevent going back to it from MainActivity
                    finish();
                } else {
                    // Login failed
                    // Display an error message or take appropriate action
                    Toast.makeText(LoginActivity.this, "Wrong credentials. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open SignupActivity when SignUp button is clicked
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
