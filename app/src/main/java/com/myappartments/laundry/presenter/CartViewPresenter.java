package com.myappartments.laundry.presenter;

import android.content.Context;

import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.cart.ModelCartAdd;
import com.myappartments.laundry.model.cart.ModelCartDelete;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;
import com.myappartments.laundry.utils.CustomToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewPresenter {



    public static void callApiCartDelete(String id, final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelCartDelete> call = api.cartDelete(id);
        call.enqueue(new Callback<ModelCartDelete>() {
            @Override
            public void onResponse(Call<ModelCartDelete> call, Response<ModelCartDelete> response) {
                ModelCartDelete tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelCartDelete> call, Throwable t) {

                CustomLog.d(Constant.TAG, "Delete Cart Not Responding : "+ t);
            }
        });
    }



    public static void callApiAdd(String user_id,String prod_id,String press_quantity,String press_price,
                                            String wash_quantity, String wash_price,String dry_quantity,String dry_price,
                                           final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelCartAdd> call = api.cartAdd(user_id, prod_id, press_quantity, press_price, wash_quantity,
                wash_price, dry_quantity, dry_price);
        call.enqueue(new Callback<ModelCartAdd>() {
            @Override
            public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
                ModelCartAdd tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelCartAdd> call, Throwable t) {

                CustomLog.d(Constant.TAG, "Delete Cart Not Responding : "+ t);
            }
        });
    }
    public static void callApiCartPress(String user_id,String prod_id,String press_quantity,String press_price,
                                            String wash_price,String dry_price,
                                           final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelCartAdd> call = api.cartAddPress(user_id, prod_id, press_quantity, press_price, wash_price, dry_price);
        call.enqueue(new Callback<ModelCartAdd>() {
            @Override
            public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
                ModelCartAdd tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelCartAdd> call, Throwable t) {

                CustomLog.d(Constant.TAG, "Delete Cart Not Responding : "+ t);
            }
        });
    }
    public static void callApiCartWash(String user_id,String prod_id,String press_price,
                                           String wash_quantity, String wash_price,String dry_price,
                                           final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelCartAdd> call = api.cartAddWash(user_id, prod_id, wash_quantity, press_price, wash_price, dry_price);
        call.enqueue(new Callback<ModelCartAdd>() {
            @Override
            public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
                ModelCartAdd tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelCartAdd> call, Throwable t) {

                CustomLog.d(Constant.TAG, "Delete Cart Not Responding : "+ t);
            }
        });
    }
    public static void callApiCartDry(String user_id,String prod_id,String press_price,
                                            String wash_price,String dry_quantity,String dry_price,
                                           final Context tContext){

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelCartAdd> call = api.cartAddDry(user_id, prod_id, dry_quantity, press_price, wash_price, dry_price);
        call.enqueue(new Callback<ModelCartAdd>() {
            @Override
            public void onResponse(Call<ModelCartAdd> call, Response<ModelCartAdd> response) {
                ModelCartAdd tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelCartAdd> call, Throwable t) {

                CustomLog.d(Constant.TAG, "Delete Cart Not Responding : "+ t);
            }
        });
    }

}
