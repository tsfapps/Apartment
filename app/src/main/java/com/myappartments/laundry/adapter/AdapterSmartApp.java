package com.myappartments.laundry.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.AppActivity;
import com.myappartments.laundry.model.ModelSmartApp;
import com.myappartments.laundry.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSmartApp extends RecyclerView.Adapter<AdapterSmartApp.SmartViewHolder> {

    private Context tContext;
    private List<ModelSmartApp> tModels;

    public AdapterSmartApp(Context tContext, List<ModelSmartApp> tModels) {
        this.tContext = tContext;
        this.tModels = tModels;
    }

    @NonNull
    @Override
    public SmartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_smart_app_item, viewGroup, false);
        return new SmartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartViewHolder smartViewHolder, int i) {
        ModelSmartApp tModel = tModels.get(i);
        final String strUrl = tModel.getLink();
        Glide.with(tContext).load(tModel.getImg()).into(smartViewHolder.ivSmartApp);
        smartViewHolder.tvSmartApp.setText(tModel.getName());
        smartViewHolder.ivSmartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tContext, AppActivity.class);
                intent.putExtra(Constant.APP_URL, strUrl);
                tContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tModels.size();
    }

    class SmartViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_smartApp)
        ImageView ivSmartApp;
         @BindView(R.id.tv_smartAPp)
         TextView tvSmartApp;
        SmartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
