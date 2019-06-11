package com.myappartments.apartment.presenter;

import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.activity.PaymentActivity;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.ModelWallet;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletPresenter {


    public static void callApiWallet(String strUserId, final MainActivity tActivity){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelWallet> call = api.userWallet(strUserId);
        call.enqueue(new Callback<ModelWallet>() {
            @Override
            public void onResponse(Call<ModelWallet> call, Response<ModelWallet> response) {
                ModelWallet tModel = response.body();
                String strWallet = tModel.getWallet();
                CustomLog.d(Constant.TAG, "Wallet : "+strWallet);
                tActivity.onResponseApiWallet(response);
            }

            @Override
            public void onFailure(Call<ModelWallet> call, Throwable t) {
                CustomLog.d(Constant.TAG, "Wallet not Responding : "+t);

//                tActivity.onFailureApiWallet(call);
            }
        });

    }
    public static void callApiWalletPayment(String strUserId, final PaymentActivity tActivity){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelWallet> call = api.userWallet(strUserId);
        call.enqueue(new Callback<ModelWallet>() {
            @Override
            public void onResponse(Call<ModelWallet> call, Response<ModelWallet> response) {
                ModelWallet tModel = response.body();
                String strWallet = tModel.getWallet();

//                tActivity.onResponseApiWallet(response);


            }

            @Override
            public void onFailure(Call<ModelWallet> call, Throwable t) {

//                tActivity.onFailureApiWallet(call);
            }
        });

    }
}
