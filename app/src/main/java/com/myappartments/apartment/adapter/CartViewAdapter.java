package com.myappartments.apartment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myappartments.apartment.R;
import com.myappartments.apartment.fragment.FragmentCartView;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.cart.ModelCartView;
import com.myappartments.apartment.presenter.CartAddPresenter;
import com.myappartments.apartment.presenter.CartViewPresenter;
import com.myappartments.apartment.utils.AnimationCustom;
import com.myappartments.apartment.utils.CustomToast;
import com.myappartments.apartment.utils.DisableView;
import com.myappartments.apartment.utils.SetSpinnerValue;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.CartViewHolder> {
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
    private CartViewAdapter tAdapter;
    private RecyclerView tRecyclerView;


    public CartViewAdapter(Context tContext, List<ModelCartView> tModels, FragmentManager tFragmentManager,
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
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_cart_view_item, viewGroup, false);
        return new CartViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, final int i) {
        tToast = new CustomToast(tContext);
        final ModelCartView tModel = tModels.get(i);

        final String strProdId = tModel.getProdId();


        Glide.with(tContext)
                .load(tModel.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.logo_512x512).error(R.drawable.logo_512x512))
                .into(cartViewHolder.iv_cartView_icon);

        cartViewHolder.iv_cart_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(tContext);
                alertDialog.setTitle("Remove from Cart");
                alertDialog.setMessage("Are you sure want to remove from the cart");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CartViewPresenter.callApiCartDelete(tModel.getId(), tContext);
                        tModels.remove(i);
                        tRecyclerView.removeViewAt(i);
                        notifyItemRemoved(i);
                        notifyItemRangeChanged(i, tModels.size());
                        tFragmentManager.beginTransaction().replace(R.id.container_main, new FragmentCartView()).addToBackStack(null).commit();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }


        });

        final String strQuantityPress = tModel.getPressQuantity();
        final String strPricePress = tModel.getPressPrice();

        final String strQuantityWash = tModel.getWashQuantity();
        final String strPriceWash = tModel.getWashPrice();

        final String strQuantityDry = tModel.getDryQuantity();
        final String strPriceDry = tModel.getDryPrice();

        final String strQuantityTotal = tModel.getTotalQuantity();
        final String strPriceTotal = tModel.getTotalPrice();

        cartViewHolder.tv_cart_name.setText(tModel.getCategoryName() );

        SetSpinnerValue.setSpinner(cartViewHolder.spn_count_press_cart, strQuantityPress);
        cartViewHolder.tv_cart_price_press.setText(strPricePress);
        cartViewHolder.tv_spinnerValuePress.setText(strQuantityPress);


        SetSpinnerValue.setSpinner(cartViewHolder.spn_count_wash_cart, strQuantityWash);
        cartViewHolder.tv_cart_price_wash.setText(strPriceWash);
        cartViewHolder.tv_spinnerValueWash.setText(strQuantityWash);


        SetSpinnerValue.setSpinner(cartViewHolder.spn_count_dry_cart, strQuantityDry);
        cartViewHolder.tv_cart_price_dry.setText(strPriceDry);
        cartViewHolder.tv_spinnerValueDry.setText(strQuantityDry);


        cartViewHolder.tv_count_total_cart.setText(strQuantityTotal);
        cartViewHolder.tv_cart_price_total.setText(strPriceTotal);


        countPress = Integer.parseInt(tModel.getPressQuantity());
        countWash = Integer.parseInt(tModel.getWashQuantity());
        countDry = Integer.parseInt(tModel.getDryQuantity());

        String strPricePressReal = tModel.getPriceSteamPress();
        String strPriceWashReal = tModel.getPriceWashing();
        String strPriceDryReal = tModel.getPriceDryCleaning();

        final int pricePressReal;
        final int priceWashReal;
        final int priceDryReal;


        if (strPricePressReal.contains("NA")) {
            strPricePressReal = "0";
            DisableView.disableTextView(cartViewHolder.tv_cart_price_press);
            DisableView.disableSpinner(cartViewHolder.spn_count_press_cart);
        }
        pricePressReal = Integer.parseInt(strPricePressReal);

        if (strPriceWashReal.contains("NA")) {
            strPriceWashReal = "0";
            DisableView.disableTextView(cartViewHolder.tv_cart_price_wash);
            DisableView.disableSpinner(cartViewHolder.spn_count_wash_cart);
        }
        priceWashReal = Integer.parseInt(strPriceWashReal);


        if (strPriceDryReal.contains("NA")) {
            strPriceDryReal = "0";
            DisableView.disableTextView(cartViewHolder.tv_cart_price_dry);
            DisableView.disableSpinner(cartViewHolder.spn_count_dry_cart);
        }
            priceDryReal = Integer.parseInt(strPriceDryReal);


        if ("Na".contains(cartViewHolder.tv_cart_price_press.getText())){
            cartViewHolder.tv_cart_price_press.setText("0");
        }
        if ("Na".contains(cartViewHolder.tv_cart_price_wash.getText())){
            cartViewHolder.tv_cart_price_wash.setText("0");
        }
        if ("Na".contains(cartViewHolder.tv_cart_price_dry.getText())){
            cartViewHolder.tv_cart_price_dry.setText("0");
        }
        cartViewHolder.spn_count_press_cart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                countPress = position * pricePressReal;
                String strCountPrice = String.valueOf(countPress);
                cartViewHolder.tv_cart_price_press.setText(strCountPrice);

                int countPress = Integer.parseInt(cartViewHolder.tv_spinnerValuePress.getText().toString());
                int countTotalGet = Integer.parseInt(cartViewHolder.tv_count_total_cart.getText().toString());
                int countTotal = countTotalGet + position-countPress;
                cartViewHolder.tv_count_total_cart.setText(String.valueOf(countTotal));
                cartViewHolder.tv_spinnerValuePress.setText(String.valueOf(position));
                int countPressPrice = Integer.parseInt(cartViewHolder.tv_cart_price_press.getText().toString());

                int countWashPrice = Integer.parseInt(cartViewHolder.tv_cart_price_wash.getText().toString());
                int countDryPrice = Integer.parseInt(cartViewHolder.tv_cart_price_dry.getText().toString());
                int countTotalPrice = countPressPrice+countWashPrice+countDryPrice;
                cartViewHolder.tv_cart_price_total.setText(String.valueOf(countTotalPrice));

