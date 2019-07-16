package com.myappartments.laundry.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappartments.laundry.R;
import com.myappartments.laundry.fragment.FragSmartApp;
import com.myappartments.laundry.fragment.FragmentLaundry;
import com.myappartments.laundry.model.MainCatModel;
import com.myappartments.laundry.utils.CustomDialog;
import com.myappartments.laundry.utils.CustomToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMainCat extends RecyclerView.Adapter<AdapterMainCat.MainCatViewHolder> implements View.OnClickListener {

    private Context tContext;
    private List<MainCatModel> tModels;
    private FragmentManager tFragmentManager;
    private String strUserId;


    public AdapterMainCat(Context tContext, List<MainCatModel> tModels, FragmentManager tFragmentManager, String strUserId) {
        this.tContext = tContext;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;
        this.strUserId = strUserId;

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
                    tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentLaundry.newInstance(tModel.getId().toString(), strUserId)).addToBackStack(null).commit();
                    break;
                    case 1:
                        CustomDialog.customDialogPos(tContext,"Our Mineral Water Supply service will start soon, as it will start we will notify you.");
                    break;
                    case 2:
                        CustomDialog.customDialogPos(tContext,"Our Apartments Services will start soon, as it will start we will notify you.");

                    break;
                    case 3:
                        CustomDialog.customDialogPos(tContext,"Our Fresh Vegetable Supply service will start soon, as it will start we will notify you.");
                    break;
                    case 4:
                        CustomDialog.customDialogPos(tContext,"Our Milk Supply service will start soon, as it will start we will notify you.");
                    break;
                    case 5:
                    tFragmentManager.beginTransaction().replace(R.id.container_main, new FragSmartApp()).addToBackStack(null).commit();
                    break;
                    default:
                        CustomToast.tToastTop(tContext, "Service will start soon...");
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
