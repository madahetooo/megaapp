package com.yat.megaapp.utils;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yat.megaapp.ui.DrawerActivity;
import com.yat.megaapp.R;

public class RegistrationActivity extends AppCompatActivity {
    TextView login;
    Button register;
    EditText etFullName, etEmailAddress, etPhoneNumber, etPassword, etConfirmPassword;
    String fullName, emailAddress, phoneNumber, password, confirmPassword;
    AwesomeValidation awesomeValidation;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference ;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        login = findViewById(R.id.tv_login);
        register = findViewById(R.id.btn_register);
        etFullName = findViewById(R.id.etFullName);
        etEmailAddress = findViewById(R.id.etEmaillAddress);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
         awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.etEmaillAddress, Patterns.EMAIL_ADDRESS, R.string.valid_email_address);
        awesomeValidation.addValidation(this, R.id.etPhoneNumber, "^[+]?[0-9]{10,13}$", R.string.valid_phone_number);
        awesomeValidation.addValidation(this, R.id.etPassword, "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", R.string.valid_password);
        awesomeValidation.addValidation(this, R.id.etConfirmPassword, R.id.etPassword, R.string.valid_confirm_password);
        awesomeValidation.addValidation(this, R.id.etFullName, RegexTemplate.NOT_EMPTY, R.string.valid_full_name);

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        login.setPaintFlags(login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Going to Login Activity
                Intent goToLogin = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(goToLogin);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = etFullName.getText().toString().trim();
                emailAddress = etEmailAddress.getText().toString().trim();
                phoneNumber = etPhoneNumber.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();

                if (awesomeValidation.validate()) {
                    mAuth.createUserWithEmailAndPassword(emailAddress, password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        user = task.getResult().getUser();
                                        UserProfileChangeRequest ProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(etFullName.getText().toString())
                                                .build();
                                        user.updateProfile(ProfileChangeRequest);
                                        DatabaseReference newUser = databaseReference.child(user.getUid());
                                        newUser.child("Full Name").setValue(fullName);
                                        newUser.child("Email Address").setValue(emailAddress);
                                        newUser.child("Phone Number").setValue(phoneNumber);
                                        newUser.child("Password").setValue(password);

                                        Toast.makeText(RegistrationActivity.this, "Registration Successfully", Toast.LENGTH_LONG).show();
                                        Intent goToLogin = new Intent(RegistrationActivity.this, DrawerActivity.class);
                                        startActivity(goToLogin);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });


                }


            }
        });

    }
//
//    //validation for phone
//    private boolean isValidPhone(String phone) {
//        boolean valid = false;
//        String PHONE_PATTERN = "^[+]?[0-9]{10,13}$";
//        Pattern pattern = Pattern.compile(PHONE_PATTERN, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(phone);
//        if (matcher.matches()) valid = true;
//        return valid;
//    }
//
//    // validation for password
//    private boolean isValidPassword(String pass) {
//        boolean valid = false;
//        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
//        Pattern pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(pass);
//        if (matcher.matches()) valid = true;
//        return valid;
//    }
//
//    // validation for email
//    private boolean isValidEmail(String email) {
//        boolean isValid = false;
//        String EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,255}$";
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(email);
//        if (matcher.matches()) {
//            isValid = true;
//        }
//        return isValid;
//    }
//
//    //setError
//    public boolean isValid(String st_email, String st_password, String st_confirmPassword, String stPhoneNumber) {
//        boolean valid = true;
//        if (!isValidEmail(st_email)) {
//            etEmailAddress.setError("Enter valid Email");
//            valid = false;
//        }
//        if (!st_password.equals(st_confirmPassword)) {
//            etConfirmPassword.setError("Password not matched");
//            valid = false;
//        }
//        if (!isValidPassword(st_password)) {
//            etPassword.setError("Use Numbers Upper and Lower case");
//            valid = false;
//        }
//        if (!isValidPhone(stPhoneNumber)) {
//            etPhoneNumber.setError("Enter valid phone number");
//            valid = false;
//        }
//
//        return valid;
//    }
}