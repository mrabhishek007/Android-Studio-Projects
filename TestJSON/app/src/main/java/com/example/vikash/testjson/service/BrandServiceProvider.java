package com.example.vikash.testjson.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.vikash.testjson.MainActivity;
import com.example.vikash.testjson.event.Mediator;
import com.example.vikash.testjson.model.Brand;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BrandServiceProvider {

    private static final String TAG = BrandServiceProvider.class.getSimpleName() ;
    private Context context;
    Retrofit retrofit;

    public BrandServiceProvider(Context context) {
        this.context = context;
    }

   private Retrofit getRetrofit(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.vehiclebuzzzz.com/index.php/JsonController/brand/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
   }

   public void getBrandInfo(){
     BrandService brandService = getRetrofit().create(BrandService.class) ;
      Call<Brand> brand =brandService.getMyBrand();

      brand.enqueue(new Callback<Brand>() {
          @Override
          public void onResponse(Call<Brand> call, Response<Brand> response) {

           Brand brandobj =   response.body() ;
              EventBus.getDefault().post(new Mediator(brandobj));
          }

          @Override
          public void onFailure(Call<Brand> call, Throwable t) {

          }
      });

   }


}
