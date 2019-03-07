package com.myappartments.apartment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappartments.apartment.R;
import com.myappartments.apartment.fragment.FragmentDetails;
import com.myappartments.apartment.model.ModelSubCat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSubCat extends RecyclerView.Adapter<AdapterSubCat.WaterViewHolder> {

    private Context tCtx;
    private List<ModelSubCat> tModels;
    private FragmentManager tFragmentManager;

    public AdapterSubCat(Context tCtx, List<ModelSubCat> tModels,  FragmentManager tFragmentManager) {
        this.tCtx = tCtx;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;
    }

    @NonNull
    @Override
    public WaterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_sub_cat, viewGroup, false);
        return new WaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterViewHolder waterViewHolder, int i) {
        final ModelSubCat tModel = tModels.get(i);

        final String strId = tModel.getCatId();
        String strSubCatName = tModel.getCategoryName();
        waterViewHolder.tvSubCat.setText(strSubCatName);
        Glide.with(tCtx).load(tModel.getCategoryImage()).into(waterViewHolder.ivSubCat);
        waterViewHolder.ivSubCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentDetails.newInstance(tCtx, tModel)).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tModels.size();
    }

    public class WaterViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_sub_cat)
        protected ImageView ivSubCat;
        @BindView(R.id.tv_sub_cat)
        protected TextView tvSubCat;

        public WaterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
