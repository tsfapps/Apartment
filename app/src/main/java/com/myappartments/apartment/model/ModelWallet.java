package com.myappartments.apartment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelWallet {

    @SerializedName("wallet")
    @Expose
    private String wallet;

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

}
