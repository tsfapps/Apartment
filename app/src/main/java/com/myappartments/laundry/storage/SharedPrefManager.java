package com.myappartments.laundry.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.myappartments.laundry.utils.Constant;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private Context tContext;

    private Editor tEditor;


    public SharedPrefManager(Context tContext) {
        this.tContext = tContext;
    }

    public static synchronized SharedPrefManager getInstance(Context tCtx){
        if (mInstance == null){
            mInstance = new SharedPrefManager(tCtx);

        }
        return mInstance;
    }


    public void setUserData(String strWallet, String strUserId,String strUserType, String strName, String strFlatNo, String strPhone, String strEmail,
                            String strAdhar, String strApartment, String strArea, String strCity,
                            String strAddress, String strPinCode) {
        SharedPreferences tSharedPreferences = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        tEditor = tSharedPreferences.edit();
        tEditor.putString(Constant.USER_WALLET, strWallet);
        tEditor.putString(Constant.USER_ID, strUserId);
        tEditor.putString(Constant.USER_TYPE , strUserType);
        tEditor.putString(Constant.USER_NAME, strName);
        tEditor.putString(Constant.USER_FLAT_NO, strFlatNo);
        tEditor.putString(Constant.USER_PHONE_NO, strPhone);
        tEditor.putString(Constant.USER_EMAIL, strEmail);
        tEditor.putString(Constant.USER_ADHAR_NO, strAdhar);
        tEditor.putString(Constant.USER_APARTMENT, strApartment);
        tEditor.putString(Constant.USER_AREA, strArea);
        tEditor.putString(Constant.USER_CITY, strCity);
        tEditor.putString(Constant.USER_ADDRESS, strAddress);
        tEditor.putString(Constant.USER_PIN_NO, strPinCode);
        tEditor.apply();

    }


    public void clearUserData(){
        SharedPreferences tPref = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        tEditor = tPref.edit();

        tEditor.remove(Constant.USER_WALLET);
        tEditor.remove(Constant.USER_ID);
        tEditor.remove(Constant.USER_TYPE);
        tEditor.remove(Constant.USER_NAME);
        tEditor.remove(Constant.USER_FLAT_NO);
        tEditor.remove(Constant.USER_PHONE_NO);
        tEditor.remove(Constant.USER_EMAIL);
        tEditor.remove(Constant.USER_ADHAR_NO);
        tEditor.remove(Constant.USER_APARTMENT);
        tEditor.remove(Constant.USER_AREA);
        tEditor.remove(Constant.USER_CITY);
        tEditor.remove(Constant.USER_ADDRESS);
        tEditor.remove(Constant.USER_LOCATION);
        tEditor.remove(Constant.USER_PIN_NO);
        tEditor.apply();
        tEditor.clear();
    }
    public void clearWallet(){
        SharedPreferences sharedPreferences = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        tEditor = sharedPreferences.edit();
        tEditor.remove(Constant.USER_WALLET);
//        tEditor.clear();
        tEditor.apply();
    }


    public int getId(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getInt(Constant.ID, -1);
    }
    public void setUserWallet(String strWallet){
        SharedPreferences tSharedPreferences = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        tEditor = tSharedPreferences.edit();
        tEditor.putString(Constant.USER_WALLET, strWallet);
        tEditor.apply();
    }
    public String getUserWallet(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_WALLET, Constant.EMPTY);
    }
    public String getUserId(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_ID, Constant.EMPTY);
    }
    public String getUserType(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_TYPE, Constant.EMPTY);
    }
    public String getUserName(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_NAME, Constant.EMPTY);
    }
    public String getUserFlat(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_FLAT_NO, Constant.EMPTY);
    }
    public String getUserPhone(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_PHONE_NO, Constant.EMPTY);
    }
    public String getUserEmail(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        return sp.getString(Constant.USER_EMAIL, Constant.EMPTY);
    }

    public String getUserAdhar(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_ADHAR_NO, Constant.EMPTY);
    }
    public String getUserApartment(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_APARTMENT, Constant.EMPTY);
    }
    public String getUserArea(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_AREA, Constant.EMPTY);
    }
    public String getUserCity(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_CITY, Constant.EMPTY);
    }
    public String getUserAddress(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_ADDRESS, Constant.EMPTY);
    }
    public String getUserPinNo(){
        SharedPreferences sp = tContext.getSharedPreferences(Constant.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constant.USER_PIN_NO, Constant.EMPTY);
    }

}