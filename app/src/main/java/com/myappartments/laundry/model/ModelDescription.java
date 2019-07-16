package com.myappartments.laundry.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDescription {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("main_category_id")
    @Expose
    private String mainCategoryId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(String mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }
}
