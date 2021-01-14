package com.yat.megaapp.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.megaapp.R;

public class CalculatorActivity extends Fragment implements View.OnClickListener {

    EditText etFirstNumber, etSecondNumber;
    Button btnAddition, btnSubtraction, btnMultiplication, btnDivision, btnResetValues;
    TextView tvResult;
    String stringValueOfNumberOne, stringValueOfNumberTwo, operation;
    double doubleValueOfNumberOne, doubleValueOfNumberTwo;
    boolean clickTwiceToExit = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_calculator, container, false);
        etFirstNumber = v.findViewById(R.id.et_firstNumber);
        etSecondNumber = v.findViewById(R.id.et_secondNumber);
        btnAddition = v.findViewById(R.id.btn_Addition);
        btnSubtraction = v.findViewById(R.id.btn_subtraction);
        btnMultiplication = v.findViewById(R.id.btn_multiplication);
        btnDivision = v.findViewById(R.id.btn_division);
        btnResetValues = v.findViewById(R.id.btn_resetValues);
        tvResult = v.findViewById(R.id.tv_result);
        btnAddition.setOnClickListener(this);
        btnSubtraction.setOnClickListener(this);
        btnMultiplication.setOnClickListener(this);
        btnDivision.setOnClickListener(this);

        return v;
    }
//

    @Override
    public void onClick(View v) {

        //Get the value from the two edit text
        stringValueOfNumberOne = etFirstNumber.getText().toString();
        stringValueOfNumberTwo = etSecondNumber.getText().toString();

        if (stringValueOfNumberOne.isEmpty() || stringValueOfNumberTwo.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter the values required", Toast.LENGTH_SHORT).show();
            resetValues();
        } else {
            doubleValueOfNumberOne = Double.parseDouble(stringValueOfNumberOne);
            doubleValueOfNumberTwo = Double.parseDouble(stringValueOfNumberTwo);
            switch (v.getId()) {

                case R.id.btn_Addition:

                    operation = String.valueOf(doubleValueOfNumberOne + doubleValueOfNumberTwo);
                    tvResult.setText("The Result is : " + operation);
                    break;

                case R.id.btn_subtraction:
//                stringValueOfNumberOne = etFirstNumber.getText().toString();
//                stringValueOfNumberTwo = etSecondNumber.getText().toString();
//                doubleValueOfNumberOne = Double.parseDouble(stringValueOfNumberOne);
//                doubleValueOfNumberTwo = Double.parseDouble(stringValueOfNumberTwo);
                    operation = String.valueOf(doubleValueOfNumberOne - doubleValueOfNumberTwo);
                    tvResult.setText("The Result is : " + operation);
                    break;
                case R.id.btn_multiplication:
//                stringValueOfNumberOne = etFirstNumber.getText().toString();
//                stringValueOfNumberTwo = etSecondNumber.getText().toString();
//                doubleValueOfNumberOne = Double.parseDouble(stringValueOfNumberOne);
//                doubleValueOfNumberTwo = Double.parseDouble(stringValueOfNumberTwo);
                    operation = String.valueOf(doubleValueOfNumberOne * doubleValueOfNumberTwo);
                    tvResult.setText("The Result is : " + operation);
                    break;
                case R.id.btn_division:
//                stringValueOfNumberOne = etFirstNumber.getText().toString();
//                stringValueOfNumberTwo = etSecondNumber.getText().toString();
//                doubleValueOfNumberOne = Double.parseDouble(stringValueOfNumberOne);
//                doubleValueOfNumberTwo = Double.parseDouble(stringValueOfNumberTwo);
                    operation = String.valueOf(doubleValueOfNumberOne / doubleValueOfNumberTwo);
                    tvResult.setText("The Result is : " + operation);
                    break;

                case R.id.btn_resetValues:

                    resetValues();
                    break;

            }
        }
    }

    public void resetValues() {
        etFirstNumber.setText("");
        etSecondNumber.setText("");
        tvResult.setText("Result is : ");
    }
//    @Override
//    public void onBackPressed() {
//
//        buildAlertDialog.setMessage("Do you want to exit ?!")
//                .setIcon(R.drawable.megalogo)
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish(); // close the application
//                        Toast.makeText(MainActivity.this, "See you later", Toast.LENGTH_SHORT).show();
//                    }
//                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel(); //close the dialog interface
//                Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        AlertDialog alertDialog = buildAlertDialog.create();
//        alertDialog.setTitle("Exit");
//        alertDialog.show();
//
//
////        super.onBackPressed();
//    }


//    @Override
//    public void onBackPressed() {
//        if (clickTwiceToExit) {
//            super.onDetach();
//
//        } else {
//            this.clickTwiceToExit = true;  // set the value for true
//
//            Toast.makeText(getActivity(), "Press back again to exit", Toast.LENGTH_SHORT).show();
//
//            // if the user waited more than 3 seconds , set the value false
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    clickTwiceToExit = false;
//                }
//            }, 3000);
//        }
//    }
//
//
}


