package com.myappartments.apartment.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.myappartments.apartment.activity.LoginActivity;
import com.myappartments.apartment.activity.MainActivity;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.login.ModelLogin;
import com.myappartments.apartment.storage.SharedPrefManager;
import com.myappartments.apartment.utils.Constant;

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
                        String strName = modelLogin.getUser().getUserName();
                        String strFlat = modelLogin.getUser().getUserFlatNo();
                        String strPhone = modelLogin.getUser().getUserPhoneNo();
                        String strEmail = modelLogin.getUser().getUserEmail();
                        String strAdhar = modelLogin.getUser().getUserAdharNo();
                        String strApartment = modelLogin.getUser().getUserAprtName();
                        String strArea = modelLogin.getUser().getUserArea();
                        String strCity = modelLogin.getUser().getUserCity();
                        String strState = modelLogin.getUser().getUserState();
                        String strPinCode = modelLogin.getUser().getUserPincode();
                        tSharedPrefManager.setUserData(strWallet, strUserId, strName, strFlat, strPhone,
                                strEmail, strAdhar, strApartment, strArea,
                                strCity, strState, strPinCode);

                        Intent tIntent = new Intent(tContext, MainActivity.class);
                        tIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        tContext.startActivity(tIntent);
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
