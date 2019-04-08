package com.myappartments.apartment.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.adapter.AdapterMainCat;
import com.myappartments.apartment.adapter.AdapterSlider;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.MainCatModel;
import com.myappartments.apartment.model.ModelBanner;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMainCat extends Fragment{
    HashMap<String,String> Hash_file_maps ;
    private AdapterMainCat tAdapter;
    private FragmentManager tFragmentManager;
    private List<MainCatModel> tModels;
    private List<ModelBanner> tBannerModels;
    private ProgressDialog tDialog;
    @BindView(R.id.rvMainCat)
    protected RecyclerView rvMainCat;
     @BindView(R.id.viewPager)
    protected ViewPager viewPager;
     @BindView(R.id.indicator)
    protected TabLayout indicator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_cat, container, false);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager tLayoutManager = new GridLayoutManager(getContext(), 2);
        rvMainCat.setLayoutManager(tLayoutManager);
        tFragmentManager = getFragmentManager();

        initMain();



        return view;
    }
    private void initMain(){
        MainActivity tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("Apartment Services");
        }
        tDialog = new ProgressDialog(getContext());
        tDialog.setMessage("Loading...");
        tDialog.show();
        new CallApiAsyncTask(tActivity).execute();
        apiBanner();
        indicator.setupWithViewPager(viewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 10000, 10000);
    }

    private void apiBanner(){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelBanner>> call = api.getBanner("2");
        call.enqueue(new Callback<List<ModelBanner>>() {
            @Override
            public void onResponse(Call<List<ModelBanner>> call, Response<List<ModelBanner>> response) {
                tBannerModels = response.body();
                viewPager.setAdapter(new AdapterSlider(getContext(), tBannerModels));
                CustomLog.e(Constant.TAG, "Slider Responding");
            }
            @Override
            public void onFailure(Call<List<ModelBanner>> call, Throwable t) {
CustomLog.e(Constant.TAG, "Slider Error : "+ t);
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
            Api api = ApiClient.getApiClients().create(Api.class);
            Call<List<MainCatModel>> catModelCall = api.mainCate();
            catModelCall.enqueue(new Callback<List<MainCatModel>>() {
                @Override
                public void onResponse(Call<List<MainCatModel>> call, Response<List<MainCatModel>> response) {
                    tModels = response.body();
                    tAdapter = new AdapterMainCat(getContext(), tModels, tFragmentManager);
                    CustomLog.d(Constant.TAG, "Responding : "+tModels.get(2).getCatName());
                    tDialog.cancel();
                    rvMainCat.setAdapter(tAdapter);
                }
                @Override
                public void onFailure(Call<List<MainCatModel>> call, Throwable t) {
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
            try {
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < tBannerModels.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            } catch (Exception e) {
                CustomLog.d(Constant.TAG, "View Pager : "+e);
            }
        }
    }
}
