package com.myappartments.apartment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelSpinerLaundry;

import java.util.ArrayList;
import java.util.List;

public class AdapterSpinnerLaundry extends ArrayAdapter<ModelSpinerLaundry> {

    private Context tCtx;
    private ArrayList<ModelSpinerLaundry> tArrList;
    private AdapterSpinnerLaundry tAdapter;
    private boolean isFormView = false;
    private AdapterLaundry tAdapterLaundry;

    public AdapterSpinnerLaundry(@NonNull Context context, int resource, @NonNull List<ModelSpinerLaundry> objects) {
        super(context, resource, objects);

        this.tCtx = context;
        this.tArrList = (ArrayList<ModelSpinerLaundry>) objects;
        this.tAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        boolean selected = true;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(tCtx);
            convertView = layoutInflator.inflate(R.layout.spinner_item_check, null);
            holder = new ViewHolder();
            holder.mTextView =  convertView.findViewById(R.id.tv_option_spinner);
            holder.mCheckBox =  convertView.findViewById(R.id.cb_option_spinner);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(tArrList.get(position).getTitle());

        // To check weather checked event fire from getView() or user input
        isFormView = true;
        holder.mCheckBox.setChecked(tArrList.get(position).isSelected());
        isFormView = false;


        if (position==2){
            holder.mCheckBox.setChecked(true);
            tArrList.get(1).setSelected(false);
        }
        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                tAdapterLaundry = new AdapterLaundry(tCtx, isFormView);
                if (!isFormView) {
                    tArrList.get(position).setSelected(isChecked);

                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }

}
