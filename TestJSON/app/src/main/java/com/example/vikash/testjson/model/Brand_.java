
package com.example.vikash.testjson.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand_ {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("createddate")
    @Expose
    private String createddate;
    @SerializedName("setorder")
    @Expose
    private String setorder;
    @SerializedName("brandresult")
    @Expose
    private List<Brandresult> brandresult = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getSetorder() {
        return setorder;
    }

    public void setSetorder(String setorder) {
        this.setorder = setorder;
    }

    public List<Brandresult> getBrandresult() {
        return brandresult;
    }

    public void setBrandresult(List<Brandresult> brandresult) {
        this.brandresult = brandresult;
    }

}
