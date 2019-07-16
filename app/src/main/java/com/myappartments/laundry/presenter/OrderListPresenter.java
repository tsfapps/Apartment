package com.myappartments.laundry.presenter;

import android.content.Context;

import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelDispatched;
import com.myappartments.laundry.model.ModelOrderCancel;
import com.myappartments.laundry.model.ModelReceived;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;
import com.myappartments.laundry.utils.CustomToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListPresenter {
//    public static void callApiOrderList(String strUserId, final FragmentOrderList tFragment){
//        Api api = ApiClient.getApiClients().create(Api.class);
//        Call<List<ModelOrderList>> call = api.myOrderList(strUserId);
//        call.enqueue(new Callback<List<ModelOrderList>>() {
//            @Override
//            public void onResponse(Call<List<ModelOrderList>> call, Response<List<ModelOrderList>> response) {
////               tFragment.onResponseApiOrderList(response);
//            }
//
//            @Override
//            public void onFailure(Call<List<ModelOrderList>> call, Throwable t) {
////                tFragment.onFailureApiOrderList(call);
//
//            }
//        });
//
//    }
    public static void callApiOrderCancel(String strUserId, String strId, String strCancelType, final Context tContext){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelOrderCancel> call = api.orderCancel(strUserId, strCancelType, strId);
        call.enqueue(new Callback<ModelOrderCancel>() {
            @Override
            public void onResponse(Call<ModelOrderCancel> call, Response<ModelOrderCancel> response) {
                ModelOrderCancel tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelOrderCancel> call, Throwable t) {
                CustomLog.d(Constant.TAG, "Order Cancel Not Responding : "+t);
            }
        });

    }
    public static void callApiOrderReceived(String strUserId, String strId, String strCancelType, final Context tContext){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelReceived> call = api.orderReceived(strUserId, strCancelType, strId);
        call.enqueue(new Callback<ModelReceived>() {
            @Override
            public void onResponse(Call<ModelReceived> call, Response<ModelReceived> response) {
                ModelReceived tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelReceived> call, Throwable t) {
                CustomLog.d(Constant.TAG, "Order Cancel Not Responding : "+t);
            }
        });

    }
    public static void callApiOrderDispatched(String strUserId, String strId, String strCancelType, final Context tContext){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelDispatched> call = api.orderDispatched(strUserId, strCancelType, strId);
        call.enqueue(new Callback<ModelDispatched>() {
            @Override
            public void onResponse(Call<ModelDispatched> call, Response<ModelDispatched> response) {
                ModelDispatched tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelDispatched> call, Throwable t) {
                CustomLog.d(Constant.TAG, "Order Cancel Not Responding : "+t);
            }
        });

    }

}
