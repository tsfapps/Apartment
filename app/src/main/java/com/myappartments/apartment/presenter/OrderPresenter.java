package com.myappartments.apartment.presenter;

import android.content.Context;

import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.ModelOrder;
import com.myappartments.apartment.utils.CustomToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter {

    public static void callApiOrder(String user_id,String status, final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelOrder> call = api.cartOrder(user_id, status);
        call.enqueue(new Callback<ModelOrder>() {
            @Override
            public void onResponse(Call<ModelOrder> call, Response<ModelOrder> response) {
                ModelOrder tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());

            }

            @Override
            public void onFailure(Call<ModelOrder> call, Throwable t) {

            }
        });
    }
}
