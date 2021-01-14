package com.yat.megaapp.tools;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.yat.megaapp.R;

public class TemperatureConverter extends Fragment implements View.OnClickListener {

    EditText etFahrenheit, etCelsius;
    Button btnConvert, btnResetValues;
    String etFahrenheitString, etCelsiusString;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_temperature_converter, container, false);
        etFahrenheit = v.findViewById(R.id.etFahrenheit);
        etCelsius = v.findViewById(R.id.etCelsius);
        btnConvert = v.findViewById(R.id.btnConvert);
        btnResetValues = v.findViewById(R.id.btn_resetTemperatureValues);
        btnConvert.setOnClickListener(this);
        btnResetValues.setOnClickListener(this);

        return v;
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnConvert:
                etFahrenheitString = etFahrenheit.getText().toString().trim();
                etCelsiusString = etCelsius.getText().toString().trim();

                if (!etFahrenheitString.isEmpty() && etCelsiusString.isEmpty()) {
                    //Converting from fahrenheit to celsius
                    double convertedValue = Double.parseDouble(etFahrenheitString);
                    String result = String.valueOf((convertedValue - 32) / 1.8);
                    etCelsius.setText(result);
                } else if (etFahrenheitString.isEmpty() && !etCelsiusString.isEmpty()) {
                    //Converting from celsius to Fahrenheit
                    double convertedValue = Double.parseDouble(etCelsiusString);
                    String result = String.valueOf((convertedValue * 1.8) + 32);
                    etFahrenheit.setText(result);

                } else if (!etFahrenheitString.isEmpty() && !etCelsiusString.isEmpty()) {
                    Toast.makeText(getContext(), "Please click on reset values", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please enter values", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_resetTemperatureValues:

                if (etFahrenheitString.isEmpty() && etCelsiusString.isEmpty()) {
                    Toast.makeText(getContext(), "There is no data to reset", Toast.LENGTH_SHORT).show();
                } else {
                    etFahrenheit.setText("");
                    etCelsius.setText("");
                }
                break;

        }
    }
}