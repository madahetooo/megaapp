package com.yat.megaapp.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.yat.megaapp.ui.DrawerActivity;
import com.yat.megaapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tv_forgetPassword, tv_registerNow;
    Button btn_login;
    AlertDialog.Builder buildAlertDialog;
    EditText etUserName, etPassword;
    CheckBox chkRememberMe;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    boolean clickTwiceToExit = false;
    private FirebaseUser currentUser;
    private SignInButton mSignInButton;

    private GoogleSignInClient mSignInClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chkRememberMe = findViewById(R.id.chkRememberMe);
        buildAlertDialog = new AlertDialog.Builder(this);
        mAuth = FirebaseAuth.getInstance();
        tv_forgetPassword = findViewById(R.id.tv_forgetPassword);
        tv_registerNow = findViewById(R.id.tv_registerNow);
        btn_login = findViewById(R.id.btn_login);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        tv_forgetPassword.setPaintFlags(tv_forgetPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_registerNow.setPaintFlags(tv_registerNow.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Going to Registration Activity
                Intent goToRegistration = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(goToRegistration);
            }
        });
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        // Set click listeners
        mSignInButton.setOnClickListener(this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailAddress = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (emailAddress.isEmpty() && !password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                }
                if (!emailAddress.isEmpty() && password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                if (emailAddress.isEmpty() && password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                }
                if (!emailAddress.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Welcome to our Mega APP", Toast.LENGTH_SHORT).show();
                                Intent goToHomeActivity = new Intent(getApplicationContext(), DrawerActivity.class);
                                startActivity(goToHomeActivity);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }


            }
        });

        tv_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Going to Forget Password Activity
                Intent goToForgetPasswordActivity = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(goToForgetPasswordActivity);
            }
        });


        SharedPreferences rememberMeFile = getSharedPreferences("rememberMeFile", 0);
        if (rememberMeFile.getBoolean("bool", true)) {
            String emailAddress = rememberMeFile.getString("username", "");
            etUserName.setText(emailAddress);

            String password = rememberMeFile.getString("password", "");
            etPassword.setText(password);
            chkRememberMe.setChecked(true);
        }
    }


    public void rememberMe(View view) {

        //Creating the xml SharedPreferences File
        SharedPreferences rememberMeFile = getSharedPreferences("rememberMeFile", 0);
        //Creating the pen to edit inside the rememberMe file
        SharedPreferences.Editor pen = rememberMeFile.edit();

        //Adding String values to the rememberMefile
        pen.putString("username", etUserName.getText().toString().trim());
        pen.putString("password", etPassword.getText().toString().trim());
        pen.putBoolean("bool", true);
        pen.apply();

        if (chkRememberMe.isChecked()) {
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        }

    }


    //    @Override
    public void onBackPressed() {
        if (clickTwiceToExit) {  //check if the value is true
            super.onBackPressed(); // close the app
        } else {
            this.clickTwiceToExit = true;  // set the value for true

            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            // if the user waited more than 3 seconds , set the value false
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickTwiceToExit = false;
                }
            }, 3000);
        }
        //show toast to let the user click again quickly
    }

    @Override
    protected void onStart() {
        mAuth.addAuthStateListener(authStateListener);
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();

                break;
        }
    }
    private void signIn() {
        Intent signInIntent = mSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,9001);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent in signIn()
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }
}