package com.example.vikash.zingohotel.service;

import com.example.vikash.zingohotel.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
//  /api/travellers are the dynamic url for signup which will be appended to the base url
    @POST("/api/travellers")
    Call<UserModel> createUser(   // call<UserModel> tells which type of response we are getting after post request
            @Field("FirstName") String firstName,
            @Field("MiddleName") String middleName,
            @Field("LastName") String lastName,
            @Field("Gender") String gender,
            @Field("DOB") String dob,
            @Field("Email") String email,
            @Field("PhoneNumber") String phoneNumber,
            @Field("PlaceName") String placeName   // here "PlaceName" is the exact key which we are sending through POST request
    );


}



