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
import com.myappartments.apartment.fragment.FragmentLaundry;
import com.myappartments.apartment.fragment.FragmentSubCat;
import com.myappartments.apartment.model.MainCatModel;

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
    public void onBindViewHolder(final MainCatViewHolder holder, int position) {
        MainCatModel mainCatModelMl = tModels.get(0);
        MainCatModel mainCatModelMw = tModels.get(1);
        MainCatModel mainCatModelCw = tModels.get(2);
        MainCatModel mainCatModelMf = tModels.get(3);
        final String mwId = String.valueOf(mainCatModelMw.getId());
        final String mlId = String.valueOf(mainCatModelMl.getId());
        final String cwId = String.valueOf(mainCatModelCw.getId());
        final String mfId = String.valueOf(mainCatModelMf.getId());

        holder.tvMainCatMw.setText(mainCatModelMw.getCatName());
        Glide.with(tContext).load(mainCatModelMw.getImg()).into(holder.ivMainCatMw);

        holder.ivMainCatMw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentLaundry()).addToBackStack(null).commit();
            }
        });

        holder.tvMainCatMl.setText(mainCatModelMl.getCatName());
        Glide.with(tContext).load(mainCatModelMl.getImg()).into(holder.ivMainCatMl);

        holder.ivMainCatMl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tFragmentManager.beginTransaction().replace(R.id.container_main,FragmentSubCat.newInstance(mlId) ).addToBackStack(null).commit();
            }
        });
        holder.tvMainCatCw.setText(mainCatModelCw.getCatName());
        Glide.with(tContext).load(mainCatModelCw.getImg()).into(holder.ivMainCatCw);

        holder.ivMainCatCw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentSubCat.newInstance(cwId)).addToBackStack(null).commit();
            }
        });
        holder.tvMainCatMf.setText(mainCatModelMf.getCatName());
        Glide.with(tContext).load(mainCatModelMf.getImg()).into(holder.ivMainCatMf);
        holder.ivMainCatMf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentSubCat.newInstance(mfId)).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onClick(View view) {

    }

    public class MainCatViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_main_category_mw)
        protected TextView tvMainCatMw;
        @BindView(R.id.tv_main_category_ml)
        protected TextView tvMainCatMl;
        @BindView(R.id.tv_main_category_cw)
        protected TextView tvMainCatCw;
        @BindView(R.id.tv_main_category_mf)
        protected TextView tvMainCatMf;
        @BindView(R.id.iv_main_category_mw)
        protected ImageView ivMainCatMw;
        @BindView(R.id.iv_main_category_ml)
        protected ImageView ivMainCatMl;
        @BindView(R.id.iv_main_category_cw)
        protected ImageView ivMainCatCw;
        @BindView(R.id.iv_main_category_mf)
        protected ImageView ivMainCatMf;
        public MainCatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
