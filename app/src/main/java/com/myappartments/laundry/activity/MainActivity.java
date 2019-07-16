package com.myappartments.laundry.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.myappartments.laundry.R;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.fragment.FragmentCartView;
import com.myappartments.laundry.fragment.FragmentContact;
import com.myappartments.laundry.fragment.FragmentLaundry;
import com.myappartments.laundry.fragment.FragmentMainCat;
import com.myappartments.laundry.fragment.FragmentOrderList;
import com.myappartments.laundry.fragment.FragmentProfile;
import com.myappartments.laundry.fragment.FragmentWallet;
import com.myappartments.laundry.model.ModelToken;
import com.myappartments.laundry.model.ModelWallet;
import com.myappartments.laundry.presenter.WalletPresenter;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MIXPANEL_TOKEN = "8e610bc0e5968777aca284dc17bf8350";

    String strUserType;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.container_main)
    protected FrameLayout containerMain;
   @BindView(R.id.tvToolbar)
   protected TextView tvToolbar;
   @BindView(R.id.tvToolbarWallet)
   protected TextView tvToolbarWallet;
   @BindView(R.id.drawer_layout)
   protected DrawerLayout drawer;
   @BindView(R.id.nav_view)
   protected NavigationView navigationView;
    private ProgressDialog mDialog;
    public UIThreadHandler uiThreadHandler = null;
   private SharedPrefManager tSharedPrefManager;
    private String strDbUserId;
    private String strDbServiceType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        initFireBase();

        tSharedPrefManager = new SharedPrefManager(this);
        callApiWallet();

        TextView tvNavHeader = navigationView.getHeaderView(0).findViewById(R.id.tv_nav_header);
        tvNavHeader.setText(tSharedPrefManager.getUserFlat());


        strUserType = tSharedPrefManager.getUserType();
        strDbUserId = getIntent().getStringExtra(Constant.DB_USER_ID);
        strDbServiceType = getIntent().getStringExtra(Constant.DB_SERVICE_TYPE);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentMainCat()).commit();

        if (strUserType.equals("0")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentMainCat()).commit();
        }
        else{
            if (strDbServiceType.equalsIgnoreCase("My Orders")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, FragmentOrderList.newInstance(strDbUserId)).commit();
            }
            else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, FragmentLaundry.newInstance("1",strDbUserId)).commit();
            }

        }
        uiThreadHandler = new UIThreadHandler();
        initMain();
    }


    @OnClick(R.id.tvToolbarWallet)
    public void onWalletClick(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentWallet()).addToBackStack(null).commit();

    }
    public void setTextToolbar(String strTextToolbar) {
        tvToolbar.setText(strTextToolbar);
    }
    private void initMain(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("MyNotifications", "MyNotifications" ,NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }
    private void callApiWallet(){
        String strUserId = tSharedPrefManager.getUserId();
        WalletPresenter.callApiWallet(strUserId, MainActivity.this);
    }

    public void onResponseApiWallet(Response<ModelWallet> response){
        ModelWallet tModel = response.body();
        tSharedPrefManager.clearWallet();
        tSharedPrefManager.setUserWallet(tModel.getWallet());
        tvToolbarWallet.setText(tModel.getWallet());
    }
    public void onFailureApiWallet(Call<ModelWallet> call){

    }

    private void initFireBase(){

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
//                        String userId = tSharedPrefManager.getUserId();
                        String strTokenKey = task.getResult().getToken();
                        CustomLog.d(Constant.TAG, "Token Key :"+strTokenKey);

                        Api api = ApiClient.getApiClients().create(Api.class);
                        Call<ModelToken> call = api.sendToken(strTokenKey);
                        call.enqueue(new Callback<ModelToken>() {
                            @Override
                            public void onResponse(Call<ModelToken> call, Response<ModelToken> response) {
                                ModelToken tModelToken = response.body();
                                CustomLog.d(Constant.TAG, "Responding"+tModelToken.getMessage());

                            }
                            @Override
                            public void onFailure(Call<ModelToken> call, Throwable t) {
                                CustomLog.d(Constant.TAG, "Token key Not Responding"+t);
                            }
                        });
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            if (strUserType.equalsIgnoreCase("0")) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constant.DB_USER_ID, strDbUserId);
                startActivity(intent);
                finishAffinity();
            }
            else {
                Intent intent = new Intent(this, DeliveryBoyActivity.class);
                startActivity(intent);
                finishAffinity();
            }
//           getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentMainCat()).addToBackStack(null).commit();

        }        if (id == R.id.nav_profile) {

           getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentProfile()).addToBackStack(null).commit();

        } else if (id == R.id.nav_wallet) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentWallet()).addToBackStack(null).commit();
        } else if (id == R.id.nav_cart) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, FragmentCartView.newInstance(strDbUserId)).addToBackStack(null).commit();
        } else if (id == R.id.nav_contact) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentContact()).addToBackStack(null).commit();

        } else if (id == R.id.nav_rate) {
            try{
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id="+getPackageName())));
            }
            catch (ActivityNotFoundException e){
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
            }
        }else if (id == R.id.nav_orders) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, FragmentOrderList.newInstance(strDbUserId)).addToBackStack(null).commit();

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Download our app and order for laundry service in few click...\n"+"https://play.google.com/store/apps/details?id="+getPackageName());
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } else if (id == R.id.nav_logout) {

            SharedPrefManager.getInstance(getApplicationContext()).clearUserData();
            Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class UIThreadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.SHOW_PROGRESS_DIALOG:
                    showProgressDialog();
                    break;
                case Constant.HIDE_PROGRESS_DIALOG:
                    hideProgressDialog();
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Loading....");
        mDialog.show();
    }
}
