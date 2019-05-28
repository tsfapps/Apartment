package com.myappartments.apartment.model.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelCartView {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("prod_id")
    @Expose
    private String prodId;
    @SerializedName("press_quantity")
    @Expose
    private String pressQuantity;
    @SerializedName("press_price")
    @Expose
    private String pressPrice;
    @SerializedName("wash_quantity")
    @Expose
    private String washQuantity;
    @SerializedName("wash_price")
    @Expose
    private String washPrice;
    @SerializedName("dry_quantity")
    @Expose
    private String dryQuantity;
    @SerializedName("dry_price")
    @Expose
    private String dryPrice;
    @SerializedName("total_quantity")
    @Expose
    private String totalQuantity;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getPressQuantity() {
        return pressQuantity;
    }

    public void setPressQuantity(String pressQuantity) {
        this.pressQuantity = pressQuantity;
    }

    public String getPressPrice() {
        return pressPrice;
    }

    public void setPressPrice(String pressPrice) {
        this.pressPrice = pressPrice;
    }

    public String getWashQuantity() {
        return washQuantity;
    }

    public void setWashQuantity(String washQuantity) {
        this.washQuantity = washQuantity;
    }

    public String getWashPrice() {
        return washPrice;
    }

    public void setWashPrice(String washPrice) {
        this.washPrice = washPrice;
    }

    public String getDryQuantity() {
        return dryQuantity;
    }

    public void setDryQuantity(String dryQuantity) {
        this.dryQuantity = dryQuantity;
    }

    public String getDryPrice() {
        return dryPrice;
    }

    public void setDryPrice(String dryPrice) {
        this.dryPrice = dryPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
