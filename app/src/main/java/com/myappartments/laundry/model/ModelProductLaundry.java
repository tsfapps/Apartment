package com.myappartments.laundry.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProductLaundry {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("sub_cat_id")
    @Expose
    private String subCatId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private Object description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }
}
