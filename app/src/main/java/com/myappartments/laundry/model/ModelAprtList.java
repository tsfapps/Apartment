package com.myappartments.laundry.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelAprtList {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("aprt_name")
    @Expose
    private String aprtName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("total_flats")
    @Expose
    private String totalFlats;
    @SerializedName("db_id")
    @Expose
    private String dbId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAprtName() {
        return aprtName;
    }

    public void setAprtName(String aprtName) {
        this.aprtName = aprtName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTotalFlats() {
        return totalFlats;
    }

    public void setTotalFlats(String totalFlats) {
        this.totalFlats = totalFlats;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }
}
