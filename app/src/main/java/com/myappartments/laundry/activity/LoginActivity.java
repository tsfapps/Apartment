package com.myappartments.laundry.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.myappartments.laundry.R;
import com.myappartments.laundry.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

//    private SharedPrefManager tSharedPrefManager;
    @BindView(R.id.et_login_phone_no)
    protected EditText etLoginPhoneNo;
    @BindView(R.id.et_login_pass)
    protected EditText etLoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
      //  tInitActivity = new InitActivity(this);
//        tSharedPrefManager = new SharedPrefManager(this);
    }
    @OnClick(R.id.btn_login_submit)
    public void onSubmitLogin(View view){
        initApi();
    }

    @OnClick(R.id.tv_login_newRegister)
    public void onNewRegister(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void initApi(){
        String strPhone = etLoginPhoneNo.getText().toString().trim();
        String strPass = etLoginPass.getText().toString().trim();
        if (strPhone.equals("") || strPhone.length()<10){
            etLoginPhoneNo.setError("Enter correct phone number");
        }
        else
        if (strPass.equals("")){
            etLoginPass.setError("Enter the password");
        }
        else {
            LoginPresenter.calApiLogin(strPhone, strPass, getApplicationContext());

//            try {
//                Api api = ApiClient.getApiClients().create(Api.class);
//                Call<ModelLogin> call = api.userLogin(strPhone, strPass);
//                call.enqueue(new Callback<ModelLogin>() {
//                    @Override
//                    public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
//                        ModelLogin modelLogin = response.body();
//                        if (!modelLogin.getError()) {
////                            int intId = modelLogin.getUser().getId();
////                            String strWallet = modelLogin.getUser().getWallet();
//                            String strUserId = modelLogin.getUser().getUserId();
//                            String strName = modelLogin.getUser().getUserName();
//                            String strFlat = modelLogin.getUser().getUserFlatNo();
//                            String strPhone = modelLogin.getUser().getUserPhoneNo();
//                            String strEmail = modelLogin.getUser().getUserEmail();
//                            String strAdhar = modelLogin.getUser().getUserAdharNo();
//                            String strApartment = modelLogin.getUser().getUserAprtName();
//                            String strArea = modelLogin.getUser().getUserArea();
//                            String strCity = modelLogin.getUser().getUserCity();
//                            String strState = modelLogin.getUser().getUserAddress();
//                            String strPinCode = modelLogin.getUser().getUserPincode();
//                            tSharedPrefManager.setUserData(strUserId, strName, strFlat, strPhone,
//                                    strEmail, strAdhar, strApartment, strArea,
//                                    strCity, strState, strPinCode);
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(), modelLogin.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ModelLogin> call, Throwable t) {
//                        Log.d(Constant.TAG, "Not Responding : " + t);
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }
}
