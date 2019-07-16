package com.myappartments.laundry.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myappartments.laundry.R;
import com.myappartments.laundry.model.ModelBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBanner extends PagerAdapter {

    private Activity tActivity;
    private List<ModelBanner> tModels;
    @BindView(R.id.iv_water_item)
    protected ImageView ivWaterItem;

    public AdapterBanner(Activity tActivity, List<ModelBanner> tModels) {
        this.tActivity = tActivity;
        this.tModels = tModels;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = tActivity.getLayoutInflater();
        View view = inflater.inflate(R.layout.frag_banner, container, false);
        ButterKnife.bind(this, view);
        Glide.with(tActivity).load(tModels.get(position).getImage()).into(ivWaterItem);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return tModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);

        container.removeView((View)object);
    }
}
