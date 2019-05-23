package com.myappartments.apartment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.storage.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentProfile extends Fragment {

    @BindView(R.id.tv_profile_user_id)
    protected TextView tvProUserId;
    @BindView(R.id.tv_profile_name)
    protected TextView tvProName;
@BindView(R.id.tv_profile_flatNo)
    protected TextView tvProFlatNo;
    @BindView(R.id.tv_profile_phone)
    protected TextView tvProPhone;
@BindView(R.id.tv_profile_email)
    protected TextView tvProEmail;
@BindView(R.id.tv_profile_adhar_no)
    protected TextView tvProAdhar;
@BindView(R.id.tv_profile_apartment)
    protected TextView tvProApartment;
@BindView(R.id.tv_profile_address)
    protected TextView tvProAddress;
private SharedPrefManager tPref;
private MainActivity tActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_profile, container, false);
        ButterKnife.bind(this, view);
        setTitle();
        tPref = new SharedPrefManager(getContext());
        setProfileData();
        return view;
    }

    private void setProfileData(){
        tvProUserId.setText(tPref.getUserId());
        tvProName.setText(tPref.getUserName());
        tvProFlatNo.setText(tPref.getUserFlat());
        tvProPhone.setText(tPref.getUserPhone());
        tvProEmail.setText(tPref.getUserEmail());
        tvProAdhar.setText(tPref.getUserAdhar());
        tvProApartment.setText(tPref.getUserApartment());
        String strAddress = tPref.getUserArea()+ ", "+tPref.getUserCity()+ ",\n"+tPref.getUserState()+ ", "+ tPref.getUserPinNo()+".";
        tvProAddress.setText(strAddress);

    }

    private void setTitle(){
        tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("Profile");
        }
    }
}
