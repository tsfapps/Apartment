package com.myappartments.laundry.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myappartments.laundry.R;
import com.myappartments.laundry.api.Api;
import com.myappartments.laundry.api.ApiClient;
import com.myappartments.laundry.model.paytm.ModelChecksum;
import com.myappartments.laundry.model.paytm.ModelTransactionHistory;
import com.myappartments.laundry.presenter.OrderPresenter;
import com.myappartments.laundry.storage.SharedPrefManager;
import com.myappartments.laundry.utils.Constant;
import com.myappartments.laundry.utils.CustomLog;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    private SharedPrefManager tSharedPrefManager;
    private Context tContext;
    private PaytmPGService mPaymentService;
    private PaytmOrder mOrder;
    private String MID = "rsIFPL71143711550832";//rsIFPL71143711550832//LVNRji48342448716443
    private String mCheckSum = null;
    private String mCallBackUrl = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=";
    //private String mCallBackUrl = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";
    private String mOrderId = null;
    private String mCustId = null;
    private String mAmount = null;
    private String mIndustryId = null;
    private String mChannelId = null;
    private String mWebsite = null;
    private String mEmail = null;
    private String mMobile = null;
//    private List<User> mList;
//    private TextView wallet_amount;
    private String mMatchId;
    private int mEntryFee;

    @BindView(R.id.wallet_amount)
    protected TextView wallet_amount;
    @BindView(R.id.billing_amount)
    protected TextView tvBillingAmount;
    @BindView(R.id.et_billing_amount)
    protected EditText etBillingAmount;

    @BindView(R.id.btn_payment)
    protected Button btnPayment;

    @BindView(R.id.iv_back)
    protected ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
//        callApiWallet();
        init();
    }

//        private void callApiWallet(){
//        tContext = getApplicationContext();
//        tSharedPrefManager = new SharedPrefManager(tContext);
//        String strUserId = tSharedPrefManager.getUserId();
//        WalletPresenter.callApiWalletPayment(strUserId, PaymentActivity.this); }

//        public void onResponseApiWallet(Response<ModelWallet> response){
//        ModelWallet tModel = response.body();
//        String strTotalAmount = getIntent().getStringExtra("TOTAL_AMOUNT");
//        String strWallet = tModel.getWallet();
//        wallet_amount.setText(strWallet);
//        tvBillingAmount.setText(strTotalAmount);
//        mEntryFee = Integer.parseInt(strTotalAmount);
//
////        mAmount = String.valueOf(mEntryFee);
////        etBillingAmount.setVisibility(View.GONE);
//        btnPayment.setText("Pay"); }

