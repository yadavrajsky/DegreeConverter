package com.unit.degreeconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText inputEditText;
    private TextView resultTextView;
    private Spinner conversionTypeSpinner;
    private Button convertButton;

    private String[] conversionTypes = {"Celsius to Fahrenheit", "Fahrenheit to Celsius", "Miles to Kilometers", "Kilometers to Miles"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        resultTextView = findViewById(R.id.resultTextView);
        conversionTypeSpinner = findViewById(R.id.conversionTypeSpinner);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, conversionTypes);
        conversionTypeSpinner.setAdapter(adapter);
        conversionTypeSpinner.setOnItemSelectedListener(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void performConversion() {
        String input = inputEditText.getText().toString().trim();
        if (!input.isEmpty()) {
            double inputValue = Double.parseDouble(input);
            String conversionType = conversionTypeSpinner.getSelectedItem().toString();

            double result;
            String resultString;

            switch (conversionType) {
                case "Celsius to Fahrenheit":
                    result = (inputValue * 9 / 5) + 32;
                    resultString = String.format("%.2f °F", result);
                    break;
                case "Fahrenheit to Celsius":
                    result = (inputValue - 32) * 5 / 9;
                    resultString = String.format("%.2f °C", result);
                    break;
                case "Miles to Kilometers":
                    result = inputValue * 1.60934;
                    resultString = String.format("%.2f km", result);
                    break;
                case "Kilometers to Miles":
                    result = inputValue / 1.60934;
                    resultString = String.format("%.2f mi", result);
                    break;
                default:
                    resultString = "Invalid conversion type";
            }

            resultTextView.setText(resultString);
        } else {
            resultTextView.setText("Please enter a valid input");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        resultTextView.setText("");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }
}
