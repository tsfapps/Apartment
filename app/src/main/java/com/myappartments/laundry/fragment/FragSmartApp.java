package com.myappartments.laundry.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.adapter.AdapterSmartApp;
import com.myappartments.laundry.adapter.AdapterSmartSubBanner;
import com.myappartments.laundry.adapter.AdapterSmartBannerSlider;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelSmartApp;
import com.myappartments.laundry.model.ModelSmartBanner;
import com.myappartments.laundry.model.ModelSmartSubBanner;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragSmartApp extends Fragment {
    private  MainActivity tActivity;
    HashMap<String,String> Hash_file_maps ;
    private AdapterSmartApp tAdapter;
    private AdapterSmartSubBanner tAdapterSubBanner;
    private FragmentManager tFragmentManager;
    private List<ModelSmartApp> tModels;
    private List<ModelSmartBanner> tBannerModels;
    private List<ModelSmartSubBanner> tSubBannerModels;
    private ProgressDialog tDialog;
    @BindView(R.id.rvSmartApp)
    protected RecyclerView rvFragApp;
     @BindView(R.id.rvSmartSubBanner)
    protected RecyclerView rvSmartSubBanner;
    @BindView(R.id.vp_frag_app)
    protected ViewPager vpFragApp;
    @BindView(R.id.indicator_frag_app)
    protected TabLayout indicatorFragApp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_smart_app, container, false);
        ButterKnife.bind(this, view);

        initMain();
        return view;
    }
    private void initMain(){
        RecyclerView.LayoutManager tLayoutManager = new GridLayoutManager(getContext(), 3);
        rvFragApp.setLayoutManager(tLayoutManager);
        tFragmentManager = getFragmentManager();
        RecyclerView.LayoutManager tHorizontal = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvSmartSubBanner.setLayoutManager(tHorizontal);
       tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Popular Apps");
        }
        tDialog = new ProgressDialog(getContext());
        tDialog.setMessage("Loading...");
        tDialog.show();
        new CallApiAsyncTask(tActivity).execute();
        apiBanner();
        indicatorFragApp.setupWithViewPager(vpFragApp, false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 10000, 10000);
        apiSmartSubBanner();
    }

    private void apiBanner(){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelSmartBanner>> call = api.getSmartAppBanner();
        call.enqueue(new Callback<List<ModelSmartBanner>>() {
            @Override
            public void onResponse(Call<List<ModelSmartBanner>> call, Response<List<ModelSmartBanner>> response) {
                tBannerModels = response.body();
                vpFragApp.setAdapter(new AdapterSmartBannerSlider(getContext(), tBannerModels));
            }
            @Override
            public void onFailure(Call<List<ModelSmartBanner>> call, Throwable t) {
                CustomLog.e(Constant.TAG, "Slider Error : "+ t);
            }
        });
    }
    private void apiSmartSubBanner(){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelSmartSubBanner>> call = api.getSmartSubBanner();
        call.enqueue(new Callback<List<ModelSmartSubBanner>>() {
            @Override
            public void onResponse(Call<List<ModelSmartSubBanner>> call, Response<List<ModelSmartSubBanner>> response) {
                tSubBannerModels = response.body();
                tAdapterSubBanner = new AdapterSmartSubBanner(getContext(), tSubBannerModels);
                rvSmartSubBanner.setAdapter(tAdapterSubBanner);


            }

            @Override
            public void onFailure(Call<List<ModelSmartSubBanner>> call, Throwable t) {

            }
        });
    }
    private class CallApiAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity tActivity;


        public CallApiAsyncTask(MainActivity tActivity) {
            this.tActivity = tActivity;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //apiSmartSubBanner();
            Api api = ApiClient.getApiClients().create(Api.class);
            Call<List<ModelSmartApp>> catModelCall = api.getSmartApp();
            catModelCall.enqueue(new Callback<List<ModelSmartApp>>() {
                @Override
                public void onResponse(Call<List<ModelSmartApp>> call, Response<List<ModelSmartApp>> response) {
                    tModels = response.body();
                    tAdapter = new AdapterSmartApp(getContext(), tModels);
                   rvFragApp.setAdapter(tAdapter);
                    tDialog.cancel();
                    rvFragApp.setAdapter(tAdapter);
                }
                @Override
                public void onFailure(Call<List<ModelSmartApp>> call, Throwable t) {
                    CustomLog.d(Constant.TAG, "Not Responding : "+ t);
                    if (tDialog.isShowing()){
                        tDialog.cancel();
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tDialog.cancel();
        }
    }

    private class SliderTimer extends TimerTask {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
           tActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (vpFragApp.getCurrentItem() < tBannerModels.size() - 1) {
                        vpFragApp.setCurrentItem(vpFragApp.getCurrentItem() + 1);
                    } else {
                        vpFragApp.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
