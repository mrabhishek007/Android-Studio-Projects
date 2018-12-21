
package com.example.vikash.testjson.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand {

    @SerializedName("com/example/vikash/testjson/model")
    @Expose
    private List<Brand_> brand = null;

    public List<Brand_> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand_> brand) {
        this.brand = brand;
    }

}
