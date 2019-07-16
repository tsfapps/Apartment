package com.myappartments.laundry.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myappartments.laundry.R;
import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.activity.PaymentActivity;
import com.myappartments.laundry.adapter.AdapterCartCheckout;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.cart.ModelCartView;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCartCheckout extends Fragment {

    private Context tContext;
    private SharedPrefManager tSharedPrefManager;
    private FragmentManager tFragmentManager;
    private RecyclerView.LayoutManager tLayoutManager;
    private AdapterCartCheckout tAdapter;


    @BindView(R.id.rv_cartCheckout)
    protected RecyclerView rv_cartCheckout;
    @BindView(R.id.btn_cartCheckoutPayment)
    protected Button btn_cartCheckoutPayment;
    private String strUserId;
    private String strDbUserId;

    public static FragmentCartCheckout newInstance(String strDbUserId) {

        FragmentCartCheckout fragment = new FragmentCartCheckout();
        fragment.strDbUserId = strDbUserId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cart_checkout, container, false);
        ButterKnife.bind(this,view);
        initFrag();
        callApi();
        return view;
    }

    private void initFrag(){
        MainActivity tActivity = (MainActivity)getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("Checkout");
        }


        tContext = getContext();
        tFragmentManager = getFragmentManager();
        tSharedPrefManager = new SharedPrefManager(tContext);
        String strUserType = tSharedPrefManager.getUserType();
        if (strUserType.equalsIgnoreCase("0")){
            strUserId = tSharedPrefManager.getUserId();
        } else {strUserId = strDbUserId;}

        tLayoutManager = new LinearLayoutManager(tContext);
        rv_cartCheckout.setLayoutManager(tLayoutManager);

    }
    private void callApi(){

        CustomLog.d(Constant.TAG,"CheckOut UserId : "+strUserId);
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelCartView>> call = api.cartView(strUserId);
        call.enqueue(new Callback<List<ModelCartView>>() {
            @Override
            public void onResponse(Call<List<ModelCartView>> call, Response<List<ModelCartView>> response) {
                List<ModelCartView> tModels = response.body();

                int countSize = 0;
               int totalAmount =  0;

                if (tModels != null) {
                    countSize = tModels.size();
                }
                for (int i = 0; i< countSize; i++){
                    String[] strTotalPrice = new String[countSize];
                    strTotalPrice[i]  = tModels.get(i).getTotalPrice();
                    totalAmount = totalAmount + Integer.parseInt(strTotalPrice[i]);
                }
              final String strTotalGrand = String.valueOf(totalAmount);
                btn_cartCheckoutPayment.setText("Proceed to Pay ₹ "+strTotalGrand);

                btn_cartCheckoutPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent tIntent = new Intent(tContext, PaymentActivity.class);
                        tIntent.putExtra("TOTAL_AMOUNT", strTotalGrand);
                        tIntent.putExtra(Constant.DB_USER_ID, strUserId);
                        tContext.startActivity(tIntent);
                        getActivity().finishAffinity();
                    }
                });

                tAdapter = new AdapterCartCheckout(tContext, tModels, tFragmentManager, strUserId, rv_cartCheckout);
                rv_cartCheckout.setAdapter(tAdapter);
            }
            @Override
            public void onFailure(Call<List<ModelCartView>> call, Throwable t) {
                CustomLog.d(Constant.TAG, "CartView not Responding : "+t);

            }
        });
    }

//    @OnClick(R.id.btn_cartCheckoutPayment)
//    public void clickPayment(View view){
//
//
//
//        Intent tIntent = new Intent(tContext, PaymentActivity.class);
//        tIntent.putExtra("TOTAL_AMNT", )
//    }
}
