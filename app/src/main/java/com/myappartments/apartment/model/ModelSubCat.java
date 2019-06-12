package com.myappartments.apartment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSubCat {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("main_category_id")
    @Expose
    private String mainCategoryId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_steam_press")
    @Expose
    private String priceSteamPress;
    @SerializedName("price_washing")
    @Expose
    private String priceWashing;
    @SerializedName("price_dry_cleaning")
    @Expose
    private String priceDryCleaning;
    @SerializedName("status")
    @Expose
    private String status;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(String mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String  getPriceSteamPress() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
