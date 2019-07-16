package com.myappartments.laundry.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.myappartments.laundry.R;
import com.myappartments.laundry.adapter.AdapterDbApartList;
import com.myappartments.laundry.adapter.AdapterDbFlatList;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelDbApartList;
import com.myappartments.laundry.model.ModelDbFlatList;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryBoyActivity extends AppCompatActivity {
    private  SharedPrefManager tSharedPrefManager;

    private List<ModelDbApartList> tModels;
    private List<ModelDbFlatList> tModelsFlatLst;
    private  Context tContext;
    public String strDbUserId;
    private String strUserId;
//    private Animation animZoomIn;
    private RecyclerView.LayoutManager tManager;
    @BindView(R.id.spn_db_flats)
    protected  Spinner spn_db_flats;
    @BindView(R.id.rvDbApartList)
    protected RecyclerView rvDbApartList;

    @BindView(R.id.tvDbName)
    protected TextView tvDbName;
    @BindView(R.id.ivDbMyOrder)
    protected ImageView ivDbMyOrder;
    @BindView(R.id.ivDbNewOrder)
    protected ImageView ivDbNewOrder;

    @BindView(R.id.llDbOrders)
    protected LinearLayout llDbOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_boy);
        ButterKnife.bind(this);

        initActivity();
        callApiApartList();


    }
    private void initActivity(){
        tContext = getApplicationContext();
        tSharedPrefManager = new SharedPrefManager(tContext);
        tManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        rvDbApartList.setLayoutManager(tManager);
        strUserId = tSharedPrefManager.getUserId();
        tvDbName.setText(tSharedPrefManager.getUserName());
        spn_db_flats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDbUserId = tModelsFlatLst.get(position).getUserId();
                llDbOrders.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void callApiApartList(){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelDbApartList>> call = api.apartListDb(strUserId);
        call.enqueue(new Callback<List<ModelDbApartList>>() {
            @Override
            public void onResponse(Call<List<ModelDbApartList>> call, Response<List<ModelDbApartList>> response) {
                tModels = response.body();
                AdapterDbApartList tAdapter = new AdapterDbApartList(tContext, tModels, DeliveryBoyActivity.this);
                rvDbApartList.setAdapter(tAdapter);

            }

            @Override
            public void onFailure(Call<List<ModelDbApartList>> call, Throwable t) {

            }
        });
    }

        public void callApiFlatList(String strApartId){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelDbFlatList>> call = api.flatListDb(strUserId, strApartId);
        call.enqueue(new Callback<List<ModelDbFlatList>>() {
            @Override
            public void onResponse(Call<List<ModelDbFlatList>> call, Response<List<ModelDbFlatList>> response) {
                  tModelsFlatLst = response.body();
                AdapterDbFlatList tAdapter = new AdapterDbFlatList(tContext, tModelsFlatLst);
                spn_db_flats.setAdapter(tAdapter);
            }

            @Override
            public void onFailure(Call<List<ModelDbFlatList>> call, Throwable t) {

            }
        });
    }


    // Calling Service

    @OnClick(R.id.ivDbMyOrder)
    public void ivDbMyOrderClicked(View view){

        Intent myOrderIntent = new Intent(DeliveryBoyActivity.this, MainActivity.class);
        myOrderIntent.putExtra(Constant.DB_USER_ID, strDbUserId);
        myOrderIntent.putExtra(Constant.DB_SERVICE_TYPE, "My Orders");
        startActivity(myOrderIntent);

    }
    @OnClick(R.id.ivDbNewOrder)
    public void ivDbNewOrderClicked(View view ){
        Intent newOrderIntent = new Intent(DeliveryBoyActivity.this, MainActivity.class);
        newOrderIntent.putExtra(Constant.DB_USER_ID, strDbUserId);
        newOrderIntent.putExtra(Constant.DB_SERVICE_TYPE, "New Order");
        startActivity(newOrderIntent);
    }


}
