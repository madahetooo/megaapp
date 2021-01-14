package com.yat.megaapp.utils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yat.megaapp.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button btn_resetPassword;
    EditText et_RecoveryMail;
    String recoveryMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btn_resetPassword = findViewById(R.id.btn_resetPassword);
        et_RecoveryMail = findViewById(R.id.et_recoveryMail);

        btn_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoveryMail = et_RecoveryMail.getText().toString().trim();  //Getting string value from the edit text

                //checking if the value is empty
                if (recoveryMail.isEmpty()) {
                    Toast.makeText(ForgetPasswordActivity.this, "Please enter your recovery Mail", Toast.LENGTH_SHORT).show();
                }
                //Sending the resetting link to the recovery mail
                else {
                    Toast.makeText(ForgetPasswordActivity.this, " Done => Please check " + recoveryMail, Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}