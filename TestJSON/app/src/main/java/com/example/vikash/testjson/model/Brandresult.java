
package com.example.vikash.testjson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brandresult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("com/example/vikash/testjson/model")
    @Expose
    private String brand;
    @SerializedName("brand_logo")
    @Expose
    private String brandLogo;
    @SerializedName("brand_type")
    @Expose
    private String brandType;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