//                CartViewPresenter.callApiAdd(strUserId,strProdId, String.valueOf(countPress), String.valueOf(countPressPrice),
//                        String.valueOf(countWash), String.valueOf(countWashPrice), String.valueOf(countDry), String.valueOf(countDryPrice), tContext);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        cartViewHolder.spn_count_wash_cart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
               int countWashQuantity = position * priceWashReal;
                String strCountPrice = String.valueOf(countWashQuantity);
                cartViewHolder.tv_cart_price_wash.setText(strCountPrice);
                String strCount = String.valueOf(position);

                int countWash = Integer.parseInt(cartViewHolder.tv_spinnerValueWash.getText().toString());
                int countTotalGet = Integer.parseInt(cartViewHolder.tv_count_total_cart.getText().toString());
                int countTotal = countTotalGet + position-countWash;
                cartViewHolder.tv_count_total_cart.setText(String.valueOf(countTotal));
                cartViewHolder.tv_spinnerValueWash.setText(String.valueOf(position));

                int countPressPrice = Integer.parseInt(cartViewHolder.tv_cart_price_press.getText().toString());
                int countWashPrice = Integer.parseInt(cartViewHolder.tv_cart_price_wash.getText().toString());
                int countDryPrice = Integer.parseInt(cartViewHolder.tv_cart_price_dry.getText().toString());
                int countTotalPrice = countPressPrice+countWashPrice+countDryPrice;
                cartViewHolder.tv_cart_price_total.setText(String.valueOf(countTotalPrice));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        cartViewHolder.spn_count_dry_cart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                int countDryQuantity = position * priceDryReal;
                String strCountPrice = String.valueOf(countDryQuantity);
                cartViewHolder.tv_cart_price_dry.setText(strCountPrice);

                int countDry = Integer.parseInt(cartViewHolder.tv_spinnerValueDry.getText().toString());
                int countTotalGet = Integer.parseInt(cartViewHolder.tv_count_total_cart.getText().toString());
                int countTotal = countTotalGet + position-countDry;
                cartViewHolder.tv_count_total_cart.setText(String.valueOf(countTotal));
                cartViewHolder.tv_spinnerValueDry.setText(String.valueOf(position));

                int countPressPrice = Integer.parseInt(cartViewHolder.tv_cart_price_press.getText().toString());
                int countWashPrice = Integer.parseInt(cartViewHolder.tv_cart_price_wash.getText().toString());
                int countDryPrice = Integer.parseInt(cartViewHolder.tv_cart_price_dry.getText().toString());
                int countTotalPrice = countPressPrice+countWashPrice+countDryPrice;
                cartViewHolder.tv_cart_price_total.setText(String.valueOf(countTotalPrice));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        cartViewHolder.tv_cart_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationCustom.animRotate(cartViewHolder.tv_cart_update);
                String strCountPress = cartViewHolder.tv_spinnerValuePress.getText().toString();
                String strCountWash = cartViewHolder.tv_spinnerValueWash.getText().toString();
                String strCountDry = cartViewHolder.tv_spinnerValueDry.getText().toString();
                String strPricePress = cartViewHolder.tv_cart_price_press.getText().toString();
                String strPriceWash = cartViewHolder.tv_cart_price_wash.getText().toString();
                String strPriceDry = cartViewHolder.tv_cart_price_dry.getText().toString();
                CartAddPresenter.callApiCartAdd(strUserId, strProdId, strCountPress, strPricePress,
                        strCountWash, strPriceWash, strCountDry, strPriceDry, tContext);
            }
        });


    }
    @Override
    public int getItemCount() {
        return tModels.size();
    }
    class CartViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_cartView_icon)
        protected ImageView iv_cartView_icon;

        @BindView(R.id.tv_cart_name)
        protected TextView tv_cart_name;
        @BindView(R.id.spn_count_item_press_cart)
        protected Spinner spn_count_press_cart;
        @BindView(R.id.tv_spinnerValuePress)
        protected TextView tv_spinnerValuePress;
        @BindView(R.id.tv_cart_price_press)
        protected TextView tv_cart_price_press;

        @BindView(R.id.spn_count_wash_cart)
        protected Spinner spn_count_wash_cart;
        @BindView(R.id.tv_cart_price_wash)
        protected TextView tv_cart_price_wash;
        @BindView(R.id.tv_spinnerValueWash)
        protected TextView tv_spinnerValueWash;

        @BindView(R.id.spn_count_dry_cart)
        protected Spinner spn_count_dry_cart;
        @BindView(R.id.tv_cart_price_dry)
        protected TextView tv_cart_price_dry;
        @BindView(R.id.tv_spinnerValueDry)
        protected TextView tv_spinnerValueDry;

        @BindView(R.id.tv_count_item_total_cart)
        protected TextView tv_count_total_cart;
        @BindView(R.id.tv_cart_price_total)
        protected TextView tv_cart_price_total;

        @BindView(R.id.iv_cart_delete)
        protected ImageView iv_cart_delete;
        @BindView(R.id.tv_cart_update)
        protected TextView tv_cart_update;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
