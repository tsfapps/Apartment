package com.myappartments.apartment.model;

public class ModelCount {

    private  int count;
    private String catId;

    public ModelCount(int count, String catId) {
        this.count = count;
        this.catId = catId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }
}
