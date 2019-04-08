package com.myappartments.apartment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelSmartSubBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSmartSubBanner extends RecyclerView.Adapter<AdapterSmartSubBanner.SmartViewHolder> {

    private Context tContext;
    private List<ModelSmartSubBanner> tModels;

    public AdapterSmartSubBanner(Context tContext, List<ModelSmartSubBanner> tModels) {
        this.tContext = tContext;
        this.tModels = tModels;
    }

    @NonNull
    @Override
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_smart_sub_banner_item, viewGroup, false);
        return new SmartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartViewHolder smartViewHolder, int i) {
        ModelSmartSubBanner tModel = tModels.get(i);
        Glide.with(tContext)
                .load(tModel.getImg())
                .placeholder(R.drawable.logo_main)
                .into(smartViewHolder.ivSmartAppSubBanner);



    }

    @Override
    public int getItemCount() {
        return tModels.size();
    }

    class SmartViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_smart_sub_banner)
        ImageView ivSmartAppSubBanner;
        SmartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
