package com.myappartments.apartment.api;

import com.myappartments.apartment.model.MainCatModel;
import com.myappartments.apartment.model.ModelOrder;
import com.myappartments.apartment.model.ModelOrderList;
import com.myappartments.apartment.model.ModelWallet;
import com.myappartments.apartment.model.cart.ModelCartAdd;
import com.myappartments.apartment.model.ModelSmartApp;
import com.myappartments.apartment.model.ModelBanner;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSmartBanner;
import com.myappartments.apartment.model.ModelSmartSubBanner;
import com.myappartments.apartment.model.ModelSubCat;
import com.myappartments.apartment.model.ModelToken;
import com.myappartments.apartment.model.cart.ModelCartDelete;
import com.myappartments.apartment.model.cart.ModelCartView;
import com.myappartments.apartment.model.login.ModelLogin;
import com.myappartments.apartment.model.paytm.ModelChecksum;
import com.myappartments.apartment.model.paytm.ModelTransactionHistory;
import com.myappartments.apartment.model.register.ModelRegistration;
import com.myappartments.apartment.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("api/aprt_login.php")
    Call<ModelLogin> userLogin(
            @Field("user_phone_no") String user_phone_no,
            @Field("user_password") String user_password
    );
 @FormUrlEncoded
    @POST("api/aprt_registration.php")
    Call<ModelRegistration> userRegister(

            @Field("user_name") String user_name,
            @Field("user_flat_no") String user_flat_no,
            @Field("user_phone_no") String user_phone_no,
            @Field("user_email") String user_email,
            @Field("user_adhar_no") String user_adhar_no,
            @Field("user_aprt_name") String user_aprt_name,
            @Field("user_area") String user_area,
            @Field("user_city") String user_city,
            @Field("user_state") String user_state,
            @Field("user_pincode") String user_pincode,
            @Field("user_password") String user_password,
            @Field("user_id") String user_id
    );

 @POST("api/main_category.php")
    Call<List<MainCatModel>> mainCate();

 @FormUrlEncoded
 @POST("api/aprt_token.php")
    Call<ModelToken> sendToken(
            @Field("token") String token,
            @Field("user_id") String user_id
 );

 @FormUrlEncoded
 @POST("api/aprt_banner.php")
    Call<List<ModelBanner>> getBanner(
            @Field("main_category_id") String main_category_id
 );

    @FormUrlEncoded
    @POST("api/aprt_sub_category.php")
    Call<List<ModelSubCat>> getSubCat(
            @Field("main_category_id") String main_category_id,
            @Field("user_id") String user_id
    );
    @FormUrlEncoded
    @POST("api/aprt_description.php")
    Call<List<ModelDescription>> getDescription(
            @Field("main_category_id") String main_category_id
    );
    @POST("api/aprt_smartapp.php")
    Call<List<ModelSmartApp>> getSmartApp(
    );

    @POST("api/aprt_smart_banner.php")
    Call<List<ModelSmartBanner>> getSmartAppBanner(
    );

    @POST("api/aprt_smart_subbanner.php")
    Call<List<ModelSmartSubBanner>> getSmartSubBanner(
    );
    @FormUrlEncoded
    @POST("api/aprt_cart_update.php")
    Call<ModelCartAdd> cartAddPress(
            @Field("user_id") String user_id,
            @Field("prod_id") String prod_id,
            @Field("press_quantity") String press_quantity,
            @Field("press_price") String press_price,
            @Field("wash_price") String wash_price,
            @Field("dry_price") String dry_price
    );
    @FormUrlEncoded
    @POST("api/aprt_cart_update.php")
    Call<ModelCartAdd> cartAddWash(
            @Field("user_id") String user_id,
            @Field("prod_id") String prod_id,
            @Field("wash_quantity") String press_quantity,
            @Field("press_price") String press_price,
            @Field("wash_price") String wash_price,
            @Field("dry_price") String dry_price
    );
    @FormUrlEncoded
    @POST("api/aprt_cart_update.php")
    Call<ModelCartAdd> cartAddDry(
            @Field("user_id") String user_id,
            @Field("prod_id") String prod_id,
            @Field("dry_quantity") String press_quantity,
            @Field("press_price") String press_price,
            @Field("wash_price") String wash_price,
            @Field("dry_price") String dry_price
    );
    @FormUrlEncoded
    @POST("api/aprt_cart_update.php")
    Call<ModelCartAdd> cartAdd(
            @Field("user_id") String user_id,
            @Field("prod_id") String prod_id,
            @Field("press_quantity") String press_quantity,
            @Field("press_price") String press_price,
            @Field("wash_quantity") String wash_quantity,
            @Field("wash_price") String wash_price,
            @Field("dry_quantity") String dry_quantity,
            @Field("dry_price") String dry_price
    );
    @FormUrlEncoded
    @POST("api/aprt_cart_view.php")
    Call<List<ModelCartView>> cartView(
            @Field("user_id") String user_id
    );
    @FormUrlEncoded
    @POST("api/aprt_cart_delete.php")
    Call<ModelCartDelete> cartDelete(
            @Field("id") String id
    );
    @FormUrlEncoded
    @POST("api/api_wallet.php")
    Call<ModelWallet> userWallet(
            @Field("user_id") String user_id
    );
    @FormUrlEncoded
    @POST("api/aprt_order.php")
    Call<ModelOrder> cartOrder(
            @Field("user_id") String user_id,
            @Field("status") String status );

    @FormUrlEncoded
    @POST("api/aprt_order_list.php")
    Call<List<ModelOrderList>> cartOrderList(
            @Field("user_id") String user_id
    );

//PayTm Integration

   @FormUrlEncoded
   @POST("api/paytm_app/generateChecksum.php")
   Call<ModelChecksum> generateCheckSum(
           @Field(Constant.MID) String mid,
           @Field(Constant.ORDER_ID) String orderId,
           @Field(Constant.CUST_ID) String custId,
           @Field(Constant.INDUSTRY_TYPE_ID) String industryId,
           @Field(Constant.CHANNEL_ID) String channelId,
           @Field(Constant.TXN_AMOUNT) String txnAmount,
           @Field(Constant.WEBSITE) String website,
           @Field(Constant.EMAIL) String email,
           @Field(Constant.MOBILE_NO) String mobileNo );

@FormUrlEncoded
@POST("api/api_transaction_history.php")
Call<ModelTransactionHistory> paymentTransactionHistory(
        @Field("status") String status,
        @Field("checksumhash") String checksumhash,
        @Field("bankname") String bankname,
        @Field("orderid") String orderid,
        @Field("txnamount") String txnamount,
        @Field("txndate") String txndate,
        @Field("mid") String mid,
        @Field("txnid") String txnid,
        @Field("currency") String currency,
        @Field("respcode") String respcode,
        @Field("paymentmode") String paymentmode,
        @Field("banktxnid") String banktxnid,
        @Field("gatewayname") String gatewayname,
        @Field("respmsg") String respmsg,
        @Field("user_id") String userId
);

   @FormUrlEncoded
   @POST("api/api_deduction.php" )
   Call<ModelTransactionHistory> amountDeductionApi(
//           @Field("match_id") String matchId,
           @Field("txnamount") String txnAmount,
           @Field("user_id") String userId
   );
}
