package com.myappartments.laundry.presenter;

import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.activity.PaymentActivity;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelWallet;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;

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
