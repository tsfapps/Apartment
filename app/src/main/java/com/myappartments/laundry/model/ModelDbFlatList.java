package com.myappartments.laundry.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDbFlatList {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_flat_no")
    @Expose
    private String userFlatNo;
    @SerializedName("user_phone_no")
    @Expose
    private String userPhoneNo;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("user_aprt_id")
    @Expose
    private String userAprtId;
    @SerializedName("user_area")
    @Expose
    private String userArea;
    @SerializedName("user_adhar_no")
    @Expose
    private String userAdharNo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFlatNo() {
        return userFlatNo;
    }

    public void setUserFlatNo(String userFlatNo) {
        this.userFlatNo = userFlatNo;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAprtId() {
        return userAprtId;
    }

    public void setUserAprtId(String userAprtId) {
        this.userAprtId = userAprtId;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    public String getUserAdharNo() {
        return userAdharNo;
    }

    public void setUserAdharNo(String userAdharNo) {
        this.userAdharNo = userAdharNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