//        public void onFailureApiWallet(Call<ModelWallet> call){
//        CustomLog.d(Constant.TAG, "Wallet Not Responding: "+call); }

    private void init() {
        tContext = getApplicationContext();
        tSharedPrefManager = new SharedPrefManager(tContext);
        wallet_amount.setText(tSharedPrefManager.getUserWallet());
        checkReadSmsPermission();
            mCustId =tSharedPrefManager.getUserId();
            mEmail = tSharedPrefManager.getUserEmail();
            mMobile = tSharedPrefManager.getUserPhone();
            String mTemp= mCustId.substring(0, Math.min(mCustId.length(), 6));
            mOrderId = mTemp + getOrderId();
            mIndustryId = "Retail";
            mChannelId = "WAP";
            mWebsite = "DEFAULT";
            mCallBackUrl = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID="+mOrderId;
            updateWalletAmount();
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAmount != null) {
                    if (btnPayment.getText().toString().equalsIgnoreCase("Add")) {
                        generateChecksumApi();
                    } else {
                        callAmountDeductionApi();
                    }
                }
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void updateWalletAmount() {
        String strTotalAmount = getIntent().getStringExtra("TOTAL_AMOUNT");
        mEntryFee = Integer.parseInt(strTotalAmount);
        int wallet = Integer.parseInt(tSharedPrefManager.getUserWallet());
        wallet_amount.setText(String.valueOf(wallet));
        tvBillingAmount.setText(String.valueOf(mEntryFee));
        if (mEntryFee > wallet){
            etBillingAmount.setVisibility(View.VISIBLE);
            mAmount = String.valueOf(mEntryFee - wallet);
            etBillingAmount.setText("₹ "+ mAmount);
            btnPayment.setText("Add");
        } else {
            mAmount = String.valueOf(mEntryFee);
            etBillingAmount.setVisibility(View.GONE);
            btnPayment.setText("Pay");
        }
    }

    private void checkReadSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
    }
    private void generateChecksumApi() {
        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelChecksum> call = api.generateCheckSum(MID, mOrderId,mCustId,mIndustryId,mChannelId,
                mAmount,mWebsite,mEmail,mMobile);
        call.enqueue(new Callback<ModelChecksum>() {
            @Override
            public void onResponse(Call<ModelChecksum> call, Response<ModelChecksum> response) {
                assert response.body() != null;
                mCheckSum = response.body().getCHECKSUMHASH();
                initPaymentMethod();
            }
            @Override
            public void onFailure(Call<ModelChecksum> call, Throwable t) {
                CustomLog.d(Constant.TAG,"generate checksum onFailure :"+ call.toString());
            }
        });
    }

    private void initPaymentMethod() {
        mPaymentService = PaytmPGService.getProductionService();// TODO for production environment: pro key : rsIFPL71143711550832
        HashMap<String, String> paramMap = new HashMap<String,String>();
        paramMap.put(Constant.MID, MID);
        paramMap.put(Constant.ORDER_ID , mOrderId);
        paramMap.put( Constant.CUST_ID , mCustId);
        paramMap.put( Constant.MOBILE_NO , mMobile);
        paramMap.put( Constant.EMAIL ,mEmail);
        paramMap.put(Constant.CHANNEL_ID , mChannelId);
        paramMap.put(Constant.TXN_AMOUNT , mAmount);
        paramMap.put( Constant.WEBSITE , mWebsite);
        paramMap.put(Constant.INDUSTRY_TYPE_ID ,mIndustryId);
        paramMap.put( Constant.CALLBACK_URL, mCallBackUrl);
        paramMap.put( Constant.CHECKSUMHASH , mCheckSum);
        mOrder = new PaytmOrder(paramMap);
        startPaymentService();
    }
    private void startPaymentService() {
        mPaymentService.initialize(mOrder, null);//TODO pass certificate if have else pass null

        mPaymentService.startPaymentTransaction(this, true, true, new PaytmPaymentTransactionCallback() {
            /*Call Backs*/
            public void someUIErrorOccurred(String inErrorMessage) {
                /*Display the error message as below */
                Toast.makeText(getApplicationContext(), "UI Error " + inErrorMessage , Toast.LENGTH_LONG).show();
            }
            public void onTransactionResponse(Bundle inResponse) {
                /*Display the message as below */
                if (inResponse.get(Constant.STATUS).equals(Constant.TXN_SUCCESS) && inResponse.get(Constant.RESPMSG).equals(Constant.Txn_Success)) {
                    callTansactionHistroyApi(inResponse);
                } else {
                    Toast.makeText(getApplicationContext(), "Payment Transaction Error occur", Toast.LENGTH_LONG).show();
                }
            }
            public void networkNotAvailable() {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();
            }
            public void clientAuthenticationFailed(String inErrorMessage) {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Authentication failed: Server error" + inErrorMessage.toString(), Toast.LENGTH_LONG).show();

            }
            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Unable to load webpage " + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
            }
            public void onBackPressedCancelTransaction() {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Transaction cancelled back press" , Toast.LENGTH_LONG).show();
            }
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                /*Display the message as below */
                Toast.makeText(getApplicationContext(), "Payment Transaction cancel " + inResponse.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private String getOrderId()
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    private void callTansactionHistroyApi(Bundle inResponse) {
        String status = inResponse.getString("STATUS");
        String checksum = inResponse.getString("CHECKSUMHASH");
        String bankName = inResponse.getString("BANKNAME");
        String orderId = inResponse.getString("ORDERID");
        String txnAmount = inResponse.getString("TXNAMOUNT");
        String txnDate = inResponse.getString("TXNDATE");
        String mid = inResponse.getString("MID");
        String txnId = inResponse.getString("TXNID");
        String respcode = inResponse.getString("RESPCODE");
        String paymentMode = inResponse.getString("PAYMENTMODE");
        String banktxnid = inResponse.getString("BANKTXNID");
        String currency = inResponse.getString("CURRENCY");
        String gatewayname = inResponse.getString("GATEWAYNAME");
        String respmsg = inResponse.getString("RESPMSG");

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelTransactionHistory> call = api.paymentTransactionHistory(status, checksum, bankName, orderId, txnAmount, txnDate, mid,
                txnId, respcode, paymentMode, banktxnid, currency, gatewayname, respmsg, mCustId);
        call.enqueue(new Callback<ModelTransactionHistory>() {
            @Override
            public void onResponse(Call<ModelTransactionHistory> call, Response<ModelTransactionHistory> response) {
                assert response.body() != null;
                if (!response.body().getError()){
                    Toast.makeText(getApplicationContext(), "Amount added in wallet successfully", Toast.LENGTH_LONG).show();
                    tSharedPrefManager.clearWallet();
                    String strUpdatedWallet = String.valueOf(response.body().getWallet());
                    tSharedPrefManager.setUserWallet(strUpdatedWallet);

                    updateWalletAmount();
                }
            }

            @Override
            public void onFailure(Call<ModelTransactionHistory> call, Throwable t) {

            }
        });
    }

    private void callAmountDeductionApi() {

        Api api = ApiClient.getApiClients().create(Api.class);
        Call<ModelTransactionHistory> call = api.amountDeductionApi(mAmount, mCustId);
        call.enqueue(new Callback<ModelTransactionHistory>() {
            @Override
            public void onResponse(Call<ModelTransactionHistory> call, Response<ModelTransactionHistory> response) {
                assert response.body() != null;
                if (!response.body().getError()){
                    Toast.makeText(getApplicationContext(), "Order is placed Successfully...", Toast.LENGTH_LONG).show();
                    tSharedPrefManager.clearWallet();
                    String strUpdatedWallet = String.valueOf(response.body().getWallet());
                    tSharedPrefManager.setUserWallet(strUpdatedWallet);
                    String strUserId;
                    String strDbUserId = getIntent().getStringExtra(Constant.DB_USER_ID);

                    String strUserType = tSharedPrefManager.getUserType();
                    if (strUserType.equalsIgnoreCase("0")){
                        strUserId = tSharedPrefManager.getUserId();
                    }else {
                        strUserId = strDbUserId;
                    }
                    OrderPresenter.callApiOrder(strUserId, "success", tContext);
                    Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                    intent.putExtra(Constant.DB_USER_ID, strUserId);
                    intent.putExtra(Constant.DB_SERVICE_TYPE, "New Order");
                    startActivity(intent);
                    finishAffinity();
                }
            }
            @Override
            public void onFailure(Call<ModelTransactionHistory> call, Throwable t) {
            }
        });
    }

}
