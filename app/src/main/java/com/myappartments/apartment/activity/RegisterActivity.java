package com.myappartments.apartment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.myappartments.apartment.R;
import com.myappartments.apartment.api.Api;
import com.myappartments.apartment.api.ApiClient;
import com.myappartments.apartment.model.register.ModelRegistration;
import com.myappartments.apartment.utils.Constant;
import com.myappartments.apartment.utils.CustomLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


   // private InitActivity tInitActivity;
    @BindView(R.id.et_reg_Name)
    protected EditText etRegName;
    @BindView(R.id.et_reg_flatNo)
    protected EditText etRegFlatNo;
    @BindView(R.id.et_reg_phoneNo)
    protected EditText etRegPhoneNo;
    @BindView(R.id.et_reg_email)
    protected EditText etRegEmail;
    @BindView(R.id.et_reg_adharNo)
    protected EditText etRegAdharNo;
    @BindView(R.id.et_apartmentName)
    protected EditText etApartName;
    @BindView(R.id.et_areaName)
    protected EditText etAreaName;
    @BindView(R.id.et_reg_state)
    protected TextView tvRegState;
    @BindView(R.id.et_reg_city)
    protected TextView tvRegCity;
    @BindView(R.id.et_reg_pinCode)
    protected Spinner etRegPinCode;
    @BindView(R.id.et_reg_pass)
    protected EditText etRegPass;
    @BindView(R.id.et_reg_pass_re)
    protected EditText etRegPassRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
       // tInitActivity = new InitActivity(this);
    }

    @OnClick(R.id.tv_reg_login)
    public void onNewRegister(){

        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.btn_reg_submit)
    public void onRegSubmitClick(){
        initApi();
    }
    private void initApi(){

        String strName = etRegName.getText().toString().trim();
        String strFlatNo = etRegFlatNo.getText().toString().trim();
        String strPhoneNo = etRegPhoneNo.getText().toString().trim();
        String strEmail = etRegEmail.getText().toString().trim();
        String strAdhar = etRegAdharNo.getText().toString().trim();
        String strAprtName = etApartName.getText().toString().trim();
        String strAreaName = etAreaName.getText().toString().trim();


        String strState = tvRegState.getText().toString().trim();
        String strCity = tvRegCity.getText().toString().trim();
        String strPincode = etRegPinCode.getSelectedItem().toString().trim();
        String strPass = etRegPass.getText().toString().trim();
        String strPassRe = etRegPassRe.getText().toString().trim();
        String strUserId;



        if (strName.equals("")){
            etRegName.setError("Enter the name");
        }
        else if  (strFlatNo.equals("")){
            etRegFlatNo.setError("Enter the flat number");
        }
        else if (strPhoneNo.length()<10){
            etRegPhoneNo.setError("Enter correct phone number");
        }
        else if (strEmail.equals("")){
            etRegEmail.setError("Enter the email id");
        }

        else if (strAprtName.equals("Select Apartment")){
           Toast.makeText(getApplicationContext(), "Select your Apartment", Toast.LENGTH_LONG).show();
        }
        else if (strAreaName.equals("Select Area")){
            Toast.makeText(getApplicationContext(), "Select your Area", Toast.LENGTH_LONG).show();
        }

       else if (strPincode.equals("--select pin code--")){
            Toast.makeText(getApplicationContext(), "Select your Area Pin Code", Toast.LENGTH_LONG).show();

        }
        else if (strPass.equals("")){
            etRegName.setError("Enter the name");
        }
        else if (!strPassRe.equals(strPass)){
            etRegPassRe.setError("Password does not match");
        }

        else if (strEmail.length()>3 && !strAprtName.contains("Select") && !strAreaName.contains("Select")) {
           // String str = strAprtName.substring(0, 3);
//            strUserId = strFlatNo+str+strPincode;
            CustomLog.d(Constant.TAG, "User Id : "+strPhoneNo);

             Api api = ApiClient.getApiClients().create(Api.class);
             Call<ModelRegistration> call = api.userRegister(strName, strFlatNo, strPhoneNo, strEmail, strAdhar,
                     strAprtName, strAreaName, strCity, strState, strPincode, strPass, strPhoneNo);
             call.enqueue(new Callback<ModelRegistration>() {
                 @Override
                 public void onResponse(Call<ModelRegistration> call, Response<ModelRegistration> response) {
                     ModelRegistration tModel = response.body();
                     if (!tModel.getError()) {
                         Toast.makeText(getApplicationContext(), tModel.getMessage(), Toast.LENGTH_LONG).show();
                        // tInitActivity.initLoginActivity();
                         startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                     }
                     else {
                         Toast.makeText(getApplicationContext(), tModel.getMessage(), Toast.LENGTH_LONG).show();
                     }
                 }

                 @Override
                 public void onFailure(Call<ModelRegistration> call, Throwable t) {

                     CustomLog.e(Constant.TAG, "Not Responding : "+t);
                     Toast.makeText(getApplicationContext(), "Registration Not Responding : "+t,Toast.LENGTH_LONG).show();

                 }
             });
        }
        else{
            Toast.makeText(RegisterActivity.this, "Enter correct email address ",Toast.LENGTH_SHORT).show();
        }

    }



}
