package com.myappartments.apartment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.adapter.OrderListAdapter;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.ModelOrderList;
import com.myappartments.apartment.presenter.OrderListPresenter;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOrderList extends Fragment {

    private Context tContext;
    private SharedPrefManager tSharedPrefManager;
    private String strUserId;
    private FragmentOrderList tFragment;
    private OrderListAdapter tAdapter;
    private MainActivity tActivity;

    @BindView(R.id.rv_orderList)
    RecyclerView rv_orderList;
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
        strUserId = tSharedPrefManager.getUserId();
        tFragment = new FragmentOrderList();
        RecyclerView.LayoutManager tManager = new LinearLayoutManager(tContext);
        rv_orderList.setLayoutManager(tManager);
        callApi();
    }
    private void callApi(){
        tActivity.uiThreadHandler.sendEmptyMessage(Constant.SHOW_PROGRESS_DIALOG);
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelOrderList>> call = api.cartOrderList(strUserId);
        call.enqueue(new Callback<List<ModelOrderList>>() {
            @Override
            public void onResponse(Call<List<ModelOrderList>> call, Response<List<ModelOrderList>> response) {
                List<ModelOrderList> tModels = response.body();
                CustomLog.d(Constant.TAG, "OrderList Responding : "+ tModels.get(0).getTotalPrice());
                tAdapter = new OrderListAdapter(tContext, strUserId, tModels);
                rv_orderList.setAdapter(tAdapter);
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

            }
            @Override
            public void onFailure(Call<List<ModelOrderList>> call, Throwable t) {
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

            }
        });
    }
}
