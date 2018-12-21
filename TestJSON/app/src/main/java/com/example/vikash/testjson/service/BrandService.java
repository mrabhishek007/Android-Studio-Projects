package com.example.vikash.testjson.service;

import com.example.vikash.testjson.model.Brand;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BrandService {

    @GET(".")
   Call<Brand> getMyBrand();
}
