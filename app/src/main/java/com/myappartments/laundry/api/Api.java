package com.myappartments.laundry.api;

import com.myappartments.laundry.model.MainCatModel;
import com.myappartments.laundry.model.ModelAprtList;
import com.myappartments.laundry.model.ModelDbApartList;
import com.myappartments.laundry.model.ModelDbFlatList;
import com.myappartments.laundry.model.ModelDbServiceList;
import com.myappartments.laundry.model.ModelDispatched;
import com.myappartments.laundry.model.ModelOrderCancel;
import com.myappartments.laundry.model.ModelContact;
import com.myappartments.laundry.model.ModelOrder;
import com.myappartments.laundry.model.ModelOrderList;
import com.myappartments.laundry.model.ModelReceived;
import com.myappartments.laundry.model.ModelWallet;
import com.myappartments.laundry.model.cart.ModelCartAdd;
import com.myappartments.laundry.model.ModelSmartApp;
import com.myappartments.laundry.model.ModelBanner;
import com.myappartments.laundry.model.ModelDescription;
import com.myappartments.laundry.model.ModelSmartBanner;
import com.myappartments.laundry.model.ModelSmartSubBanner;
import com.myappartments.laundry.model.ModelSubCat;
import com.myappartments.laundry.model.ModelToken;
import com.myappartments.laundry.model.cart.ModelCartDelete;
import com.myappartments.laundry.model.cart.ModelCartView;
import com.myappartments.laundry.model.login.ModelLogin;
import com.myappartments.laundry.model.paytm.ModelChecksum;
import com.myappartments.laundry.model.paytm.ModelTransactionHistory;
import com.myappartments.laundry.model.register.ModelRegistration;
import com.myappartments.laundry.utils.Constant;

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


    @POST("api/api_apartments.php")
    Call<List<ModelAprtList>> apartList(
    );

    @FormUrlEncoded
    @POST("api/aprt_db_apartments.php")
    Call<List<ModelDbApartList>> apartListDb(
            @Field("db_id") String db_id
    );

    @FormUrlEncoded
    @POST("api/aprt_db_flatno.php")
    Call<List<ModelDbFlatList>> flatListDb(
            @Field("db_id") String db_id,
            @Field("aprt_id") String aprt_id
    );
    @POST("api/api_apartments.php")
    Call<List<ModelDbServiceList>> serviceListDb(
    );
 @FormUrlEncoded
    @POST("api/aprt_registration.php")
    Call<ModelRegistration> userRegister(

            @Field("user_name") String user_name,
            @Field("user_flat_no") String user_flat_no,
            @Field("user_phone_no") String user_phone_no,
            @Field("user_email") String user_email,
            @Field("user_adhar_no") String user_adhar_no,
            @Field("user_aprt_id") String user_aprt_id,
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
    @POST("api/aprt_sub_category.php")
    Call<List<ModelSubCat>> getSubCat(
            @Field("main_category_id") String main_category_id,
            @Field("user_id") String user_id
    );

 @FormUrlEncoded
 @POST("api/add_device.php")
    Call<ModelToken> sendToken(
            @Field("token_key") String token_key
 );
 @FormUrlEncoded
 @POST("api/aprt_banner.php")
    Call<List<ModelBanner>> getBanner(
            @Field("main_category_id") String main_category_id
 );
     @FormUrlEncoded
    @POST("api/aprt_description.php")
    Call<List<ModelDescription>> getDescription(
            @Field("main_category_id") String main_category_id
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
    Call<ModelOrder> myOrder(
            @Field("user_id") String user_id,
            @Field("status") String status );

    @FormUrlEncoded
    @POST("api/aprt_order_list.php")
    Call<List<ModelOrderList>> myOrderList(
            @Field("user_id") String user_id,
            @Field("index_count") String index_cont
    );
//    http://chilikaxi.com/myapartmentsadmin/api/api_order_list_status.php
    @FormUrlEncoded
    @POST("api/api_order_list_status.php")
    Call<ModelOrderList> cartOrderListStatus(
            @Field("type") String type,
            @Field("status") String status,
            @Field("id") String id
    );
   @FormUrlEncoded
   @POST("api/aprt_contact_us.php")
   Call<ModelContact> contactUs(
           @Field("user_id") String user_id,
           @Field("feed_back") String feed_back );
   @FormUrlEncoded
   @POST("api/aprt_order_cancel.php")
   Call<ModelOrderCancel> orderCancel(
           @Field("user_id") String user_id,
           @Field("type") String type,
           @Field("id") String id );
   @FormUrlEncoded
   @POST("api/aprt_order_receieved.php")
   Call<ModelReceived> orderReceived(
           @Field("user_id") String user_id,
           @Field("type") String type,
           @Field("id") String id );

   @FormUrlEncoded
   @POST("api/aprt_order_dispatched.php")
   Call<ModelDispatched> orderDispatched(
           @Field("user_id") String user_id,
           @Field("type") String type,
           @Field("id") String id );

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

    @POST("api/aprt_smartapp.php")
    Call<List<ModelSmartApp>> getSmartApp(
    );
}
