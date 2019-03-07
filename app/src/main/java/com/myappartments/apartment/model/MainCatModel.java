package com.myappartments.apartment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class MainCatModel {

    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("id")
    @Expose
    private Long id;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
