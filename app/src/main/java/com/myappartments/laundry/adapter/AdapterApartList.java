package com.myappartments.laundry.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myappartments.laundry.R;
import com.myappartments.laundry.model.ModelAprtList;

import java.util.List;

public class AdapterApartList extends BaseAdapter {
    private Context tContext;
    private List<ModelAprtList> tModels;
    private LayoutInflater inflater;

    public AdapterApartList(Context tContext, List<ModelAprtList> tModels) {
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
        view = inflater.inflate(R.layout.spn_aprt_list, null);
        TextView tv_aprtListName =  view.findViewById(R.id.tv_aprtListName);
        tv_aprtListName.setText(tModels.get(i).getAprtName());
        return view;
    }

}