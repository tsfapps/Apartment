package com.myappartments.laundry.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.adapter.AdapterOrderList;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelOrderList;
import com.myappartments.laundry.presenter.OrderListPresenter;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOrderList extends Fragment {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 4;
    private int indexCount = 0;
    int firstVisibleItem, visibleItemCount, totalItemCount;


    private Context tContext;
    private SharedPrefManager tSharedPrefManager;
    private FragmentManager tFragmentManager;
    private String strUserId;
    private String strUserType;
    private FragmentOrderList tFragment;
    private AdapterOrderList tAdapter;
    private MainActivity tActivity;

    @BindView(R.id.ll_emptyOrderList)
    protected LinearLayout ll_emptyOrderList;
    @BindView(R.id.rv_orderList)
    protected RecyclerView rv_orderList;
    @BindView(R.id.swipeRefreshOrderList)
    protected SwipeRefreshLayout swipeRefreshOrderList;

    String strDbUserId;

    public static FragmentOrderList newInstance(String strDbUserId) {

        FragmentOrderList fragment = new FragmentOrderList();
        fragment.strDbUserId = strDbUserId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_order_list, container, false);
        ButterKnife.bind(this, view);
        initFrag();
        return view; }
    private void initFrag(){
        tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Orders");  }
        tContext = getContext();
        tSharedPrefManager = new SharedPrefManager(tContext);
        strUserType = tSharedPrefManager.getUserType();

        if (strUserType.equalsIgnoreCase("0")) {
            strUserId = tSharedPrefManager.getUserId();
        }
        else {
            strUserId = strDbUserId;
        }
        tFragmentManager = getFragmentManager();
        tFragment = new FragmentOrderList();
        final LinearLayoutManager tManager = new LinearLayoutManager(tContext);
        rv_orderList.setLayoutManager(tManager);
        callApi(indexCount);
        swipeRefreshOrderList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshOrderList.setRefreshing(false);
                callApi(indexCount);
            }
        });
    }
    private void callApi(int indexCount){

        CustomLog.d(Constant.TAG, "Order USer Id: "+ strUserId);
        tActivity.uiThreadHandler.sendEmptyMessage(Constant.SHOW_PROGRESS_DIALOG);
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelOrderList>> call = api.myOrderList(strUserId, String.valueOf(indexCount));
        call.enqueue(new Callback<List<ModelOrderList>>() {
            @Override
            public void onResponse(Call<List<ModelOrderList>> call, Response<List<ModelOrderList>> response) {
                List<ModelOrderList> tModels = response.body();
                if (tModels.size() == 0){
                    ll_emptyOrderList.setVisibility(View.VISIBLE);
                    swipeRefreshOrderList.setVisibility(View.GONE);
                }else {
                    ll_emptyOrderList.setVisibility(View.GONE);
                    swipeRefreshOrderList.setVisibility(View.VISIBLE);
                    tAdapter = new AdapterOrderList(tContext, strUserId, strUserType, tModels, FragmentOrderList.this);
                    rv_orderList.setAdapter(tAdapter);
                    tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);
//                setAdapterRv(response);
                }

            }
            @Override
            public void onFailure(Call<List<ModelOrderList>> call, Throwable t) {
                ll_emptyOrderList.setVisibility(View.VISIBLE);
                swipeRefreshOrderList.setVisibility(View.GONE);
                CustomLog.d(Constant.TAG, "Order List Failure : "+t);

                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

            }
        });
    }

    public void showAlert(String strTitle, String strMsg, final Button btnOrderList, final String strId,
                          final String btnText, final int btnBackground, final String strCancelType, final String strOrderStatus){
        final SweetAlertDialog alertDialog = new SweetAlertDialog(tContext, SweetAlertDialog.WARNING_TYPE);
        alertDialog.setTitleText(strTitle);
        alertDialog.setCancelText("No");
        alertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                alertDialog.dismiss();

            }
        });
        alertDialog.setConfirmText("Yes");
        alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                btnOrderList.setEnabled(false);
                btnOrderList.setText(btnText);
                btnOrderList.setBackgroundResource(btnBackground);

                if (strUserType.equalsIgnoreCase("0")){
                    OrderListPresenter.callApiOrderCancel(strUserId, strId, strCancelType, tContext);
                }
                else {
                    switch (strOrderStatus) {
                        case "1":
                        OrderListPresenter.callApiOrderReceived(strUserId, strId, strCancelType, tContext);
                        break;
                        case "4":
                        OrderListPresenter.callApiOrderDispatched(strUserId, strId, strCancelType, tContext);
                        break;
                    }
                }
                alertDialog.dismiss();

            }
        });
        alertDialog.setContentText(strMsg);
        alertDialog.show();
        Button btn = alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(tContext, R.color.green));
        Button btn1 = alertDialog.findViewById(R.id.cancel_button);
        btn1.setBackgroundColor(ContextCompat.getColor(tContext, R.color.red));
    }

    @OnClick(R.id.btn_orderListEmpty)
    public void btn_orderListEmptyClicked(View view){
        tFragmentManager.beginTransaction().replace(R.id.container_main, FragmentLaundry.newInstance("1", strUserId)).commit();
    }
}
