package com.example.vikash.darkskyclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vikash.darkskyclient.events.ErrorEvent;
import com.example.vikash.darkskyclient.events.WeatherEvent;
import com.example.vikash.darkskyclient.service.WeatherServiceProvider;
import com.example.vikash.darkskyclient.util.WeatherIconUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Currently;

/*
*  STEP 1 - Open Dark Sky Api website register and clic on link to get json data
*  STEP 2 - http://www.jsonschema2pojo.org/ .Mention - Package name, A suitable Class Name (User Defined like Weather.java), Select Source Type : JSON, Annotation Style : gson and download the zip file
*  STEP 3 - Create a seperate package name like 'model' and paste the downloaded java files (Only useful) into it...
*  STEP 4 - Search 'gson gradle' on google and copy the library dependency 'implementation 'com.google.code.gson:gson:2.8.5''
*  STEP 5 - Open 'Retrofit' on google and copy the library dependency 'compile 'com.squareup.retrofit2:retrofit:2.4.0''
* */

public class MainActivity extends AppCompatActivity {

    //Using Butterknife instead of traditional findViewById()

    @BindView(R.id.current_temp_txt_id) public TextView current_temp ;

    @BindView(R.id.summary_txt_id) public TextView weather_summary ;

    @BindView(R.id.weather_icon_img_id) public ImageView weather_icon ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestCurrentWeather(37.5267,-122.4238);
        ButterKnife.bind(this);

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {
      Currently currently =   weatherEvent.getWeather().getCurrently();

      current_temp.setText(String.valueOf(Math.round(currently.getTemperature())));

      weather_summary.setText(currently.getSummary());

      weather_icon.setImageResource(WeatherIconUtil.ICONS.get(currently.getIcon()));


    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
       String errorMsg =  errorEvent.getErrMsg();

        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }


    private void requestCurrentWeather(double latitude, double longitude) {

        WeatherServiceProvider serviceProvider = new WeatherServiceProvider();
        serviceProvider.getWeatherInfo(latitude,longitude);
    }


}
