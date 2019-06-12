package com.myappartments.apartment.presenter;

import android.content.Context;

import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.ModelContact;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;
import com.myappartments.apartment.utils.CustomToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPresenter {

    public static void callApiContact(String strUserId, String strMessage, final Context tContext){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelContact> call = api.contactUs(strUserId, strMessage);
        call.enqueue(new Callback<ModelContact>() {
            @Override
            public void onResponse(Call<ModelContact> call, Response<ModelContact> response) {
                ModelContact tModel = response.body();
                CustomToast.tToastTop(tContext, tModel.getMessage());
            }

            @Override
            public void onFailure(Call<ModelContact> call, Throwable t) {
                CustomLog.d(Constant.TAG, "Contact Api Not Responding : "+t);
            }
        });
    }
}
