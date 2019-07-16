package com.myappartments.laundry.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myappartments.laundry.R;
import com.myappartments.laundry.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppActivity extends AppCompatActivity {
    @BindView(R.id.wv_smart_app)
    protected WebView wvApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(this);
        initAppView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initAppView() {
        String strUrl = getIntent().getStringExtra(Constant.APP_URL);
        wvApp.getSettings().setJavaScriptEnabled(true);
        wvApp.setWebViewClient(new WebViewClient());
        wvApp.getSettings().setSupportZoom(true);
        wvApp.getSettings().setBuiltInZoomControls(true);
        wvApp.getSettings().setDisplayZoomControls(true);
        wvApp.loadUrl(strUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvApp.canGoBack()) {
            wvApp.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {

        if (wvApp.canGoBack()) {
            wvApp.goBack();
        } else {
            finish();
        }
    }
}