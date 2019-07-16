package com.myappartments.laundry.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.myappartments.laundry.R;
import com.myappartments.laundry.model.ModelSubCat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDetails extends Fragment {

    @BindView(R.id.iv_details_banner)
    protected ImageView ivBanner;
    @BindView(R.id.tv_details_name)
    protected TextView tvDetailsName;
    @BindView(R.id.tv_details_price)
    protected TextView tvDetailsPrice;
    @BindView(R.id.tv_details_description)
    protected TextView tvDetailsDescription;
    @BindView(R.id.btn_add_details)
    protected TextView btnAddDetails;
    @BindView(R.id.btn_remove_details)
    protected TextView btnRemoveDetails;
    @BindView(R.id.btn_details_checkOut)
    protected TextView btnDetailsCheckOut;
    private ModelSubCat tModel;
    private Context tCtx;

    public static FragmentDetails newInstance(Context tCtx, ModelSubCat tModel) {

        FragmentDetails fragment = new FragmentDetails();
        fragment.tCtx = tCtx;
        fragment.tModel = tModel;
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_details, container, false);
        ButterKnife.bind(this, view);
        setDetails();
        return view;
    }
    @SuppressLint("CheckResult")
    private void setDetails(){
        Glide.with(tCtx).load(tModel.getCategoryImage()).into(ivBanner);
        tvDetailsName.setText(tModel.getCategoryName());
        tvDetailsPrice.setText(tModel.getPrice());
        tvDetailsDescription.setText(tModel.getDescription());
    }
}
