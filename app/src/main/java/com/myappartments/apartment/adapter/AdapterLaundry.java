package com.myappartments.apartment.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myappartments.apartment.R;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.fragment.FragmentCartView;
import com.myappartments.apartment.model.cart.ModelCartAdd;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSubCat;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;
import com.myappartments.apartment.utils.CustomToast;
import com.myappartments.apartment.utils.DisableView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterLaundry extends RecyclerView.Adapter<AdapterLaundry.LaundryHolder> {
    private ModelCount tCount;
    private int countSmallPress = 0;
    private int countMediumPress = 0;
    private int countLargePress = 0;
    private int countExtraPress = 0;
    private int totalPressSmall;
    private int totalPressMedium;
    private int totalPressLarge;
    private int totalPressExtra;
    private int grandTotalSmall;
    private int grandTotal = 0;
    private int grandTotalMedium = 0;
    private int grandTotalLarge = 0;
    private int grandTotalExtra = 0;
    private int countMediumWash = 0;
    private int countLargeWash = 0;
    private int countExtraWash = 0;
    private int totalWashMedium;
    private int totalWashLarge;
    private int totalWashExtra;
    private int countMediumDry = 0;
    private int countLargeDry = 0;
    private int countExtraDry = 0;
    private int totalDryMedium;
    private int totalDryLarge;
    private int totalDryExtra;
    private int countTotalPrice;
    private int countTotalQuantity;
    private Context tContext;
    private List<ModelSubCat> tModels;
    private List<ModelDescription> tDescriptions;
    private FragmentManager tFragmentManager;
    private String strMainCatId;
    private String strUserId;
    private CustomToast tToast;
    public AdapterLaundry(Context tContext, List<ModelSubCat> tModels, FragmentManager tFragmentManager,
                          ModelCount tCount, String strMainCatId, String strUserId) {
        this.tContext = tContext;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;
        this.tCount = tCount;
        this.strMainCatId = strMainCatId;
        this.strUserId = strUserId;
    }
    @NonNull
    @Override
    public LaundryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_laundry_item, viewGroup, false);
        return new LaundryHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final LaundryHolder laundryHolder, final int i) {
        tToast = new CustomToast(tContext);
        final ModelSubCat tModel = tModels.get(i);

        if (tModel.getStatus() != null){
            laundryHolder.btn_add_to_cart.setVisibility(View.GONE);
            laundryHolder.btn_go_to_cart.setVisibility(View.VISIBLE);
            DisableView.disableEditText(laundryHolder.et_count_laundry_press);
            DisableView.disableEditText(laundryHolder.et_count_laundry_wash);
            DisableView.disableEditText(laundryHolder.et_count_laundry_dry);

        }
        else {
            laundryHolder.btn_add_to_cart.setVisibility(View.VISIBLE);
            laundryHolder.btn_go_to_cart.setVisibility(View.GONE);

        }
        laundryHolder.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(tContext);
                alertDialog.setTitle(tModel.getCategoryName());
                alertDialog.setMessage(tModel.getDescription());
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
        String strPricePress = tModel.getPriceSteamPress();
        String strPriceWash = tModel.getPriceWashing();
        String strPriceDry = tModel.getPriceDryCleaning();


        laundryHolder.tvLaundryName.setText(tModel.getCategoryName());
        laundryHolder.tvLaundryPressPrice.setText("₹ "+strPricePress);
        laundryHolder.tvLaundryWashPrice.setText("₹ "+strPriceWash);
        laundryHolder.tvLaundryDryPrice.setText("₹ "+strPriceDry);

        final int getPresPrice = Integer.valueOf(strPricePress);
        if (strPriceWash.equals("NA")) {
            strPriceWash = "0";
            DisableView.disableEditText(laundryHolder.et_count_laundry_wash);
        }
        final int getWashPrice = Integer.parseInt(strPriceWash);
        if (strPriceDry.equals("NA")) {
            strPriceDry = "0";
            DisableView.disableEditText(laundryHolder.et_count_laundry_dry);
        }
        final int getDryPrice = Integer.parseInt(strPriceDry);
        Glide.with(tContext).load(tModel.getCategoryImage()).into(laundryHolder.ivLaundry);



        laundryHolder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String prod_id = tModels.get(i).getCatId();

                String countPress = laundryHolder.et_count_laundry_press.getText().toString().trim();
                String countWash = laundryHolder.et_count_laundry_wash.getText().toString().trim();
                String countDry = laundryHolder.et_count_laundry_dry.getText().toString().trim();

                if (countPress.equals("")) countPress = "1";
                if (countWash.equals("")||countWash.equals("Na")) countWash = "0";
                if (countDry.equals("")||countDry.equals("Na")) countDry = "0";

                int pressQuantity = Integer.parseInt(countPress);
                int washQuantity = Integer.parseInt(countWash);
                int dryQuantity = Integer.parseInt(countDry);


                    String pressPrice = String.valueOf(getPresPrice * pressQuantity);
                    String washPrice = String.valueOf(getWashPrice * washQuantity);
                    String dryPrice = String.valueOf(getDryPrice * dryQuantity);


                    Api api = ApiClient.getApiClients().create(Api.class);
                    Call<ModelCartAdd> cartCall = api.cartAdd(strUserId, prod_id, countPress, pressPrice, countWash,
                            washPrice, countDry, dryPrice);

                    cartCall.enqueue(new Callback<ModelCartAdd>() {
                        @Override
                        public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
                            ModelCartAdd tModel = response.body();
                            if (!tModel.getError()) {
                                CustomToast.tToastTop(tContext, tModel.getMessage());
                                laundryHolder.btn_add_to_cart.setVisibility(View.GONE);
                                laundryHolder.btn_go_to_cart.setVisibility(View.VISIBLE);
                                DisableView.disableEditText(laundryHolder.et_count_laundry_press);
                                DisableView.disableEditText(laundryHolder.et_count_laundry_wash);
                                DisableView.disableEditText(laundryHolder.et_count_laundry_dry);
                            } else {
                                CustomToast.tToastTop(tContext, tModel.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelCartAdd> call, Throwable t) {
                            CustomLog.d(Constant.TAG, "Cart Not Responding : " + t);
                        }
                    });
                }

        });
        laundryHolder.btn_go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentCartView()).addToBackStack(null).commit();
            }
        });




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
        TextView tvLaundryPressPrice;
        @BindView(R.id.et_count_laundry_press)
            EditText et_count_laundry_press;
        @BindView(R.id.et_count_laundry_wash)
            EditText et_count_laundry_wash;
        @BindView(R.id.et_count_laundry_dry)
            EditText et_count_laundry_dry;
        @BindView(R.id.tv_laundry_details)
        TextView tvDescription;
        @BindView(R.id.tv_laundry_price_wash)
        TextView tvLaundryWashPrice;
        @BindView(R.id.tv_laundry_price_dry)
        TextView tvLaundryDryPrice;
        @BindView(R.id.tv_laundry_press_total)
        TextView tvLaundryPressTotal;
        @BindView(R.id.tv_laundry_wash_total)
        TextView tvLaundryTotalWash;
        @BindView(R.id.tv_laundry_dry_total)
        TextView tvLaundryTotalDry;
        @BindView(R.id.btn_add_to_cart)
        Button btn_add_to_cart;
        @BindView(R.id.btn_go_to_cart)
        Button btn_go_to_cart;
        LaundryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
