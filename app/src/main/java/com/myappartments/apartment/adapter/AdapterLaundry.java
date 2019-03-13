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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSpinerLaundry;
import com.myappartments.apartment.model.ModelSubCat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private int grandTotalMedium = 0;
    private int grandTotalLarge = 0;
    private int grandTotalExtra = 0;
   // private int countSmallWash = 0;
    private int countMediumWash = 0;
    private int countLargeWash = 0;
    private int countExtraWash = 0;
  //  private int totalWashSmall;
    private int totalWashMedium;
    private int totalWashLarge;
    private int totalWashExtra;
   // private int countSmallDry = 0;
    private int countMediumDry = 0;
    private int countLargeDry = 0;
    private int countExtraDry = 0;
   // private int totalDrySmall;
    private int totalDryMedium;
    private int totalDryLarge;
    private int totalDryExtra;
    private Context tCtx;
    private List<ModelSubCat> tModels;
    private List<ModelDescription> tDescriptions;
    private FragmentManager tFragmentManager;

    public AdapterLaundry(Context tCtx, List<ModelSubCat> tModels, FragmentManager tFragmentManager, ModelCount tCount) {
        this.tCtx = tCtx;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;
        this.tCount = tCount;
    }

    public AdapterLaundry(List<ModelDescription> tDescriptions) {
        this.tDescriptions = tDescriptions;
    }

    //    private boolean isChecked;
//    public AdapterLaundry(Context tCtx, boolean isChecked) {
//        this.tCtx = tCtx;
//        this.isChecked = isChecked;
//    }

    @NonNull
    @Override
    public LaundryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_laundry_item, viewGroup, false);
        return new LaundryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LaundryHolder laundryHolder, final int i) {


        final ModelSubCat tModel = tModels.get(i);

//        ModelDescription tDescription = tDescriptions.get(i);
        String strPricePress = tModel.getPriceSteamPress();
        String strPriceWash = tModel.getPriceWashing();
        String strPriceDry = tModel.getPriceDryCleaning();

        laundryHolder.tvLaundryName.setText(tModel.getCategoryName());
        laundryHolder.tvLaundryPressPrice.setText(strPricePress);
        laundryHolder.tvDescription.setText(tModel.getDescription());
        laundryHolder.tvLaundryWashPrice.setText(strPriceWash);
        laundryHolder.tvLaundryDryPrice.setText(strPriceDry);
        Glide.with(tCtx).load(tModel.getCategoryImage()).into(laundryHolder.ivLaundry);

//        ArrayList<ModelSpinerLaundry> tModelList = new ArrayList<>();
//        for (String aSelect_cat : select_cat) {
//            ModelSpinerLaundry tModelSpinner = new ModelSpinerLaundry();
//            tModelSpinner.setTitle(aSelect_cat);
//            tModelSpinner.setSelected(false);
//            tModelList.add(tModelSpinner);
//        }
//        AdapterSpinnerLaundry tAdapterSpinner = new AdapterSpinnerLaundry(tCtx, 0, tModelList);
//        laundryHolder.spinnerLaundry.setAdapter(tAdapterSpinner);
//

        switch (i){

            case 0:
               final int press = Integer.parseInt(strPricePress);


                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countSmallPress++;
                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallPress));
                        totalPressSmall = countSmallPress *press;
                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressSmall));
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(totalPressSmall));

                    }
                });
                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countSmallPress >0)
                            countSmallPress--;
                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallPress));
                        totalPressSmall = countSmallPress *press;
                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressSmall));
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(totalPressSmall));

                    }
                });
                //Wash click
                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        countSmallWash++;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallWash));
//                        totalWashSmall = countSmallWash *wash;
//                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashSmall));

                        Toast.makeText(tCtx, "Service Not Available", Toast.LENGTH_SHORT).show();

                    }
                });
                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        if (countSmallWash >0)
