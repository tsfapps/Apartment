package com.myappartments.apartment.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myappartments.apartment.R;
import com.myappartments.apartment.storage.SharedPrefApart;
import com.myappartments.apartment.utils.InitActivity;

public class SplashActivity extends AppCompatActivity {

    private SharedPrefApart tPref;
   // private InitActivity tInitActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tPref = new SharedPrefApart(getApplicationContext());
      //  tInitActivity = new InitActivity(getApplicationContext());
        initSplash();
    }

    private void initSplash(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tPref.getUserId().equalsIgnoreCase("")) {
                 startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                  finish();
                }else {
                  //  tInitActivity.initMainActivity();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

            }
        }, 2000);
    }


}
