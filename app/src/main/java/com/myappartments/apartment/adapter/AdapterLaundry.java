package com.myappartments.apartment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.ModelSpinerLaundry;
import com.myappartments.apartment.model.ModelSubCat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLaundry extends RecyclerView.Adapter<AdapterLaundry.LaundryHolder> {
    final String[] select_cat = {
            "Opt Service","Steam Press","Washing", "Dry Cleaning" };
    private ModelCount tCount;
    private int countS = 0;
    private int countB = 0;
    private int countC = 0;
    private int countD = 0;
    private Context tCtx;
    private List<ModelSubCat> tModels;
    private List<ModelSpinerLaundry> tModelSpinerLaundryList;
    private FragmentManager tFragmentManager;
    private int totalA;
    private int totalB;
    private int totalC;
    private int totalD;
    public AdapterLaundry(Context tCtx, List<ModelSubCat> tModels, FragmentManager tFragmentManager, ModelCount tCount) {
        this.tCtx = tCtx;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;
        this.tCount = tCount;
    }


    private boolean isChecked;
    public AdapterLaundry(Context tCtx, boolean isChecked) {
        this.tCtx = tCtx;
        this.isChecked = isChecked;
    }

    @NonNull
    @Override
    public LaundryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_laundry_item, viewGroup, false);
        return new LaundryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LaundryHolder laundryHolder, final int i) {


        final ModelSubCat tModel = tModels.get(i);

//        String strPricePress = tModel.getPriceSteamPress();
        String strPriceWash = tModel.getPriceWashing();
        String strPriceDry = tModel.getPriceDryCleaning();

        laundryHolder.tvLaundryName.setText(tModel.getCategoryName());
      //  laundryHolder.tvLaundryPricePress.setText(tModel.getPriceSteamPress());
        laundryHolder.tvDescription.setText(tModel.getDescription());
        laundryHolder.tvLaundryPriceWahs.setText(tModel.getPriceWashing());
        laundryHolder.tvLaundryPriceDry.setText(tModel.getPriceDryCleaning());
        Glide.with(tCtx).load(tModel.getCategoryImage()).into(laundryHolder.ivLaundry);

        ArrayList<ModelSpinerLaundry> tModelList = new ArrayList<>();
        for (String aSelect_cat : select_cat) {
            ModelSpinerLaundry tModelSpinner = new ModelSpinerLaundry();
            tModelSpinner.setTitle(aSelect_cat);
            tModelSpinner.setSelected(false);
            tModelList.add(tModelSpinner);
        }

        AdapterSpinnerLaundry tAdapterSpinner = new AdapterSpinnerLaundry(tCtx, 0, tModelList);
        laundryHolder.spinnerLaundry.setAdapter(tAdapterSpinner);


        switch (i){

            case 0:
                String strPricePress = tModels.get(0).getPriceSteamPress();
                if (tModelList.get(0).isSelected()) {
                     totalA = Integer.valueOf(strPricePress);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalA));
                }
                else if (tModelList.get(1).isSelected()){
                    totalA = Integer.valueOf(strPriceWash);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalA));
                }else if (tModelList.get(2).isSelected()){
                    totalA = Integer.valueOf(strPriceDry);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalA));
                }

                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countS++;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countS));

                    }
                });
                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countS>0)
                            countS--;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countS));
                    }
                });
                break;
            case 1:
                String strPricePressB = tModels.get(1).getPriceSteamPress();
                if (tModelList.get(0).isSelected()) {
                    totalB = Integer.valueOf(strPricePressB);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalB));
                }
                else if (tModelList.get(1).isSelected()){
                    totalB = Integer.valueOf(strPriceWash);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalB));
                }else if (tModelList.get(2).isSelected()){
                    totalB = Integer.valueOf(strPriceDry);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalB));
                }
                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // String strCatId = tModel.getCatId();

                        countB++;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countB));
                    }

                });

                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countB>0)
                            countB--;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countB));
                    }
                });
                break;
            case 2:
                String strPricePressC = tModels.get(2).getPriceSteamPress();
                if (tModelList.get(0).isSelected()) {
                    totalC = Integer.valueOf(strPricePressC);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalC));
                }
                else if (tModelList.get(1).isSelected()){
                    totalC = Integer.valueOf(strPriceWash);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalC));
                }else if (tModelList.get(2).isSelected()){
                    totalC = Integer.valueOf(strPriceDry);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalC));
                }
                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countC++;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countC));
                    }
                });

                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countC>0)
                            countC--;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countC));
                    }
                });
                break;
            case 3:
                String strPricePressD = tModels.get(3).getPriceSteamPress();
                if (tModelList.get(0).isSelected()) {
                    totalD = Integer.valueOf(strPricePressD);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalD));
                }
                else if (tModelList.get(1).isSelected()){
                    totalD = Integer.valueOf(strPriceWash);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalD));
                }else if (tModelList.get(2).isSelected()){
                    totalD = Integer.valueOf(strPriceDry);
                    laundryHolder.tvLaundryPricePress.setText(String.valueOf(totalD));
                }
                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countD++;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countD));
                    }
                });

                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countD>0)
                            countD--;
                        laundryHolder.tvLaundryCount.setText(String.valueOf(countD));
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return tModels.size();
    }

    class LaundryHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_laundry)
        ImageView ivLaundry;
        @BindView(R.id.tv_laundry_name)
        TextView tvLaundryName;
        @BindView(R.id.tv_laundry_price)
        TextView tvLaundryPricePress;
        @BindView(R.id.btn_add_laundry)
        Button btnAddLaundry;
        @BindView(R.id.btn_remove_laundry)
        Button btnRemoveLaundry;
        @BindView(R.id.tv_total_item_laundry)
        TextView tvLaundryCount;
        @BindView(R.id.spinner_laundry)
        Spinner spinnerLaundry;
        @BindView(R.id.tv_laundry_description)
        TextView tvDescription;
        @BindView(R.id.tv_laundry_price_wash)
        TextView tvLaundryPriceWahs;
        @BindView(R.id.tv_laundry_price_dry)
        TextView tvLaundryPriceDry;
        LaundryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
