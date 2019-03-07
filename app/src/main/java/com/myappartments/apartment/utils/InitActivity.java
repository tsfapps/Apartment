package com.myappartments.apartment.utils;

import android.content.Context;
import android.content.Intent;

import com.myappartments.apartment.activity.LoginActivity;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.activity.RegisterActivity;
import com.myappartments.apartment.activity.SplashActivity;

public class InitActivity {

    private Context tCtx;

    public InitActivity(Context tCtx) {
        this.tCtx = tCtx;
    }

    public void initLoginActivity(){
        tCtx.startActivity(new Intent(tCtx, LoginActivity.class));
    }
    public void initMainActivity(){
        tCtx.startActivity(new Intent(tCtx, MainActivity.class));

    }
    public void initRegisterActivity(){
        tCtx.startActivity(new Intent(tCtx, RegisterActivity.class));

    }
    public void initSplashActivity(){
        tCtx.startActivity(new Intent(tCtx, SplashActivity.class));

    }
}
