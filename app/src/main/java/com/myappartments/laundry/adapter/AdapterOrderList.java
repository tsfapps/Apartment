package com.myappartments.laundry.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myappartments.laundry.R;
import com.myappartments.laundry.fragment.FragmentOrderList;
import com.myappartments.laundry.model.ModelOrderList;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomFunction;
import com.myappartments.laundry.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterOrderList extends RecyclerView.Adapter<AdapterOrderList.OrderListViewHolder> {


    private Context tContext;
    private String strUserId;
    private String strUserType;
    private List<ModelOrderList> tModels;
    private FragmentOrderList tFragmentOrderList;

    public AdapterOrderList(Context tContext, String strUserId, String strUserType, List<ModelOrderList> tModels, FragmentOrderList tFragmentOrderList) {
        this.tContext = tContext;
        this.strUserId = strUserId;
        this.strUserType = strUserType ;
        this.tModels = tModels;
        this.tFragmentOrderList = tFragmentOrderList;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_order_list_item, viewGroup, false);
        return new OrderListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final OrderListViewHolder orderListViewHolder, int i) {

        final ModelOrderList tModel = tModels.get(i);
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
        String strDate = tModel.getPlacedDatetime();
        String strMyDate = DateUtils.ddMMMMyyyy(strDate);
        orderListViewHolder.tv_orderList_date.setText(strMyDate);




        switch (tModel.getPressStatus()){
            case "0":
                orderListViewHolder.btn_orderListCancelPress.setEnabled(false);
                break;
        case "1":
            if (strUserType.equalsIgnoreCase("0")) {
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.CANCEL_ORDER, true, R.drawable.bg_primary);

            }
            else {
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.RECEIVE_ORDER, true, R.drawable.bg_primary);
            }

            break;
        case "2":
            CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.ORDER_RECEIVED, false, R.drawable.bg_yellow);

            break;
        case "3":
            CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.ORDER_CANCELED, false, R.drawable.bg_red);

            break;
        case "4":
            if (strUserType.equalsIgnoreCase("0")) {
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.ORDER_READY_TO_DELIVER, false, R.drawable.bg_pink);
            }else {
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.DELIVER_ORDER, true, R.drawable.bg_pink);
            }
            break;
            case "5":
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.ORDER_COMPLETED, false, R.drawable.bg_green);

                break;
            default:
                orderListViewHolder.btn_orderListCancelPress.setVisibility(View.GONE);
                break;
        }
        final String strId = tModel.getId();
        orderListViewHolder.btn_orderListCancelPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strUserType.equalsIgnoreCase("0")) {

                    tFragmentOrderList.showAlert(Constant.CANCEL_ORDER,Constant.MSG_CANCEL_ORDER,
                            orderListViewHolder.btn_orderListCancelPress, strId, Constant.ORDER_CANCELED, R.drawable.bg_red, Constant.STR_PRESS, "");
                }
                else {
                   switch (tModel.getPressStatus()){
                       case "1":
                        tFragmentOrderList.showAlert(Constant.RECEIVE_ORDER, Constant.MSG_RECEIVE_ORDER,
                                orderListViewHolder.btn_orderListCancelPress, strId, Constant.ORDER_RECEIVED, R.drawable.bg_yellow, Constant.STR_PRESS, "1");
                   break;
                       case "4":
                        tFragmentOrderList.showAlert(Constant.DELIVER_ORDER, Constant.MSG_DELIVER_ORDER,
                                orderListViewHolder.btn_orderListCancelPress, strId, Constant.ORDER_COMPLETED, R.drawable.bg_green, Constant.STR_PRESS, "4");
                        break;
                    }

                }
            }
        });



        switch (tModel.getWashStatus()){
            case "0":
                orderListViewHolder.btn_orderListCancelWash.setEnabled(false);
                break;
            case "1":
                if (strUserType.equalsIgnoreCase("0")) {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelWash, Constant.CANCEL_ORDER, true, R.drawable.bg_primary);
                }
                else {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelWash, Constant.RECEIVE_ORDER, true, R.drawable.bg_primary);
                }
                break;
            case "2":
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelWash, Constant.ORDER_RECEIVED, false, R.drawable.bg_yellow);

                break;
            case "3":
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelWash, Constant.ORDER_CANCELED, false, R.drawable.bg_red);

                break;
            case "4":
                if (strUserType.equalsIgnoreCase("0")) {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelWash, Constant.ORDER_READY_TO_DELIVER, false, R.drawable.bg_pink);
                }else {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelWash, Constant.DELIVER_ORDER, true, R.drawable.bg_pink);
                }
                break;
            case "5":
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelWash, Constant.ORDER_COMPLETED, false, R.drawable.bg_green);

                break;
                default:
                    orderListViewHolder.btn_orderListCancelWash.setVisibility(View.GONE);
                break;
        }

        orderListViewHolder.btn_orderListCancelWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strUserType.equalsIgnoreCase("0")) {
                    tFragmentOrderList.showAlert(Constant.CANCEL_ORDER,Constant.MSG_CANCEL_ORDER,
                            orderListViewHolder.btn_orderListCancelWash, strId, Constant.ORDER_CANCELED, R.drawable.bg_red, Constant.STR_WASH, "");
                }
                else {
                    switch (tModel.getWashStatus()){
                        case "1":
                            tFragmentOrderList.showAlert(Constant.RECEIVE_ORDER,Constant.MSG_RECEIVE_ORDER,
                                    orderListViewHolder.btn_orderListCancelWash, strId, Constant.ORDER_RECEIVED, R.drawable.bg_yellow, Constant.STR_WASH, "1");
                            break;
                        case "4":
                            tFragmentOrderList.showAlert(Constant.DELIVER_ORDER, Constant.MSG_DELIVER_ORDER,
                                    orderListViewHolder.btn_orderListCancelWash, strId, Constant.ORDER_COMPLETED, R.drawable.bg_green, Constant.STR_WASH, "4");
                            break;
                    }

                }
            }
        });

        switch (tModel.getDryStatus()){
            case "0":
                orderListViewHolder.btn_orderListCancelDry.setEnabled(false);
                break;
            case "1":
                if (strUserType.equalsIgnoreCase("0")) {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelDry, Constant.CANCEL_ORDER, true, R.drawable.bg_primary);
                }
                else {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelDry, Constant.RECEIVE_ORDER, true, R.drawable.bg_primary);
                }
                break;
            case "2":
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelDry, Constant.ORDER_RECEIVED, false, R.drawable.bg_yellow);

                break;
            case "3":
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelDry, Constant.ORDER_CANCELED, false, R.drawable.bg_red);

                break;
            case "4":
                if (strUserType.equalsIgnoreCase("0")) {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelDry, Constant.ORDER_READY_TO_DELIVER, false, R.drawable.bg_pink);
                }else {
                    CustomFunction.callButton(orderListViewHolder.btn_orderListCancelDry, Constant.DELIVER_ORDER, true, R.drawable.bg_pink);
                }
                break;
            case "5":
                CustomFunction.callButton(orderListViewHolder.btn_orderListCancelPress, Constant.ORDER_COMPLETED, false, R.drawable.bg_green);

                break;
            default:
                orderListViewHolder.btn_orderListCancelDry.setVisibility(View.GONE);
                break;
        }

        orderListViewHolder.btn_orderListCancelDry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strUserType.equalsIgnoreCase("0")) {
                    tFragmentOrderList.showAlert(Constant.CANCEL_ORDER,Constant.MSG_CANCEL_ORDER,
                            orderListViewHolder.btn_orderListCancelDry, strId, Constant.ORDER_CANCELED, R.drawable.bg_red, Constant.STR_DRY, "");
                }
                else {
                    switch (tModel.getDryStatus()){
                        case "1":
                            tFragmentOrderList.showAlert(Constant.RECEIVE_ORDER,Constant.MSG_RECEIVE_ORDER,
                                    orderListViewHolder.btn_orderListCancelDry, strId, Constant.ORDER_RECEIVED, R.drawable.bg_yellow, Constant.STR_WASH, "1");
                            break;
                        case "4":
                            tFragmentOrderList.showAlert(Constant.DELIVER_ORDER, Constant.MSG_DELIVER_ORDER,
                                    orderListViewHolder.btn_orderListCancelDry, strId, Constant.ORDER_COMPLETED, R.drawable.bg_green, Constant.STR_WASH, "4");
                            break;
                    }
                }
            }
        });
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
        @BindView(R.id.btn_orderListCancelPress)
        protected Button btn_orderListCancelPress;
        @BindView(R.id.btn_orderListCancelWash)
        protected Button btn_orderListCancelWash;
        @BindView(R.id.btn_orderListCancelDry)
        protected Button btn_orderListCancelDry;
        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
