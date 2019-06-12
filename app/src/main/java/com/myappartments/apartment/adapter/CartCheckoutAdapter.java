package com.myappartments.apartment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.cart.ModelCartView;
import com.myappartments.apartment.utils.CustomToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartCheckoutAdapter extends RecyclerView.Adapter<CartCheckoutAdapter.CartCheckoutHolder> {
    private ModelCount tCount;
    private int countPress;

    private int countWash;

    private int countDry;
//    private int pricePressReal;
//    private int priceWashReal;
//    private int priceDryReal;



    private int countTotalPrice;
   // private int countTotalQuantity = 0;


    private Context tContext;
    private List<ModelCartView> tModels;
    private FragmentManager tFragmentManager;
    private String strMainCatId;
    private String strUserId;
    private CustomToast tToast;
    private CartCheckoutAdapter tAdapter;
    private RecyclerView tRecyclerView;


    public CartCheckoutAdapter(Context tContext, List<ModelCartView> tModels, FragmentManager tFragmentManager,
                               String strUserId, RecyclerView tRecyclerView) {
        this.tContext = tContext;
        this.tModels = tModels;
        this.tFragmentManager = tFragmentManager;
        this.strMainCatId = strMainCatId;
        this.strUserId = strUserId;
        this.tRecyclerView = tRecyclerView;
    }
    @NonNull
    @Override
    public CartCheckoutHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_cart_checkout_item, viewGroup, false);
        return new CartCheckoutHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CartCheckoutHolder cartCheckoutHolder, final int i) {
        tToast = new CustomToast(tContext);
        final ModelCartView tModel = tModels.get(i);

        final String strProdId = tModel.getProdId();


        Glide.with(tContext)
                .load(tModel.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.logo_512x512).error(R.drawable.logo_512x512))
                .into(cartCheckoutHolder.iv_cartCheckout_icon);


        final String strQuantityPress = tModel.getPressQuantity();
        final String strPricePress = tModel.getPressPrice();

        final String strQuantityWash = tModel.getWashQuantity();
        final String strPriceWash = tModel.getWashPrice();

        final String strQuantityDry = tModel.getDryQuantity();
        final String strPriceDry = tModel.getDryPrice();

        final String strQuantityTotal = tModel.getTotalQuantity();
        final String strPriceTotal = tModel.getTotalPrice();

        cartCheckoutHolder.tv_cartCheckoutProduct_name.setText(tModel.getCategoryName() );

        cartCheckoutHolder.tv_cartCheckoutPrice_press.setText("₹ "+strPricePress);
        cartCheckoutHolder.tv_cartCheckoutCount_press.setText(strQuantityPress);

        cartCheckoutHolder.tv_cartCheckoutPrice_wash.setText("₹ "+strPriceWash);
        cartCheckoutHolder.tv_cartCheckoutCount_wash.setText(strQuantityWash);

        cartCheckoutHolder.tv_cartCheckoutPrice_dry.setText("₹ "+strPriceDry);
        cartCheckoutHolder.tv_cartCheckoutCount_dry.setText(strQuantityDry);

        cartCheckoutHolder.tv_cartCheckoutPrice_total.setText("₹ "+strPriceTotal);
        cartCheckoutHolder.tv_cartCheckoutCount_total.setText(strQuantityTotal);


        countPress = Integer.parseInt(tModel.getPressQuantity());
        countWash = Integer.parseInt(tModel.getWashQuantity());
        countDry = Integer.parseInt(tModel.getDryQuantity());

//        String strPricePressReal = tModel.getPriceSteamPress();
//        String strPriceWashReal = tModel.getPriceWashing();
//        String strPriceDryReal = tModel.getPriceDryCleaning();

//        final int pricePressReal;
//        final int priceWashReal;
//        final int priceDryReal;
//
//        if (strPricePressReal.contains("NA")) {
//            strPriceWashReal = "0";
//
//        }
//
//        if (strPriceWashReal.contains("NA")) {
//            strPriceWashReal = "0";
//
//        }
//
//
//        if (strPriceDryReal.contains("NA")) {
//            strPriceDryReal = "0";
//        }

        if ("Na".contains(cartCheckoutHolder.tv_cartCheckoutPrice_press.getText())){
            cartCheckoutHolder.tv_cartCheckoutPrice_press.setText("0");
        }
        if ("Na".contains(cartCheckoutHolder.tv_cartCheckoutPrice_wash.getText())){
            cartCheckoutHolder.tv_cartCheckoutPrice_wash.setText("0");
        }
        if ("Na".contains(cartCheckoutHolder.tv_cartCheckoutPrice_dry.getText())){
            cartCheckoutHolder.tv_cartCheckoutPrice_dry.setText("0");
        } 


    }
    @Override
    public int getItemCount() {
        return tModels.size();
    }
    class CartCheckoutHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_cartCheckout_icon)
        protected ImageView iv_cartCheckout_icon;

        @BindView(R.id.tv_cartCheckoutProduct_name)
        protected TextView tv_cartCheckoutProduct_name;

        @BindView(R.id.tv_cartCheckoutPrice_press)
        protected TextView tv_cartCheckoutPrice_press;
        @BindView(R.id.tv_cartCheckoutCount_press)
        protected TextView tv_cartCheckoutCount_press;

        @BindView(R.id.tv_cartCheckoutPrice_wash)
        protected TextView tv_cartCheckoutPrice_wash;
        @BindView(R.id.tv_cartCheckoutCount_wash)
        protected TextView tv_cartCheckoutCount_wash;

        @BindView(R.id.tv_cartCheckoutPrice_dry)
        protected TextView tv_cartCheckoutPrice_dry;
        @BindView(R.id.tv_cartCheckoutCount_dry)
        protected TextView tv_cartCheckoutCount_dry;

        @BindView(R.id.tv_cartCheckoutPrice_total)
        protected TextView tv_cartCheckoutPrice_total;
        @BindView(R.id.tv_cartCheckoutCount_total)
        protected TextView tv_cartCheckoutCount_total;



        CartCheckoutHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
