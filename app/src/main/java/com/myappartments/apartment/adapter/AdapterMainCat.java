package com.myappartments.apartment.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappartments.apartment.R;
import com.myappartments.apartment.fragment.FragSmartApp;
import com.myappartments.apartment.fragment.FragmentLaundry;
import com.myappartments.apartment.model.MainCatModel;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMainCat extends RecyclerView.Adapter<AdapterMainCat.MainCatViewHolder> implements View.OnClickListener {

    private Context tContext;
    private List<MainCatModel> tModels;
    private FragmentManager tFragmentManager;


    public AdapterMainCat(Context tContext, List<MainCatModel> tModels, FragmentManager tFragmentManager) {
        this.tContext = tContext;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;

    }

    @Override
    public MainCatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_main_cat_item, parent, false);
        return new MainCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainCatViewHolder holder, final int position) {
        final MainCatModel tModel = tModels.get(position);
        holder.tvMainCat.setText(tModel.getCatName());
        Glide.with(tContext).load(tModel.getImg()).into(holder.ivMainCat);
        holder.ivMainCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (position) {
                    case 0:
                    tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentLaundry.newInstance(tModel.getId().toString())).addToBackStack(null).commit();
                    break;
                    case 5:
                    tFragmentManager.beginTransaction().replace(R.id.container_main, new FragSmartApp()).addToBackStack(null).commit();
                    break;
                    default:
                        CustomToast.tToast(tContext, "Service will start soon...");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tModels.size();
    }

    @Override
    public void onClick(View view) {

    }

    class MainCatViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_main_category)
        TextView tvMainCat;

        @BindView(R.id.iv_main_category)
        ImageView ivMainCat;

        MainCatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
