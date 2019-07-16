package com.myappartments.laundry.presenter;

import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelWallet;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCatPresenter {

    public static void callApiManCat(String strUserId, final MainActivity tActivity){
        tActivity.uiThreadHandler.sendEmptyMessage(Constant.SHOW_PROGRESS_DIALOG);
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelWallet> call = api.userWallet(strUserId);
        call.enqueue(new Callback<ModelWallet>() {
            @Override
            public void onResponse(Call<ModelWallet> call, Response<ModelWallet> response) {
                ModelWallet tModel = response.body();
                String strWallet = tModel.getWallet();
                CustomLog.d(Constant.TAG, "Wallet : "+strWallet);
                tActivity.onResponseApiWallet(response);
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

            }

            @Override
            public void onFailure(Call<ModelWallet> call, Throwable t) {
                CustomLog.d(Constant.TAG, "Wallet not Responding : "+t);
                tActivity.uiThreadHandler.sendMessageDelayed(tActivity.uiThreadHandler.obtainMessage(Constant.HIDE_PROGRESS_DIALOG),Constant.HIDE_PROGRESS_DIALOG_DELAY);

//                tActivity.onFailureApiWallet(call);
            }
        });

    }

}
