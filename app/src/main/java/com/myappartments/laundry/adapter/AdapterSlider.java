package com.myappartments.laundry.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappartments.laundry.R;
import com.myappartments.laundry.fragment.FragmentMainCat;
import com.myappartments.laundry.model.ModelBanner;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdapterSlider extends PagerAdapter {
    private Context context;
    private List<ModelBanner> tBannerModels;

    //gloabals
    final Handler handler = new Handler();
    public Timer swipeTimer ;



    public AdapterSlider(Context context, List<ModelBanner> tBannerModels) {
        this.context = context;
        this.tBannerModels = tBannerModels;
    }

    @Override
    public int getCount() {
        return tBannerModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slider, null);

        ModelBanner tModel = tBannerModels.get(position);

        ImageView imageView = view.findViewById(R.id.iv_slider_main);
        Glide.with(context).load(tModel.getImage()).into(imageView);


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

    public void setTimer(final ViewPager myPager, int time){
        final int size =tBannerModels.size();

        final Runnable Update = new Runnable() {
            int NUM_PAGES =size;
            int currentPage = 0 ;
            public void run() {
                if (currentPage == NUM_PAGES ) {
                    currentPage = 0;
                }
                myPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, time*1000);
    }}
