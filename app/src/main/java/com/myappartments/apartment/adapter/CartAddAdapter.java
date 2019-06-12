package com.myappartments.apartment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelCount;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSubCat;
import com.myappartments.apartment.utils.CustomToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAddAdapter extends RecyclerView.Adapter<CartAddAdapter.LaundryHolder> {
    private ModelCount tCount;
    private Context tContext;
    private List<ModelSubCat> tModels;
    private List<ModelDescription> tDescriptions;
    private FragmentManager tFragmentManager;
    private String strMainCatId;
    private String strUserId;
    private CustomToast tToast;


    public CartAddAdapter(Context tContext, List<ModelSubCat> tModels, FragmentManager tFragmentManager,
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







    }
    @Override
    public int getItemCount() {
        return tModels.size();
    }
    class LaundryHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_laundry)
        ImageView ivLaundry;

        @BindView(R.id.tv_laundry_product_name)
        TextView tv_laundry_product_name;

        @BindView(R.id.tv_laundry_price)
        TextView tv_laundry_price;

        @BindView(R.id.et_laundry_quantity)
        EditText et_laundry_quantity;

        @BindView(R.id.btn_laundry_add_to_cart)
        Button btn_laundry_add_to_cart;

        LaundryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
