package com.myappartments.apartment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;

public class FragmentOrder extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_order, container, false);
        setTitle();
        return view;
    }
    private void setTitle(){
        MainActivity tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Orders");
        }
    }
}
