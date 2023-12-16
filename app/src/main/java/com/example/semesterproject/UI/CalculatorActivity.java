package com.example.semesterproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.semesterproject.R;

public class CalculatorActivity extends AppCompatActivity {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private Spinner spinnerOperator;
    private TextView textViewResult;
    private CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        spinnerOperator = findViewById(R.id.spinnerOperator);
        textViewResult = findViewById(R.id.textViewResult);

        calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);

        // Populate the spinner with operators
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.operators_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperator.setAdapter(adapter);

        // Restore state if available
        if (savedInstanceState != null) {
            calculatorViewModel.setNumber1(savedInstanceState.getDouble("number1"));
            calculatorViewModel.setNumber2(savedInstanceState.getDouble("number2"));
            calculatorViewModel.setOperator(savedInstanceState.getString("operator"));
            calculatorViewModel.setResult(savedInstanceState.getDouble("result"));
            displayResult();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save state during configuration changes (e.g., screen rotation)
        outState.putDouble("number1", calculatorViewModel.getNumber1());
        outState.putDouble("number2", calculatorViewModel.getNumber2());
        outState.putString("operator", calculatorViewModel.getOperator());
        outState.putDouble("result", calculatorViewModel.getResult());
    }

    public void onCalculateButtonClick(View view) {
        // Get the numbers and operator from user input
        String number1Str = editTextNumber1.getText().toString();
        String number2Str = editTextNumber2.getText().toString();
        String selectedOperator = spinnerOperator.getSelectedItem().toString();

        if (!number1Str.isEmpty() && !number2Str.isEmpty()) {
            // Parse numbers
            calculatorViewModel.setNumber1(Double.parseDouble(number1Str));
            calculatorViewModel.setNumber2(Double.parseDouble(number2Str));
            calculatorViewModel.setOperator(selectedOperator);

            // Perform calculation based on the selected operator
            calculateResult();

            // Display the result
            displayResult();
        } else {
            // Display an error message if input is empty
            textViewResult.setText("Please enter both numbers.");
        }
    }

    private void calculateResult() {
        double number1 = calculatorViewModel.getNumber1();
        double number2 = calculatorViewModel.getNumber2();
        String operator = calculatorViewModel.getOperator();

        // Perform calculation based on the selected operator
        switch (operator) {
            case "+":
                calculatorViewModel.setResult(number1 + number2);
                break;
            case "-":
                calculatorViewModel.setResult(number1 - number2);
                break;
            case "*":
                calculatorViewModel.setResult(number1 * number2);
                break;
            case "/":
                // Check for division by zero
                if (number2 != 0) {
                    calculatorViewModel.setResult(number1 / number2);
                } else {
                    // Handle division by zero error
                    calculatorViewModel.setResult(Double.NaN);
                }
                break;
            default:
                // Handle unknown operator
                calculatorViewModel.setResult(Double.NaN);
        }
    }

    private void displayResult() {
        // Display the result
        double result = calculatorViewModel.getResult();
        if (!Double.isNaN(result)) {
            textViewResult.setText("Result: " + result);
        } else {
            textViewResult.setText("Error");
        }
    }
}