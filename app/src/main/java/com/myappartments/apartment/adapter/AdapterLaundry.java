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
import com.myappartments.apartment.fragment.FragmentCart;
import com.myappartments.apartment.model.cart.ModelCartAdd;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSubCat;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;
import com.myappartments.apartment.utils.CustomToast;

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
        laundryHolder.btn_add_to_cart.setVisibility(View.GONE);
        laundryHolder.btn_go_to_cart.setVisibility(View.GONE);
        laundryHolder.btn_get_total.setVisibility(View.VISIBLE);
        final String strSubCatId = tModel.getCatId();

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
        final String strPricePress = tModel.getPriceSteamPress();
        final String strPriceWash = tModel.getPriceWashing();
        final String strPriceDry = tModel.getPriceDryCleaning();

        laundryHolder.tvLaundryName.setText(tModel.getCategoryName());
        laundryHolder.tvLaundryPressPrice.setText("₹ "+strPricePress);
        laundryHolder.tvLaundryWashPrice.setText("₹ "+strPriceWash);
        laundryHolder.tvLaundryDryPrice.setText("₹ "+strPriceDry);

        Glide.with(tContext).load(tModel.getCategoryImage()).into(laundryHolder.ivLaundry);



        laundryHolder.btn_get_total.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String countPress = laundryHolder.et_total_item_laundry_press.getText().toString().trim();
                String countWash = laundryHolder.et_total_item_laundry_wash.getText().toString().trim();
                String countDry = laundryHolder.et_total_item_laundry_dry.getText().toString().trim();
                String countPressPrice = tModel.getPriceSteamPress();
                String countWashPrice = tModel.getPriceWashing();
                String countDryPrice = tModel.getPriceDryCleaning();

                if (countPress.equals("")) countPress = "0";
                if (countWash.equals("") ||countWashPrice.equals("NA")){
                    countWashPrice = "0";
                    countWash = "0";}

                if (countDry.equals("")||countDryPrice.equals("NA")){
                    countDryPrice = "0";
                    countDry = "0";}

                    countTotalQuantity = Integer.parseInt(countPress) + Integer.valueOf(countWash) + Integer.valueOf(countDry);
                    laundryHolder.tv_total_item_get.setText(String.valueOf(countTotalQuantity));
                     countTotalPrice
                            = Integer.valueOf(countPress) * Integer.valueOf(countPressPrice)
                            + Integer.valueOf(countWash) * Integer.valueOf(countWashPrice)
                            + Integer.valueOf(countDry) * Integer.valueOf(countDryPrice);
                    laundryHolder.tv_laundry_total_price.setText("₹ "+String.valueOf(countTotalPrice));
                laundryHolder.btn_add_to_cart.setVisibility(View.VISIBLE);
                laundryHolder.btn_go_to_cart.setVisibility(View.GONE);
                laundryHolder.btn_get_total.setVisibility(View.GONE);

            }
        });
        laundryHolder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(tContext)
                        .setTitle("Add To Cart")
                        .setMessage("Are you sure you want to add to cart?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                String strTotalPrice = String.valueOf(countTotalPrice);
                                String strTotalQuantity = String.valueOf(countTotalQuantity);
                                Api api = ApiClient.getApiClients().create(Api.class);
                                Call<ModelCartAdd> cartCall = api.cartAdd(strUserId, strMainCatId, strSubCatId, strTotalPrice, strTotalQuantity);
                                cartCall.enqueue(new Callback<ModelCartAdd>() {
                                    @Override
                                    public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
                                        ModelCartAdd tModel = response.body();
                                       if (!tModel.getError()){
                                        CustomToast.tToast(tContext, tModel.getMessage());
                                        laundryHolder.btn_add_to_cart.setVisibility(View.GONE);
                                        laundryHolder.btn_go_to_cart.setVisibility(View.VISIBLE);}
                                        else {
                                           CustomToast.tToast(tContext, tModel.getMessage());
                                       }
                                    }
                                    @Override
                                    public void onFailure(Call<ModelCartAdd> call, Throwable t) {
                                        CustomLog.d(Constant.TAG, "Cart Not Responding : "+t);
                                    }
                                });
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.logo_512x512)
                        .show();
            }
        });


        laundryHolder.btn_go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentCart()).addToBackStack(null).commit();
            }
        });

