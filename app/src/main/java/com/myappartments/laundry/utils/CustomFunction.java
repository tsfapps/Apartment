package com.myappartments.laundry.utils;

import android.widget.Button;

public class CustomFunction {

    public  static void callButton(Button tButton, String btnText, boolean btnEnable, int btnBackround){

        tButton.setText(btnText);
        tButton.setEnabled(btnEnable);
        tButton.setBackgroundResource(btnBackround);

    }
}
