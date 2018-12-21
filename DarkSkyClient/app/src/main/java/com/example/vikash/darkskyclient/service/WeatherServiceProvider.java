package com.example.vikash.darkskyclient.service;

import android.util.Log;

import com.example.vikash.darkskyclient.MainActivity;
import com.example.vikash.darkskyclient.events.ErrorEvent;
import com.example.vikash.darkskyclient.events.WeatherEvent;

import org.greenrobot.eventbus.EventBus;

import model.Currently;
import model.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceProvider {

    private static final String BASE_URL = "https://api.darksky.net/forecast/12fa791b5c7308f30b806215172967f0/" ;
    private static final String TAG = WeatherServiceProvider.class.getSimpleName();
    private Retrofit retrofit;


    // Creating a Retrofit
    private Retrofit getRetrofit(){

        if(retrofit==null){
               retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         }
         return retrofit;
    }


    public void getWeatherInfo(double latitude, double longitude){

        WeatherService weatherService = getRetrofit().create(WeatherService.class);
        Call<Weather> weatherData = weatherService.getWeather(latitude,longitude);

        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();

                if(weather!=null) {
                    Currently currently = weather.getCurrently();
                    Log.e(TAG,currently.getTemperature()+"") ;

                    EventBus.getDefault().post(new WeatherEvent(weather));
                }else{
                     Log.e(TAG,"No response : Check Secret key");
                    EventBus.getDefault().post(new ErrorEvent("No weather data available"));
                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG,"onFailure: Unable to get weather data");

                EventBus.getDefault().post(new ErrorEvent("Unable to get weather data"));
            }
        });


    }


}
