package com.myappartments.laundry.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myappartments.laundry.R;
import com.myappartments.laundry.model.ModelDbFlatList;

import java.util.List;

public class AdapterDbFlatList extends BaseAdapter {
    private Context tContext;
    private List<ModelDbFlatList> tModels;
    private LayoutInflater inflater;

    public AdapterDbFlatList(Context tContext, List<ModelDbFlatList> tModels) {
        this.tContext = tContext;
        this.tModels = tModels;
        inflater = (LayoutInflater.from(tContext));
    }

    @Override
    public int getCount() {
        return tModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spn_db_flat_list, null);
        TextView tv_dbFlatNoList =  view.findViewById(R.id.tv_dbFlatNoList);
        TextView tvDbFlatListPersonName =  view.findViewById(R.id.tv_dbFlatPersonName);
        TextView tvDbFlatListPhNo =  view.findViewById(R.id.tv_dbFlatPhoneNo);
        tv_dbFlatNoList.setText(tModels.get(i).getUserFlatNo());
        tvDbFlatListPersonName.setText(tModels.get(i).getUserName());
        tvDbFlatListPhNo.setText(tModels.get(i).getUserPhoneNo());
        return view;
    }

}