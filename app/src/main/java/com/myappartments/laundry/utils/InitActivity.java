package com.myappartments.laundry.utils;

import android.content.Context;
import android.content.Intent;

import com.myappartments.laundry.activity.LoginActivity;
import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.activity.RegisterActivity;
import com.myappartments.laundry.activity.SplashActivity;

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
