package com.myappartments.apartment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myappartments.apartment.R;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.cart.ModelCartAdd;
import com.myappartments.apartment.model.cart.ModelCartView;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCart extends Fragment {

    private Context tContext;
    private SharedPrefManager tSharedPrefManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cart, container, false);
        ButterKnife.bind(this,view);
        initFrag();
        return view;
    }

    private void initFrag(){
        tContext = getContext();
        tSharedPrefManager = new SharedPrefManager(tContext);
        MainActivity tActivity = (MainActivity)getActivity();
        if (tActivity != null){
            tActivity.setTextToolbar("My Cart");
        }
        callApi();
    }
    private void callApi(){
        String strUserId = tSharedPrefManager.getUserId();
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelCartView>> call = api.cartView(strUserId);
        call.enqueue(new Callback<List<ModelCartView>>() {
            @Override
            public void onResponse(Call<List<ModelCartView>> call, Response<List<ModelCartView>> response) {
                List<ModelCartView> tModels = response.body();
                CustomLog.d(Constant.TAG, "CartView Response : "+tModels.get(1));
                CustomLog.d(Constant.TAG, "CartView Response : "+response.message());
            }

            @Override
            public void onFailure(Call<List<ModelCartView>> call, Throwable t) {
                CustomLog.d(Constant.TAG, "CartView not Responding : "+t);

            }
        });
    }

}
