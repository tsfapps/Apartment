package com.myappartments.laundry.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myappartments.laundry.R;
import com.myappartments.laundry.storage.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    private SharedPrefManager tSharedPrefManager;
   // private InitActivity tInitActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tSharedPrefManager = new SharedPrefManager(getApplicationContext());
      //tInitActivity = new InitActivity(getApplicationContext());
        initSplash();
    }

    private void initSplash(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tSharedPrefManager.getUserId().equalsIgnoreCase("")) {
                 startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                  finish();
                }else {
                  //  tInitActivity.initMainActivity();
                    if (tSharedPrefManager.getUserType().equalsIgnoreCase("0")) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, DeliveryBoyActivity.class));

                    }
                    finish();
                }

            }
        }, 2000);
    }
}
