package com.myappartments.apartment.api;

import com.myappartments.apartment.model.MainCatModel;
import com.myappartments.apartment.model.ModelSmartApp;
import com.myappartments.apartment.model.ModelBanner;
import com.myappartments.apartment.model.ModelDescription;
import com.myappartments.apartment.model.ModelSmartBanner;
import com.myappartments.apartment.model.ModelSmartSubBanner;
import com.myappartments.apartment.model.ModelSubCat;
import com.myappartments.apartment.model.ModelToken;
import com.myappartments.apartment.model.login.ModelLogin;
import com.myappartments.apartment.model.register.ModelRegistration;

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
            @Field("main_category_id") String main_category_id
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


}
