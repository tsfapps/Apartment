package com.myappartments.apartment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSmartBanner {

    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("id")
    @Expose
    private String id;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
