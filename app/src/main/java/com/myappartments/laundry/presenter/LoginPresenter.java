package com.myappartments.laundry.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.myappartments.laundry.activity.DeliveryBoyActivity;
import com.myappartments.laundry.activity.MainActivity;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.login.ModelLogin;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    public static void calApiLogin(String strUserPhone, String strPassword, final Context tContext){
        final SharedPrefManager tSharedPrefManager = new SharedPrefManager(tContext);


            Api api = ApiClient.getApiClients().create(Api.class);
            Call<ModelLogin> call = api.userLogin(strUserPhone, strPassword);
            call.enqueue(new Callback<ModelLogin>() {
                @Override
                public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                    ModelLogin modelLogin = response.body();
                    if (!modelLogin.getError()) {
//                            int intId = modelLogin.getUser().getId();
                            String strWallet = modelLogin.getUser().getWallet();
                        String strUserId = modelLogin.getUser().getUserId();
                        String strUserType = modelLogin.getUser().getUserType();
                        String strName = modelLogin.getUser().getUserName();
                        String strFlat = modelLogin.getUser().getUserFlatNo();
                        String strPhone = modelLogin.getUser().getUserPhoneNo();
                        String strEmail = modelLogin.getUser().getUserEmail();
                        String strAdhar = modelLogin.getUser().getUserAdharNo();
                        String strApartment = modelLogin.getUser().getAprtName();
                        String strArea = modelLogin.getUser().getUserArea();
                        String strCity = modelLogin.getUser().getCity();
                        String strAddress = modelLogin.getUser().getAddress();
                        String strPinCode = modelLogin.getUser().getPincode();
                        tSharedPrefManager.setUserData(strWallet, strUserId, strUserType, strName, strFlat, strPhone,
                                strEmail, strAdhar, strApartment, strArea,
                                strCity, strAddress, strPinCode);

                        if (strUserType.equals("0")){
                        Intent tIntent = new Intent(tContext, MainActivity.class);
                        tIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        tContext.startActivity(tIntent);
                        }else {
                            Intent tIntent = new Intent(tContext, DeliveryBoyActivity.class);
                            tIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            tContext.startActivity(tIntent);
                        }
                    }
                    else {
                        Toast.makeText(tContext, modelLogin.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<ModelLogin> call, Throwable t) {
                    Log.d(Constant.TAG, "Not Responding : " + t);
                }
            });



    }
}
