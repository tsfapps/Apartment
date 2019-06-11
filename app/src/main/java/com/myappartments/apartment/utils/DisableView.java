package com.myappartments.apartment.utils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.myappartments.apartment.R;

public class DisableView {

    public static void disableEditText(EditText tEditText){
        tEditText.setFocusable(false);
        tEditText.setEnabled(false);
        tEditText.setCursorVisible(false);
        tEditText.setKeyListener(null);
        tEditText.setBackgroundResource(R.drawable.bg_et_disabled);
        tEditText.setText("Na");
    }
    public static void disableButton(Button tButton){
        tButton.setEnabled(false);
        tButton.setVisibility(View.GONE);
    }
    public static void disableTextView(TextView tTextView){
        tTextView.setText("Na");
    }
    public static void disableSpinner(Spinner tSpinner){
       tSpinner.setEnabled(false);
       tSpinner.setBackgroundResource(R.drawable.bg_et_disabled);
    }
}
