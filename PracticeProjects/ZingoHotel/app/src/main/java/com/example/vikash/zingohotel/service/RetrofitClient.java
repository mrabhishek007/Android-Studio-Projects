package com.example.vikash.zingohotel.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static RetrofitClient mInstance;
    private static final String BASE_URL = "https://zingolocals.azurewebsites.net";

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    };


    // Factory Method
    public static synchronized RetrofitClient getRetrofitInstance(){
        if(mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ApiService getApiService(){
        ApiService apiService =  retrofit.create(ApiService.class);
        return apiService;
    }

}
