package com.myappartments.apartment.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myappartments.apartment.R;
import com.myappartments.apartment.model.ModelOrderList;
import com.myappartments.apartment.presenter.OrderListPresenter;
import com.myappartments.apartment.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {

    private Context tContext;
    private String strUserId;
    private List<ModelOrderList> tModels;

    public OrderListAdapter(Context tContext, String strUserId, List<ModelOrderList> tModels) {
        this.tContext = tContext;
        this.strUserId = strUserId;
        this.tModels = tModels;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_order_list_item, viewGroup, false);
        return new OrderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderListViewHolder orderListViewHolder, int i) {

        ModelOrderList tModel = tModels.get(i);
        Glide.with(tContext)
                .load(tModel.getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.logo_512x512).error(R.drawable.logo_512x512))
                .into(orderListViewHolder.iv_orderList_icon);
        orderListViewHolder.tv_orderListPrice_press.setText("₹ "+tModel.getPressPrice());
        orderListViewHolder.tv_orderListCount_press.setText(tModel.getPressQuantity());
        orderListViewHolder.tv_orderListPrice_wash.setText("₹ "+tModel.getWashPrice());
        orderListViewHolder.tv_orderListCount_wash.setText(tModel.getWashQuantity());
        orderListViewHolder.tv_orderListPrice_dry.setText("₹ "+tModel.getDryPrice());
        orderListViewHolder.tv_orderListCount_dry.setText(tModel.getDryQuantity());
        orderListViewHolder.tv_orderListCount_total.setText(tModel.getTotalQuantity());
        orderListViewHolder.tv_orderListPrice_total.setText("₹ "+tModel.getTotalPrice());
        orderListViewHolder.tv_orderListProduct_name.setText(tModel.getCategoryName());

        String strDate = tModel.getDate();
        String strMyDate = DateUtils.ddMMMMyyyy(strDate);
        orderListViewHolder.tv_orderList_date.setText(strMyDate);

        String strStatus = tModel.getStatus();
        if (strStatus.equals("2")){
            orderListViewHolder.btn_orderListCancel.setText("Order Canceled");
            orderListViewHolder.btn_orderListCancel.setEnabled(false);
            orderListViewHolder.btn_orderListCancel.setBackgroundResource(R.drawable.bg_red);
        }
        if (strStatus.equals("3")){
            orderListViewHolder.btn_orderListCancel.setText("Order Completed");
            orderListViewHolder.btn_orderListCancel.setEnabled(false);
            orderListViewHolder.btn_orderListCancel.setBackgroundResource(R.drawable.bg_green);
        }
        final String strId = tModel.getId();
        orderListViewHolder.btn_orderListCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(tContext)
                        .setTitle("Cancel Order")
                        .setMessage("Are you sure you want to cancel the order?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                orderListViewHolder.btn_orderListCancel.setEnabled(false);
                                orderListViewHolder.btn_orderListCancel.setText("Order Canceled");
                                orderListViewHolder.btn_orderListCancel.setBackgroundResource(R.drawable.bg_red);
                                OrderListPresenter.callApiOrderCancel(strUserId,strId, tContext );

                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
//        orderListViewHolder.tv_orderListCount_press.setText(tModel.getPressQuantity());
    }

    @Override
    public int getItemCount() {
        return tModels.size();
    }

    public class OrderListViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_orderList_icon)
        protected ImageView iv_orderList_icon;

        @BindView(R.id.tv_orderListProduct_name)
        protected TextView tv_orderListProduct_name;

        @BindView(R.id.tv_orderListPrice_press)
        protected TextView tv_orderListPrice_press;
        @BindView(R.id.tv_orderListCount_press)
        protected TextView tv_orderListCount_press;

        @BindView(R.id.tv_orderListPrice_wash)
        protected TextView tv_orderListPrice_wash;
        @BindView(R.id.tv_orderListCount_wash)
        protected TextView tv_orderListCount_wash;

        @BindView(R.id.tv_orderListPrice_dry)
        protected TextView tv_orderListPrice_dry;
        @BindView(R.id.tv_orderListCount_dry)
        protected TextView tv_orderListCount_dry;

        @BindView(R.id.tv_orderListPrice_total)
        protected TextView tv_orderListPrice_total;
        @BindView(R.id.tv_orderListCount_total)
        protected TextView tv_orderListCount_total;

        @BindView(R.id.tv_orderList_date)
        protected TextView tv_orderList_date;

        @BindView(R.id.btn_orderListCancel)
        protected Button btn_orderListCancel;
        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
