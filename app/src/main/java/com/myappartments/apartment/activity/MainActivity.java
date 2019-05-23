package com.myappartments.apartment.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.myappartments.apartment.R;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.fragment.FragmentLaundry;
import com.myappartments.apartment.fragment.FragmentMainCat;
import com.myappartments.apartment.fragment.FragmentOrder;
import com.myappartments.apartment.fragment.FragmentProfile;
import com.myappartments.apartment.model.ModelToken;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;
import com.myappartments.apartment.utils.CustomToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.container_main)
    protected FrameLayout containerMain;
   @BindView(R.id.tvToolbar)
   protected TextView tvToolbar;
   @BindView(R.id.drawer_layout)
   protected DrawerLayout drawer;
   @BindView(R.id.nav_view)
   protected NavigationView navigationView;
    private ProgressDialog mDialog;
    public UIThreadHandler uiThreadHandler = null;
   private SharedPrefManager tSharedPrefManager;
    private ProgressDialog tDialog;
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
        TextView tvNavHeader = navigationView.getHeaderView(0).findViewById(R.id.tv_nav_header);
        tvNavHeader.setText(tSharedPrefManager.getUserFlat());
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentMainCat()).commit();
        uiThreadHandler = new UIThreadHandler();
initMain();
    }


    public void setTextToolbar(String strTextToolbar) {
        tvToolbar.setText(strTextToolbar);
    }
    private void initMain(){

        tDialog = new ProgressDialog(this);
        tDialog.setMessage("Loading...");
        tDialog.show();

    }

    private void initFireBase(){
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(Constant.TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        String userId = tSharedPrefManager.getUserId();
                        String token = task.getResult().getToken();

                        Api api = ApiClient.getApiClients().create(Api.class);
                        Call<ModelToken> call = api.sendToken(token, userId);
                        call.enqueue(new Callback<ModelToken>() {
                            @Override
                            public void onResponse(Call<ModelToken> call, Response<ModelToken> response) {
                                ModelToken tModelToken = response.body();
                                CustomLog.d(Constant.TAG, "Responding : "+tModelToken.getMessage());
                                tDialog.cancel();
                            }
                            @Override
                            public void onFailure(Call<ModelToken> call, Throwable t) {
                                CustomLog.d(Constant.TAG, "Not Responding"+t);

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_exit) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

           getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentProfile()).addToBackStack(null).commit();

        } else if (id == R.id.nav_water) {
           // getSupportFragmentManager().beginTransaction().replace(R.id.container_main, FragmentSubCat.newInstance("1")).addToBackStack(null).commit();
            CustomToast.tToast(this, "Service will start soon...");
        } else if (id == R.id.nav_laundry) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentLaundry()).addToBackStack(null).commit();
        } else if (id == R.id.nav_car) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, FragmentSubCat.newInstance("3")).addToBackStack(null).commit();
            CustomToast.tToast(this, "Service will start soon...");

        } else if (id == R.id.nav_fresh) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, FragmentSubCat.newInstance("4")).addToBackStack(null).commit();
            CustomToast.tToast(this, "Service will start soon...");

        }else if (id == R.id.nav_orders) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FragmentOrder()).addToBackStack(null).commit();

        } else if (id == R.id.nav_share) {

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
