package com.myappartments.apartment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.adapter.OrderListAdapter;
import com.myappartments.apartment.model.ModelOrderList;
import com.myappartments.apartment.presenter.OrderListPresenter;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class FragmentOrderList extends Fragment {

    private Context tContext;
    private SharedPrefManager tSharedPrefManager;
    private String strUserId;
    private FragmentOrderList tFragment;
    private OrderListAdapter tAdapter;

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
       MainActivity tActivity = (MainActivity) getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Orders");
        }
        tContext = getContext();
        tSharedPrefManager = new SharedPrefManager(tContext);
        strUserId = tSharedPrefManager.getUserId();
        tFragment = new FragmentOrderList();
        RecyclerView.LayoutManager tManager = new LinearLayoutManager(tContext);
        rv_orderList.setLayoutManager(tManager);
        OrderListPresenter.callApiOrderList(strUserId, tFragment);
        tAdapter = new OrderListAdapter();
        rv_orderList.setAdapter(tAdapter);
    }
        public void onResponseApiOrderList(Response<List<ModelOrderList>> response) {
            List<ModelOrderList> tModels = response.body();
            tAdapter = new OrderListAdapter();
            rv_orderList.setAdapter(tAdapter);
        }
        public void onFailureApiOrderList(Call<List<ModelOrderList>> call){
        CustomLog.d(Constant.TAG, "Order List Not Responding: "+call); }

}