//                            countSmallWash--;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallWash));
//                        totalWashSmall = countSmallWash *wash;
//                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashSmall));
                        Toast.makeText(tCtx, "Service Not Available", Toast.LENGTH_SHORT).show();
                    }
                });

                //Dry Cleaning
                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        countSmallDry++;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallDry));
//                        totalDrySmall = countSmallDry *dry;
//                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDrySmall));
                        Toast.makeText(tCtx, "Service Not Available", Toast.LENGTH_SHORT).show();


                    }
                });
                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        if (countSmallDry >0)
//                            countSmallDry--;
//                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countSmallDry));
//                        totalDrySmall = countSmallDry *dry;
//                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDrySmall));
                        Toast.makeText(tCtx, "Service Not Available", Toast.LENGTH_SHORT).show();

                    }
                });
                break;
            case 1:
                final int pressB = Integer.parseInt(strPricePress);
                final int washB = Integer.parseInt(strPriceWash);
                final int dryB = Integer.parseInt(strPriceDry);
                //Press B Medium
                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countMediumPress++;
                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countMediumPress));
                        totalPressMedium = countMediumPress *pressB;
                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressMedium));
                        grandTotalMedium = grandTotalMedium+pressB;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));


                    }

                });

                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countMediumPress >0) {
                            countMediumPress--;
                            laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countMediumPress));
                            totalPressMedium = countMediumPress * pressB;
                            laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressMedium));
                                grandTotalMedium = grandTotalMedium - pressB;
                                laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
                            }

                    }
                });
                //Wash B Medium
                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countMediumWash++;
                        laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countMediumWash));
                        totalWashMedium = countMediumWash *washB;
                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashMedium));
                        grandTotalMedium = grandTotalMedium+washB;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));

                    }
                });
                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countMediumWash >0) {
                            countMediumWash--;
                            laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countMediumWash));
                            totalWashMedium = countMediumWash * washB;
                            laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashMedium));
                            grandTotalMedium = grandTotalMedium - washB;
                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
                        }
                    }
                });

                //Dry B Cleaning Medium
                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countMediumDry++;
                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countMediumDry));
                        totalDryMedium = countMediumDry *dryB;
                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryMedium));
                        grandTotalMedium = grandTotalMedium+dryB;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));

                    }
                });
                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countMediumDry >0) {
                            countMediumDry--;
                            laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countMediumDry));
                            totalDryMedium = countMediumDry * dryB;
                            laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryMedium));
                            grandTotalMedium = grandTotalMedium - dryB;
                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalMedium));
                        }
                    }
                });
                break;
            case 2:
                final int pressC = Integer.parseInt(strPricePress);
                final int washC = Integer.parseInt(strPriceWash);
                final int dryC = Integer.parseInt(strPriceDry);
                //Press C Large
                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countLargePress++;
                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countLargePress));
                        totalPressLarge = countLargePress *pressC;
                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressLarge));
                        grandTotalLarge = grandTotalLarge+pressC;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));

                    }
                });
                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countLargePress >0) {
                            countLargePress--;
                            laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countLargePress));
                            totalPressLarge = countLargePress * pressC;
                            laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressLarge));
                            grandTotalLarge = grandTotalLarge - pressC;
                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
                        }
                    }
                });
                //Wash C Large
                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countLargeWash++;
                        laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countLargeWash));
                        totalWashLarge = countLargeWash *washC;
                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashLarge));
                        grandTotalLarge = grandTotalLarge + washC;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
                    }
                });
                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countLargeWash >0) {
                            countLargeWash--;
                            laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countLargeWash));
                            totalWashLarge = countLargeWash * washC;
                            laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashLarge));
                            grandTotalLarge = grandTotalLarge - washC;
                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
                        }
                    }
                });

                //Dry C Cleaning Large
                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countLargeDry++;
                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countLargeDry));
                        totalDryLarge = countLargeDry *dryC;
                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryLarge));
                        grandTotalLarge = grandTotalLarge + dryC;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));

                    }
                });
                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countLargeDry >0) {
                            countLargeDry--;
                            laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countLargeDry));
                            totalDryLarge = countLargeDry * dryC;
                            laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryLarge));
                            grandTotalLarge = grandTotalLarge - dryC;
                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalLarge));
                        }
                    }
                });
                break;
            case 3:
                final int pressD = Integer.parseInt(strPricePress);
                final int washD = Integer.parseInt(strPriceWash);
                final int dryD = Integer.parseInt(strPriceDry);

                //Press Extra Large
                laundryHolder.btnAddLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countExtraPress++;
                        laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countExtraPress));
                        totalPressExtra = countExtraPress *pressD;
                        laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressExtra));
                        grandTotalExtra = grandTotalExtra + pressD;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
                    }
                });

                laundryHolder.btnRemoveLaundry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countExtraPress >0) {
                            countExtraPress--;
                            laundryHolder.tvLaundryItemCountPress.setText(String.valueOf(countExtraPress));
                            totalPressExtra = countExtraPress * pressD;
                            laundryHolder.tvLaundryPressTotal.setText(String.valueOf(totalPressExtra));
                            grandTotalExtra = grandTotalExtra - pressD;
                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
                        }
                    }
                });
                //Wash Extra Large
                laundryHolder.btnAddLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countExtraWash++;
                        laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countExtraWash));
                        totalWashExtra = countExtraWash *washD;
                        laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashExtra));
                        grandTotalExtra = grandTotalExtra + washD;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));

                    }
                });
                laundryHolder.btnRemoveLaundryWash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countExtraWash >0) {
                            countExtraWash--;
                            laundryHolder.tvLaundryItemCountWash.setText(String.valueOf(countExtraWash));
                            totalWashExtra = countExtraWash * washD;
                            laundryHolder.tvLaundryTotalWash.setText(String.valueOf(totalWashExtra));
                            grandTotalExtra = grandTotalExtra - washD;
                            laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
                        }
                    }
                });

                //Dry Cleaning Extra Large
                laundryHolder.btnAddLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countExtraDry++;
                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countExtraDry));
                        totalDryExtra = countExtraDry *dryD;
                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryExtra));
                        grandTotalExtra = grandTotalExtra + dryD;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));

                    }
                });
                laundryHolder.btnRemoveLaundryDry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (countExtraDry >0)
                            countExtraDry--;
                        laundryHolder.tvLaundryItemCountDry.setText(String.valueOf(countExtraDry));
                        totalDryExtra = countExtraDry *dryD;
                        laundryHolder.tvLaundryTotalDry.setText(String.valueOf(totalDryExtra));
                        grandTotalExtra = grandTotalExtra -  dryD;
                        laundryHolder.tvLaundryGrandTotalPrice.setText(String.valueOf(grandTotalExtra));
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
        TextView tvLaundryPressPrice;
        @BindView(R.id.btn_add_laundry)
        Button btnAddLaundry;
        @BindView(R.id.btn_add_laundry_wash)
        Button btnAddLaundryWash;
        @BindView(R.id.btn_add_laundry_dry)
        Button btnAddLaundryDry;
        @BindView(R.id.btn_remove_laundry)
        Button btnRemoveLaundry;
         @BindView(R.id.btn_remove_laundry_wash)
        Button btnRemoveLaundryWash;
         @BindView(R.id.btn_remove_laundry_dry)
        Button btnRemoveLaundryDry;
        @BindView(R.id.tv_total_item_laundry_press)
        TextView tvLaundryItemCountPress;
        @BindView(R.id.tv_total_item_laundry_wash)
        TextView tvLaundryItemCountWash;
        @BindView(R.id.tv_total_item_laundry_dry)
        TextView tvLaundryItemCountDry;

        @BindView(R.id.tv_laundry_description)
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
               @BindView(R.id.tv_laundry_grand_total)
        TextView tvLaundryGrandTotalPrice;

        LaundryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
