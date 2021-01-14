package com.yat.megaapp.entertainment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yat.megaapp.R;

public class DogOrCatQuiz extends AppCompatActivity {
    RadioGroup radioGroupQuestion1, radioGroupQuestion2, radioGroupQuestion3;
    RadioButton radioButtonQuestion1, radioButtonQuestion2, radioButtonQuestion3;
    TextView txShowQuizResult;
    Button btnGetResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_or_cat_quiz);
        radioGroupQuestion1 = findViewById(R.id.rgQuestion1);
        radioGroupQuestion2 = findViewById(R.id.rgQuestion2);
        radioGroupQuestion3 = findViewById(R.id.rgQuestion3);
        radioButtonQuestion1 =findViewById(R.id.rbNever);

        txShowQuizResult = findViewById(R.id.tx_quizResult);
        btnGetResult = findViewById(R.id.btn_getResult);
        btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRadioButton = radioGroupQuestion1.getCheckedRadioButtonId();  // get the radio button which checked

                radioButtonQuestion1 = findViewById(selectedRadioButton);  // pass checked radio button to an object


                int selectedRadioButton2 = radioGroupQuestion2.getCheckedRadioButtonId();

                radioButtonQuestion2 = findViewById(selectedRadioButton2);

                int selectedRadioButton3 = radioGroupQuestion3.getCheckedRadioButtonId();

                radioButtonQuestion3 = findViewById(selectedRadioButton3);


                if (radioGroupQuestion1.getCheckedRadioButtonId() == -1 || radioGroupQuestion2.getCheckedRadioButtonId() == -1 ||
                        radioGroupQuestion3.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please Choose Choice", Toast.LENGTH_SHORT).show();
                } else {
                    if (radioButtonQuestion1.getText().toString().toUpperCase().equals("NEVER") &&
                            radioButtonQuestion2.getText().toString().toUpperCase().equals("ALL THE TIME") &&
                            radioButtonQuestion3.getText().toString().toUpperCase().equals("DAILY")) {
                        txShowQuizResult.setText("You Are A Cat Person");
                    } else if (radioButtonQuestion1.getText().toString().toUpperCase().equals("ALWAYS") &&
                            radioButtonQuestion2.getText().toString().toUpperCase().equals("NOT PHYSICAL PERSON") &&
                            radioButtonQuestion3.getText().toString().toUpperCase().equals("FROM TIME TO TIME")) {
                        txShowQuizResult.setText("You Are A DOG Person");
                    } else if (radioButtonQuestion1.getText().toString().toUpperCase().equals("SOME TIMES") &&
                            radioButtonQuestion2.getText().toString().toUpperCase().equals("OCCASIONALLY") &&
                            radioButtonQuestion3.getText().toString().toUpperCase().equals("WEEKLY")) {
                        txShowQuizResult.setText("You Are A CAT Person");
                    }
                }


            }
        });
    }
}