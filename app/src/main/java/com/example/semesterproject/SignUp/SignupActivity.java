package com.example.semesterproject.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.Login.LoginActivity;
import com.example.semesterproject.R;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextRollNumber;
    private EditText editTextPassword;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextRollNumber = findViewById(R.id.editTextRollNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve entered credentials
                String newUsername = editTextRollNumber.getText().toString();
                String newPassword = editTextPassword.getText().toString();

                // Check if the username is already taken (for educational purposes)
                if (AuthenticationManager.isUsernameTaken(newUsername)) {
                    Toast.makeText(SignupActivity.this, "Username is already taken. Please choose another.", Toast.LENGTH_SHORT).show();
                } else {
                    // Sign up the user
                    AuthenticationManager.signup(newUsername, newPassword);
                    Toast.makeText(SignupActivity.this, "Signup successful. You can now log in.", Toast.LENGTH_SHORT).show();

                    // Optionally, you can navigate to LoginActivity or perform other actions
                    // For example, you can automatically log in the user after signup

                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
