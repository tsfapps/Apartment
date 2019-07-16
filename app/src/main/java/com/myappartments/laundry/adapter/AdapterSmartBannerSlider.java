package com.myappartments.laundry.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myappartments.laundry.R;
import com.myappartments.laundry.model.ModelSmartBanner;

import java.util.List;

public class AdapterSmartBannerSlider extends PagerAdapter {

    private Context context;
    private List<Integer> color;
    private List<String> colorName;
    private List<ModelSmartBanner> tBannerModels;


    public AdapterSmartBannerSlider(Context context, List<ModelSmartBanner> tBannerModels) {
        this.context = context;
        this.tBannerModels = tBannerModels;
        this.colorName = colorName;
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

        ModelSmartBanner tModel = tBannerModels.get(position);

        ImageView imageView = view.findViewById(R.id.iv_slider_main);
        Glide.with(context).load(tModel.getImg()).into(imageView);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }}
