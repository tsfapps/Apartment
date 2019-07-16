package com.myappartments.laundry.presenter;

import android.content.Context;

import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.cart.ModelCartAdd;
import com.myappartments.laundry.utils.CustomToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAddPresenter {


//strUserId, prod_id, countPress, pressPrice, countWash,
//                            washPrice, countDry, dryPrice
    public static void callApiCartAdd(String user_id,String prod_id,String countPress,String pressPrice,
                                      String countWash,String washPrice,String countDry,String dryPrice, final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelCartAdd> call = api.cartAdd(user_id, prod_id, countPress, pressPrice, countWash, washPrice, countDry, dryPrice);
        call.enqueue(new Callback<ModelCartAdd>() {
            @Override
            public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
                ModelCartAdd tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());

            }

            @Override
            public void onFailure(Call<ModelCartAdd> call, Throwable t) {

            }
        });
    }

}
