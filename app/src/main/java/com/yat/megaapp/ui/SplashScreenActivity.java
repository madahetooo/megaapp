package com.yat.megaapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yat.megaapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Objects.requireNonNull(getSupportActionBar()).hide(); //Hiding the toolbar

        //Creating new thread for sleep
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);  //Sleeping for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Going to Main Activity
                Intent goToMainActivity = new Intent(SplashScreenActivity.this, IntroActivity.class);
                startActivity(goToMainActivity);
                finish();
            }
        }).start();

    }
}