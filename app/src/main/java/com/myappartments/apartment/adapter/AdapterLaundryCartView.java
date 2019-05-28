package com.myappartments.apartment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.cart.ModelCartView;
import com.myappartments.apartment.utils.CustomToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLaundryCartView extends RecyclerView.Adapter<AdapterLaundryCartView.CartViewHolder> {
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
    private List<ModelCartView> tModels;
    private FragmentManager tFragmentManager;
    private String strMainCatId;
    private String strUserId;
    private CustomToast tToast;


    public AdapterLaundryCartView(Context tContext, List<ModelCartView> tModels, FragmentManager tFragmentManager,
                                  String strUserId) {
        this.tContext = tContext;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;
        this.strMainCatId = strMainCatId;
        this.strUserId = strUserId;
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_cart_view_item, viewGroup, false);
        return new CartViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, final int i) {
        tToast = new CustomToast(tContext);
        final ModelCartView tModel = tModels.get(i);
//        cartViewHolder.btn_add_to_cart.setVisibility(View.GONE);
//        cartViewHolder.btn_go_to_cart.setVisibility(View.GONE);
//        cartViewHolder.btn_get_total.setVisibility(View.VISIBLE);
//        final String strSubCatId = tModel.getCatId();
//
//        cartViewHolder.tvDescription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(tContext);
//                alertDialog.setTitle(tModel.getCategoryName());
//                alertDialog.setMessage(tModel.getDescription());
//                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                AlertDialog dialog = alertDialog.create();
//                dialog.show();
//            }
//        });
        final String strQuantityPress = tModel.getPressQuantity();
        final String strPricePress = tModel.getPressPrice();

        final String strQuantityWash = tModel.getWashQuantity();
        final String strPriceWash = tModel.getWashPrice();

        final String strQuantityDry = tModel.getDryQuantity();
        final String strPriceDry = tModel.getDryPrice();

        final String strQuantityTotal = tModel.getTotalQuantity();
        final String strPriceTotal = tModel.getTotalPrice();

        final String strDate = tModel.getDate();

        cartViewHolder.tv_count_item_press_cart.setText(strQuantityPress);
        cartViewHolder.tv_cart_price_press.setText(strPricePress);

        cartViewHolder.tv_count_item_wash_cart.setText(strQuantityWash);
        cartViewHolder.tv_cart_price_wash.setText(strPriceWash);

        cartViewHolder.tv_count_item_dry_cart.setText(strQuantityDry);
        cartViewHolder.tv_price_dry_cart.setText(strPriceDry);

        cartViewHolder.tv_count_item_total_cart.setText(strQuantityTotal);
        cartViewHolder.tv_total_price_cart.setText(strPriceTotal);

        cartViewHolder.tv_cart_price_press.setText(strPricePress);


//        cartViewHolder.tvLaundryName.setText(tModel.getCategoryName());
//        cartViewHolder.tvLaundryPressPrice.setText("₹ "+strPricePress);
//        cartViewHolder.tvLaundryWashPrice.setText("₹ "+strPriceWash);
//        cartViewHolder.tvLaundryDryPrice.setText("₹ "+strPriceDry);
//
//        Glide.with(tContext).load(tModel.getCategoryImage()).into(cartViewHolder.ivLaundry);
//
//
//
//        cartViewHolder.btn_get_total.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                String countPress = cartViewHolder.et_total_item_laundry_press.getText().toString().trim();
//                String countWash = cartViewHolder.et_total_item_laundry_wash.getText().toString().trim();
//                String countDry = cartViewHolder.et_total_item_laundry_dry.getText().toString().trim();
//                String countPressPrice = tModel.getPriceSteamPress();
//                String countWashPrice = tModel.getPriceWashing();
//                String countDryPrice = tModel.getPriceDryCleaning();
//
//                if (countPress.equals("")) countPress = "0";
//                if (countWash.equals("") ||countWashPrice.equals("NA")){
//                    countWashPrice = "0";
//                    countWash = "0";}
//
//                if (countDry.equals("")||countDryPrice.equals("NA")){
//                    countDryPrice = "0";
//                    countDry = "0";}
//
//                    countTotalQuantity = Integer.parseInt(countPress) + Integer.valueOf(countWash) + Integer.valueOf(countDry);
//                    cartViewHolder.tv_total_item_get.setText(String.valueOf(countTotalQuantity));
//                     countTotalPrice
//                            = Integer.valueOf(countPress) * Integer.valueOf(countPressPrice)
//                            + Integer.valueOf(countWash) * Integer.valueOf(countWashPrice)
//                            + Integer.valueOf(countDry) * Integer.valueOf(countDryPrice);
//                    cartViewHolder.tv_laundry_total_price.setText("₹ "+String.valueOf(countTotalPrice));
//                cartViewHolder.btn_add_to_cart.setVisibility(View.VISIBLE);
//                cartViewHolder.btn_go_to_cart.setVisibility(View.GONE);
//                cartViewHolder.btn_get_total.setVisibility(View.GONE);
//
//            }
//        });
//        cartViewHolder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                new AlertDialog.Builder(tContext)
//                        .setTitle("Add To Cart")
//                        .setMessage("Are you sure you want to add to cart?")
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Continue with delete operation
//                                String strTotalPrice = String.valueOf(countTotalPrice);
//                                String strTotalQuantity = String.valueOf(countTotalQuantity);
//                                Api api = ApiClient.getApiClients().create(Api.class);
//                                Call<ModelCartAdd> cartCall = api.cartAdd(strUserId, strMainCatId, strSubCatId, strTotalPrice, strTotalQuantity);
//                                cartCall.enqueue(new Callback<ModelCartAdd>() {
//                                    @Override
//                                    public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
//                                        ModelCartAdd tModel = response.body();
//                                       if (!tModel.getError()){
//                                        CustomToast.tToast(tContext, tModel.getMessage());
//                                        cartViewHolder.btn_add_to_cart.setVisibility(View.GONE);
//                                        cartViewHolder.btn_go_to_cart.setVisibility(View.VISIBLE);}
//                                        else {
//                                           CustomToast.tToast(tContext, tModel.getMessage());
//                                       }
//                                    }
//                                    @Override
//                                    public void onFailure(Call<ModelCartAdd> call, Throwable t) {
//                                        CustomLog.d(Constant.TAG, "Cart Not Responding : "+t);
//                                    }
//                                });
//                            }
//                        })
//
//                        // A null listener allows the button to dismiss the dialog and take no further action.
//                        .setNegativeButton(android.R.string.no, null)
//                        .setIcon(R.drawable.logo_512x512)
//                        .show();
//            }
//        });


//        cartViewHolder.btn_go_to_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentCart()).addToBackStack(null).commit();
//            }
//        });

//        switch (i){
//            case 0:
//               final int press = Integer.parseInt(strPricePress);
//                cartViewHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countSmallPress++;
//                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallPress));
//                        totalPressSmall = countSmallPress *press;
//                        cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressSmall));
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(totalPressSmall));
//                        grandTotal = grandTotal + Integer.parseInt(strPricePress);
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countSmallPress >0)
//                            countSmallPress--;
//                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallPress));
//                        totalPressSmall = countSmallPress *press;
//                        cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressSmall));
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(totalPressSmall));
//                        grandTotal = grandTotal - Integer.parseInt(strPricePress);
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                //Wash click
//                cartViewHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        countSmallWash++;
////                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallWash));
////                        totalWashSmall = countSmallWash *wash;
////                        cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashSmall));
//                       CustomToast.tToast(tContext,Constant.SERVICE_NOT);
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        if (countSmallWash >0)
////                            countSmallWash--;
////                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallWash));
////                        totalWashSmall = countSmallWash *wash;
////                        cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashSmall));
//                        CustomToast.tToast(tContext,Constant.SERVICE_NOT);
//                    }
//                });
//
//                //Dry Cleaning
//                cartViewHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        countSmallDry++;
////                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallDry));
////                        totalDrySmall = countSmallDry *dry;
////                        cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDrySmall));
//                        CustomToast.tToast(tContext,Constant.SERVICE_NOT);
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        if (countSmallDry >0)
////                            countSmallDry--;
////                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallDry));
////                        totalDrySmall = countSmallDry *dry;
////                        cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDrySmall));
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
//                cartViewHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countMediumPress++;
//                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countMediumPress));
//                        totalPressMedium = countMediumPress *pressB;
//                        cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressMedium));
//                        grandTotalMedium = grandTotalMedium+pressB;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                        grandTotal = grandTotal + pressB;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//
//                });
//
//                cartViewHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countMediumPress >0) {
//                            countMediumPress--;
//                            cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countMediumPress));
//                            totalPressMedium = countMediumPress * pressB;
//                            cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressMedium));
//                                grandTotalMedium = grandTotalMedium - pressB;
//                                cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                            grandTotal = grandTotal - pressB;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                            }
//                    }
//                });
//                //Wash B Medium
//                cartViewHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countMediumWash++;
//                        cartViewHolder.tvLaundryItemCountWash.setText(String.valueOf(countMediumWash));
//                        totalWashMedium = countMediumWash *washB;
//                        cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashMedium));
//                        grandTotalMedium = grandTotalMedium+washB;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                        grandTotal = grandTotal + washB;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countMediumWash >0) {
//                            countMediumWash--;
//                            cartViewHolder.tvLaundryItemCountWash.setText(String.valueOf(countMediumWash));
//                            totalWashMedium = countMediumWash * washB;
//                            cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashMedium));
//                            grandTotalMedium = grandTotalMedium - washB;
//                            cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                            grandTotal = grandTotal - washB;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Dry B Cleaning Medium
//                cartViewHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countMediumDry++;
//                        cartViewHolder.tvLaundryItemCountDry.setText(String.valueOf(countMediumDry));
//                        totalDryMedium = countMediumDry *dryB;
//                        cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryMedium));
//                        grandTotalMedium = grandTotalMedium+dryB;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
//                        grandTotal = grandTotal + dryB;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countMediumDry >0) {
//                            countMediumDry--;
//                            cartViewHolder.tvLaundryItemCountDry.setText(String.valueOf(countMediumDry));
//                            totalDryMedium = countMediumDry * dryB;
//                            cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryMedium));
//                            grandTotalMedium = grandTotalMedium - dryB;
//                            cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
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
//                cartViewHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countLargePress++;
//                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countLargePress));
//                        totalPressLarge = countLargePress *pressC;
//                        cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressLarge));
//                        grandTotalLarge = grandTotalLarge+pressC;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                        grandTotal = grandTotal + pressC;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countLargePress >0) {
//                            countLargePress--;
//                            cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countLargePress));
//                            totalPressLarge = countLargePress * pressC;
//                            cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressLarge));
//                            grandTotalLarge = grandTotalLarge - pressC;
//                            cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                            grandTotal = grandTotal - pressC;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Wash C Large
//                cartViewHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countLargeWash++;
//                        cartViewHolder.tvLaundryItemCountWash.setText(String.valueOf(countLargeWash));
//                        totalWashLarge = countLargeWash *washC;
//                        cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashLarge));
//                        grandTotalLarge = grandTotalLarge + washC;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                        grandTotal = grandTotal + washC;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countLargeWash >0) {
//                            countLargeWash--;
//                            cartViewHolder.tvLaundryItemCountWash.setText(String.valueOf(countLargeWash));
//                            totalWashLarge = countLargeWash * washC;
//                            cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashLarge));
//                            grandTotalLarge = grandTotalLarge - washC;
//                            cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                            grandTotal = grandTotal - washC;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Dry C Cleaning Large
//                cartViewHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countLargeDry++;
//                        cartViewHolder.tvLaundryItemCountDry.setText(String.valueOf(countLargeDry));
//                        totalDryLarge = countLargeDry *dryC;
//                        cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryLarge));
//                        grandTotalLarge = grandTotalLarge + dryC;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
//                        grandTotal = grandTotal + dryC;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countLargeDry >0) {
//                            countLargeDry--;
//                            cartViewHolder.tvLaundryItemCountDry.setText(String.valueOf(countLargeDry));
//                            totalDryLarge = countLargeDry * dryC;
//                            cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryLarge));
//                            grandTotalLarge = grandTotalLarge - dryC;
//                            cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
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
//                cartViewHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countExtraPress++;
//                        cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countExtraPress));
//                        totalPressExtra = countExtraPress *pressD;
//                        cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressExtra));
//                        grandTotalExtra = grandTotalExtra + pressD;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal + pressD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//
//                cartViewHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countExtraPress > 0) {
//                            countExtraPress--;
//                            cartViewHolder.tvLaundryItemCountPress.setText(String.valueOf(countExtraPress));
//                            totalPressExtra = countExtraPress * pressD;
//                            cartViewHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressExtra));
//                            grandTotalExtra = grandTotalExtra - pressD;
//                            cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                            grandTotal = grandTotal - pressD;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Wash Extra Large
//                cartViewHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countExtraWash++;
//                        cartViewHolder.tvLaundryItemCountWash.setText(String.valueOf(countExtraWash));
//                        totalWashExtra = countExtraWash *washD;
//                        cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashExtra));
//                        grandTotalExtra = grandTotalExtra + washD;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal + washD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countExtraWash >0) {
//                            countExtraWash--;
//                            cartViewHolder.tvLaundryItemCountWash.setText(String.valueOf(countExtraWash));
//                            totalWashExtra = countExtraWash * washD;
//                            cartViewHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashExtra));
//                            grandTotalExtra = grandTotalExtra - washD;
//                            cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                            grandTotal = grandTotal - washD;
//                            tToast.toastTotal(String.valueOf(grandTotal));
//                        }
//                    }
//                });
//                //Dry Cleaning Extra Large
//                cartViewHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        countExtraDry++;
//                        cartViewHolder.tvLaundryItemCountDry.setText(String.valueOf(countExtraDry));
//                        totalDryExtra = countExtraDry *dryD;
//                        cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryExtra));
//                        grandTotalExtra = grandTotalExtra + dryD;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal + dryD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                cartViewHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (countExtraDry >0)
//                            countExtraDry--;
//                        cartViewHolder.tvLaundryItemCountDry.setText(String.valueOf(countExtraDry));
//                        totalDryExtra = countExtraDry *dryD;
//                        cartViewHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryExtra));
//                        grandTotalExtra = grandTotalExtra -  dryD;
//                        cartViewHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
//                        grandTotal = grandTotal - dryD;
//                        tToast.toastTotal(String.valueOf(grandTotal));
//                    }
//                });
//                break;
//        }


    }
    @Override
    public int getItemCount() {
        return 10;
    }
    class CartViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_cart_name)
        protected TextView tv_cart_name;
        @BindView(R.id.tv_count_item_press_cart)
        protected TextView tv_count_item_press_cart;
        @BindView(R.id.tv_cart_price_press)
        protected TextView tv_cart_price_press;

        @BindView(R.id.tv_count_item_wash_cart)
        protected TextView tv_count_item_wash_cart;
        @BindView(R.id.tv_cart_price_wash)
        protected TextView tv_cart_price_wash;

        @BindView(R.id.tv_count_item_dry_cart)
        protected TextView tv_count_item_dry_cart;
        @BindView(R.id.tv_price_dry_cart)
        protected TextView tv_price_dry_cart;

        @BindView(R.id.tv_count_item_total_cart)
        protected TextView tv_count_item_total_cart;
        @BindView(R.id.tv_total_price_cart)
        protected TextView tv_total_price_cart;
        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
