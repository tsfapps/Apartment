package com.myappartments.apartment.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.myappartments.apartment.utils.Constants;

public class SharedPrefApart {
    private static SharedPrefApart mInstance;
    private Context tContext;

    private Editor tEditor;


    public SharedPrefApart(Context tContext) {
        this.tContext = tContext;
    }

    public static synchronized SharedPrefApart getInstance(Context tCtx){
        if (mInstance == null){
            mInstance = new SharedPrefApart(tCtx);

        }
        return mInstance;
    }


    public void setBannerId(String id){

    }
    public void setUserData(int intId, String strUserId, String strName, String strFlatNo, String strPhone, String strEmail,
                                  String strAdhar, String strApartment, String strArea, String strCity,
                                  String strState, String strPinNo) {
        SharedPreferences tSharedPreferences = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        tEditor = tSharedPreferences.edit();
        tEditor.putInt(Constants.ID, intId);
        tEditor.putString(Constants.USER_ID, strUserId);
        tEditor.putString(Constants.USER_NAME, strName);
        tEditor.putString(Constants.USER_FLAT_NO, strFlatNo);
        tEditor.putString(Constants.USER_PHONE_NO, strPhone);
        tEditor.putString(Constants.USER_EMAIL, strEmail);
        tEditor.putString(Constants.USER_ADHAR_NO, strAdhar);
        tEditor.putString(Constants.USER_APARTMENT, strApartment);
        tEditor.putString(Constants.USER_AREA, strArea);
        tEditor.putString(Constants.USER_CITY, strCity);
        tEditor.putString(Constants.USER_STATE, strState);
        tEditor.putString(Constants.USER_PIN_NO, strPinNo);
        tEditor.apply();

    }


    public void clearUserData(){
        SharedPreferences tPref = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        tEditor = tPref.edit();
        tEditor.remove(Constants.ID);
        tEditor.remove(Constants.USER_ID);
        tEditor.remove(Constants.USER_NAME);
        tEditor.remove(Constants.USER_FLAT_NO);
        tEditor.remove(Constants.USER_PHONE_NO);
        tEditor.remove(Constants.USER_EMAIL);
        tEditor.remove(Constants.USER_ADHAR_NO);
        tEditor.remove(Constants.USER_APARTMENT);
        tEditor.remove(Constants.USER_AREA);
        tEditor.remove(Constants.USER_CITY);
        tEditor.remove(Constants.USER_STATE);
        tEditor.remove(Constants.USER_PIN_NO);
        tEditor.apply();
        tEditor.clear();
    }

    public int getId(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getInt(Constants.ID, -1);
    }
    public String getUserId(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_ID, Constants.EMPTY);
    }
    public String getUserName(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_NAME, Constants.EMPTY);
    }
    public String getUserFlat(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_FLAT_NO, Constants.EMPTY);
    }
    public String getUserPhone(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_PHONE_NO, Constants.EMPTY);
    }
    public String getUserEmail(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        return sp.getString(Constants.USER_EMAIL, Constants.EMPTY);
    }

    public String getUserAdhar(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_ADHAR_NO, Constants.EMPTY);
    }
    public String getUserApartment(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_APARTMENT, Constants.EMPTY);
    }
    public String getUserArea(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_AREA, Constants.EMPTY);
    }
    public String getUserCity(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_CITY, Constants.EMPTY);
    }
    public String getUserState(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_STATE, Constants.EMPTY);
    }
    public String getUserPinNo(){
        SharedPreferences sp = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
       return sp.getString(Constants.USER_PIN_NO, Constants.EMPTY);
    }

    public void clear(){
        SharedPreferences sharedPreferences = tContext.getSharedPreferences(Constants.TSF_SHARED_PREFENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}