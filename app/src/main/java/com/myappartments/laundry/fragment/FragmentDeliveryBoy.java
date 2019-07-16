package com.myappartments.laundry.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.DeliveryBoyActivity;
import com.myappartments.laundry.model.ModelDbApartList;
import com.myappartments.laundry.model.ModelDbFlatList;
import com.myappartments.laundry.storage.SharedPrefManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDeliveryBoy extends Fragment {

    private SharedPrefManager tSharedPrefManager;

    private FragmentManager tFragmentManager;
    private DeliveryBoyActivity tActivity;
    private List<ModelDbApartList> tModels;
    private List<ModelDbFlatList> tModelsFlatLst;
    private Context tContext;
    public String strDbUserId;
    private String strApartId;
    //    private Animation animZoomIn;
    private RecyclerView.LayoutManager tManager;
    @BindView(R.id.spn_db_flats)
    protected Spinner spn_db_flats;
    @BindView(R.id.rvDbApartList)
    protected RecyclerView rvDbApartList;

    @BindView(R.id.ivDbMyOrder)
    protected ImageView ivDbMyOrder;
    @BindView(R.id.ivDbNewOrder)
    protected ImageView ivDbNewOrder;

    @BindView(R.id.llDbOrders)
    protected LinearLayout llDbOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_delivery_boy, container, false);
        ButterKnife.bind(this, view);

//       initFrag();
        return view;
    }

//    private void initFrag(){
//        tContext = getContext();
//        tFragmentManager = getFragmentManager();
//        tSharedPrefManager = new SharedPrefManager(tContext);
//        tManager = new LinearLayoutManager(tContext, LinearLayoutManager.HORIZONTAL, true);
//        rvDbApartList.setLayoutManager(tManager);
//
//        callApiApartList();
//
//        spn_db_flats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                strDbUserId = tModelsFlatLst.get(position).getId();
//                llDbOrders.setVisibility(View.VISIBLE);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }

//    private void callApiApartList(){
//        String strUserId = tSharedPrefManager.getUserId();
//        Api api = ApiClient.getApiClients().create(Api.class);
//        Call<List<ModelDbApartList>> call = api.apartListDb(strUserId);
//        call.enqueue(new Callback<List<ModelDbApartList>>() {
//            @Override
//            public void onResponse(Call<List<ModelDbApartList>> call, Response<List<ModelDbApartList>> response) {
//                tModels = response.body();
//                AdapterDbApartList tAdapter = new AdapterDbApartList(tContext, tModels, FragmentDeliveryBoy.this);
//                rvDbApartList.setAdapter(tAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<ModelDbApartList>> call, Throwable t) {
//
//            }
//        });
//    }
//    public void callApiFlatList(String strApartId){
//        String strUserId = tSharedPrefManager.getUserId();
//        Api api = ApiClient.getApiClients().create(Api.class);
//        Call<List<ModelDbFlatList>> call = api.flatListDb(strUserId, strApartId);
//        call.enqueue(new Callback<List<ModelDbFlatList>>() {
//            @Override
//            public void onResponse(Call<List<ModelDbFlatList>> call, Response<List<ModelDbFlatList>> response) {
//                tModelsFlatLst = response.body();
//                AdapterDbFlatList tAdapter = new AdapterDbFlatList(tContext, tModelsFlatLst);
//                spn_db_flats.setAdapter(tAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<ModelDbFlatList>> call, Throwable t) {
//
//            }
//        });
//    }

//    @OnClick(R.id.ivDbMyOrder)
//    public void ivDbMyOrderClicked(View view){
//
////        Intent myOrderIntent = new Intent(tContext, MainActivity.class);
////        myOrderIntent.putExtra(Constant.DB_USER_ID, strDbUserId);
////        startActivity(myOrderIntent);
//        tFragmentManager.beginTransaction().replace(R.id.container_delivery, FragmentOrderList.newInstance(strDbUserId)).addToBackStack(null).commit();
//        CustomLog.d(Constant.TAG, "Flat Contact Name : "+ strDbUserId);
//    }
//    @OnClick(R.id.ivDbNewOrder)
//    public void ivDbNewOrderClicked(View view ){
//
//    }


}
