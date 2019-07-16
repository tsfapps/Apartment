package com.myappartments.laundry.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.myappartments.laundry.R;
import com.myappartments.laundry.adapter.AdapterApartList;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.ModelAprtList;
import com.myappartments.laundry.model.register.ModelRegistration;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private List<ModelAprtList> tModels;
    private Context tContext;
    private String strAprtId;
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

    @BindView(R.id.spn_apartmentName)
    protected Spinner spn_apartmentName;

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
        tContext = getApplicationContext();
        callApiAprtList();
//        String strAprtName = spn_apartmentName.getSelectedItem().toString();
//        CustomLog.d(Constant.TAG, "AprtNamr : "+strAprtName);


        // tInitActivity = new InitActivity(this);
        spn_apartmentName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAprtId = tModels.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
//        String strAprtName = etApartName.getText().toString().trim();



       // String strAprtName = String.valueOf(spn_apartmentName.getSelectedItemPosition());
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

//        else if (strAprtName.equals("Select Apartment")){
//           Toast.makeText(getApplicationContext(), "Select your Apartment", Toast.LENGTH_LONG).show();
//        }
//        else if (strAreaName.equals("Select Area")){
//            Toast.makeText(getApplicationContext(), "Select your Area", Toast.LENGTH_LONG).show();
//        }

       else if (strPincode.equals("--select pin code--")){
            Toast.makeText(getApplicationContext(), "Select your Area Pin Code", Toast.LENGTH_LONG).show();

        }
        else if (strPass.equals("")){
            etRegName.setError("Enter the name");
        }
        else if (!strPassRe.equals(strPass)){
            etRegPassRe.setError("Password does not match");
        }

        else if (strEmail.length()>3) {
           // String str = strAprtName.substring(0, 3);
//            strUserId = strFlatNo+str+strPincode;

             Api api = ApiClient.getApiClients().create(Api.class);
             Call<ModelRegistration> call = api.userRegister(strName, strFlatNo, strPhoneNo, strEmail, strAdhar,
                     strAprtId, strAreaName, strCity, strState, strPincode, strPass, strPhoneNo);
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


    private void callApiAprtList(){
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<List<ModelAprtList>> call = api.apartList();
        call.enqueue(new Callback<List<ModelAprtList>>() {
            @Override
            public void onResponse(Call<List<ModelAprtList>> call, Response<List<ModelAprtList>> response) {
                tModels = response.body();
                AdapterApartList tAdapter = new AdapterApartList(tContext, tModels);
                spn_apartmentName.setAdapter(tAdapter);
            }

            @Override
            public void onFailure(Call<List<ModelAprtList>> call, Throwable t) {

            }
        });
    }

}
