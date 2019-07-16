package com.myappartments.laundry.presenter;

import android.content.Context;

import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelOrder;
import com.myappartments.laundry.utils.CustomToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter {

    public static void callApiOrder(String user_id,String status, final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelOrder> call = api.myOrder(user_id, status);
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
