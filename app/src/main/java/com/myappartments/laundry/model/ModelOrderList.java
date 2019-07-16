package com.myappartments.laundry.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelOrderList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("prod_id")
    @Expose
    private String prodId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("image")
    @Expose
    private String image;
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
    @SerializedName("price_steam_press")
    @Expose
    private String priceSteamPress;
    @SerializedName("price_washing")
    @Expose
    private String priceWashing;
    @SerializedName("price_dry_cleaning")
    @Expose
    private String priceDryCleaning;
    @SerializedName("press_status")
    @Expose
    private String pressStatus;
    @SerializedName("wash_status")
    @Expose
    private String washStatus;
    @SerializedName("dry_status")
    @Expose
    private String dryStatus;
    @SerializedName("placed_datetime")
    @Expose
    private String placedDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getPriceSteamPress() {
        return priceSteamPress;
    }

    public void setPriceSteamPress(String priceSteamPress) {
        this.priceSteamPress = priceSteamPress;
    }

    public String getPriceWashing() {
        return priceWashing;
    }

    public void setPriceWashing(String priceWashing) {
        this.priceWashing = priceWashing;
    }

    public String getPriceDryCleaning() {
        return priceDryCleaning;
    }

    public void setPriceDryCleaning(String priceDryCleaning) {
        this.priceDryCleaning = priceDryCleaning;
    }

    public String getPressStatus() {
        return pressStatus;
    }

    public void setPressStatus(String pressStatus) {
        this.pressStatus = pressStatus;
    }

    public String getWashStatus() {
        return washStatus;
    }

    public void setWashStatus(String washStatus) {
        this.washStatus = washStatus;
    }

    public String getDryStatus() {
        return dryStatus;
    }

    public void setDryStatus(String dryStatus) {
        this.dryStatus = dryStatus;
    }

    public String getPlacedDatetime() {
        return placedDatetime;
    }

    public void setPlacedDatetime(String placedDatetime) {
        this.placedDatetime = placedDatetime;
    }

}