//        switch (i){
//            case 0:
//               final int press = Integer.parseInt(strPricePress);
//                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countSmallPress++;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallPress));
//                        totalPressSmall = countSmallPress *press;
//                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressSmall));
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(totalPressSmall));
//                        grandTotal = grandTotal + Integer.parseInt(strPricePress);
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countSmallPress >0)
//                            countSmallPress--;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallPress));
//                        totalPressSmall = countSmallPress *press;
//                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressSmall));
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(totalPressSmall));
//                        grandTotal = grandTotal - Integer.parseInt(strPricePress);
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                //Wash click
//                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        countSmallWash++;
////                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallWash));
////                        totalWashSmall = countSmallWash *wash;
////                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashSmall));
//                       CustomToast.tToast(tContext,Constant.SERVICE_NOT);
//                    }
//                });
//                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        if (countSmallWash >0)
////                            countSmallWash--;
////                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallWash));
////                        totalWashSmall = countSmallWash *wash;
////                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashSmall));
//                        CustomToast.tToast(tContext,Constant.SERVICE_NOT);
//                    }
//                });
//
//                //Dry Cleaning
//                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        countSmallDry++;
////                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallDry));
////                        totalDrySmall = countSmallDry *dry;
////                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDrySmall));
//                        CustomToast.tToast(tContext,Constant.SERVICE_NOT);
//                    }
//                });
//                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        if (countSmallDry >0)
////                            countSmallDry--;
////                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallDry));
////                        totalDrySmall = countSmallDry *dry;
////                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDrySmall));
//                        CustomToast.tToast(tContext,Constant.SERVICE_NOT);
//
//                    }
//                });
//                break;
//            case 1:
//                final int pressB = Integer.parseInt(strPricePress);
//                final int washB = Integer.parseInt(strPriceWash);
//                final int dryB = Integer.parseInt(strPriceDry);
//                //Press B Medium
//                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countMediumPress++;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countMediumPress));
//                        totalPressMedium = countMediumPress *pressB;
//                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressMedium));
//                        grandTotalMedium = grandTotalMedium+pressB;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                        grandTotal = grandTotal + pressB;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//
//                });
//
//                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countMediumPress >0) {
//                            countMediumPress--;
//                            laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countMediumPress));
//                            totalPressMedium = countMediumPress * pressB;
//                            laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressMedium));
//                                grandTotalMedium = grandTotalMedium - pressB;
//                                laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                            grandTotal = grandTotal - pressB;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                            }
//                    }
//                });
//                //Wash B Medium
//                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countMediumWash++;
//                        laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countMediumWash));
//                        totalWashMedium = countMediumWash *washB;
//                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashMedium));
//                        grandTotalMedium = grandTotalMedium+washB;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                        grandTotal = grandTotal + washB;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countMediumWash >0) {
//                            countMediumWash--;
//                            laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countMediumWash));
//                            totalWashMedium = countMediumWash * washB;
//                            laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashMedium));
//                            grandTotalMedium = grandTotalMedium - washB;
//                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                            grandTotal = grandTotal - washB;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Dry B Cleaning Medium
//                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countMediumDry++;
//                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countMediumDry));
//                        totalDryMedium = countMediumDry *dryB;
//                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryMedium));
//                        grandTotalMedium = grandTotalMedium+dryB;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                        grandTotal = grandTotal + dryB;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countMediumDry >0) {
//                            countMediumDry--;
//                            laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countMediumDry));
//                            totalDryMedium = countMediumDry * dryB;
//                            laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryMedium));
//                            grandTotalMedium = grandTotalMedium - dryB;
//                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                            grandTotal = grandTotal - dryB;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                break;
//            case 2:
//                final int pressC = Integer.parseInt(strPricePress);
//                final int washC = Integer.parseInt(strPriceWash);
//                final int dryC = Integer.parseInt(strPriceDry);
//                //Press C Large
//                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countLargePress++;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countLargePress));
//                        totalPressLarge = countLargePress *pressC;
//                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressLarge));
//                        grandTotalLarge = grandTotalLarge+pressC;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                        grandTotal = grandTotal + pressC;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countLargePress >0) {
//                            countLargePress--;
//                            laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countLargePress));
//                            totalPressLarge = countLargePress * pressC;
//                            laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressLarge));
//                            grandTotalLarge = grandTotalLarge - pressC;
//                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                            grandTotal = grandTotal - pressC;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Wash C Large
//                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countLargeWash++;
//                        laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countLargeWash));
//                        totalWashLarge = countLargeWash *washC;
//                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashLarge));
//                        grandTotalLarge = grandTotalLarge + washC;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                        grandTotal = grandTotal + washC;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countLargeWash >0) {
//                            countLargeWash--;
//                            laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countLargeWash));
//                            totalWashLarge = countLargeWash * washC;
//                            laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashLarge));
//                            grandTotalLarge = grandTotalLarge - washC;
//                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                            grandTotal = grandTotal - washC;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Dry C Cleaning Large
//                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countLargeDry++;
//                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countLargeDry));
//                        totalDryLarge = countLargeDry *dryC;
//                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryLarge));
//                        grandTotalLarge = grandTotalLarge + dryC;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                        grandTotal = grandTotal + dryC;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countLargeDry >0) {
//                            countLargeDry--;
//                            laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countLargeDry));
//                            totalDryLarge = countLargeDry * dryC;
//                            laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryLarge));
//                            grandTotalLarge = grandTotalLarge - dryC;
//                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                            grandTotal = grandTotal - dryC;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                break;
//            case 3:
//                final int pressD = Integer.parseInt(strPricePress);
//                final int washD = Integer.parseInt(strPriceWash);
//                final int dryD = Integer.parseInt(strPriceDry);
//                //Press Extra Large
//                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countExtraPress++;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countExtraPress));
//                        totalPressExtra = countExtraPress *pressD;
//                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressExtra));
//                        grandTotalExtra = grandTotalExtra + pressD;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal + pressD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//
//                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countExtraPress > 0) {
//                            countExtraPress--;
//                            laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countExtraPress));
//                            totalPressExtra = countExtraPress * pressD;
//                            laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressExtra));
//                            grandTotalExtra = grandTotalExtra - pressD;
//                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                            grandTotal = grandTotal - pressD;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Wash Extra Large
//                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countExtraWash++;
//                        laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countExtraWash));
//                        totalWashExtra = countExtraWash *washD;
//                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashExtra));
//                        grandTotalExtra = grandTotalExtra + washD;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal + washD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countExtraWash >0) {
//                            countExtraWash--;
//                            laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countExtraWash));
//                            totalWashExtra = countExtraWash * washD;
//                            laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashExtra));
//                            grandTotalExtra = grandTotalExtra - washD;
//                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                            grandTotal = grandTotal - washD;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Dry Cleaning Extra Large
//                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countExtraDry++;
//                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countExtraDry));
//                        totalDryExtra = countExtraDry *dryD;
//                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryExtra));
//                        grandTotalExtra = grandTotalExtra + dryD;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal + dryD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countExtraDry >0)
//                            countExtraDry--;
//                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countExtraDry));
//                        totalDryExtra = countExtraDry *dryD;
//                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryExtra));
//                        grandTotalExtra = grandTotalExtra -  dryD;
//                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal - dryD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                break;
//        }


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
//        @BindView(R.id.btn_add_laundry)
//        Button btnAddLaundry;
//        @BindView(R.id.btn_add_laundry_wash)
//        Button btnAddLaundryWash;
//        @BindView(R.id.btn_add_laundry_dry)
//        Button btnAddLaundryDry;
//        @BindView(R.id.btn_remove_laundry)
//        Button btnRemoveLaundry;
//        @BindView(R.id.btn_remove_laundry_wash)
//        Button btnRemoveLaundryWash;
//        @BindView(R.id.btn_remove_laundry_dry)
//        Button btnRemoveLaundryDry;
        @BindView(R.id.et_total_item_laundry_press)
            EditText et_total_item_laundry_press;
        @BindView(R.id.et_total_item_laundry_wash)
            EditText et_total_item_laundry_wash;
        @BindView(R.id.et_total_item_laundry_dry)
            EditText et_total_item_laundry_dry;
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
        @BindView(R.id.tv_total_item_get)
        TextView tv_total_item_get;
        @BindView(R.id.tv_laundry_total_price)
        TextView tv_laundry_total_price;
        @BindView(R.id.btn_add_to_cart)
        Button btn_add_to_cart;
        @BindView(R.id.btn_go_to_cart)
        Button btn_go_to_cart;
        @BindView(R.id.btn_get_total)
        Button btn_get_total;
        LaundryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
