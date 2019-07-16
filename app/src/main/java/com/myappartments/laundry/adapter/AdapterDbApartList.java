package com.myappartments.laundry.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.DeliveryBoyActivity;
import com.myappartments.laundry.model.ModelDbApartList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDbApartList extends RecyclerView.Adapter<AdapterDbApartList.ApartViewHolder> {

    private DeliveryBoyActivity tActivity;
    private Context tContext;
    private List<ModelDbApartList> tModels;
    private Animation animZoomIn;
    private int row_index = 999;


    public AdapterDbApartList(Context tContext, List<ModelDbApartList> tModels, DeliveryBoyActivity tActivity ) {
        this.tContext = tContext;
        this.tModels = tModels;
        this.tActivity = tActivity;
    }

    @NonNull
    @Override
    public ApartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_delivery_apart_list_item, viewGroup, false);
        return new ApartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ApartViewHolder apartViewHolder, final int i) {


        final ModelDbApartList tModel = tModels.get(i);


                apartViewHolder.tvDbApartListName.setText(tModel.getAprtName());

        apartViewHolder.ivDbApartListImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tActivity.callApiFlatList(tModel.getId());
                row_index = i;
                notifyDataSetChanged();
            }
        });

        if (row_index == i){
            apartViewHolder.ivDbApartListImage.setBackgroundResource(R.drawable.ic_apart_selected);
        }else {
            apartViewHolder.ivDbApartListImage.setBackgroundResource(R.drawable.ic_apart_unselected);
        }
    }

    @Override
    public int getItemCount() {
        return tModels.size();
    }


    public class ApartViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivDbApartListImage)
        protected ImageView ivDbApartListImage;
        @BindView(R.id.tvDbApartListName)
        protected TextView tvDbApartListName;

       public ApartViewHolder(@NonNull View itemView) {
           super(itemView);
           ButterKnife.bind(this, itemView);
       }
   }

}