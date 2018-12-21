package com.example.vikash.whatstheweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    TextView current_temp_txt, weather_type_txt ;
    EditText search_city_edittext;
    Button search_btn;
    public Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        search_city_edittext = findViewById(R.id.city_edit_txt);
        search_btn = findViewById(R.id.search_btn);
        current_temp_txt = findViewById(R.id.crnt_temp_txtview);
        weather_type_txt = findViewById(R.id.weather_type_txtview);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String city =  search_city_edittext.getText().toString();
               if(city.isEmpty()){
                   search_city_edittext.setError("Empty !");
               }else{
                   try {
                           String encoded_city_name =  URLEncoder.encode(city,"UTF-8");  //Checks for wrong url format

                       String url_adress="https://openweathermap.org/data/2.5/weather?q=" + encoded_city_name + "&appid=b6907d289e10d714a6e88b30761fae22" ;
                       searchWeather(url_adress);
                       try {
                           InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                           imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                       } catch (Exception e) {
                           // TODO: handle exception
                       }

                       search_city_edittext.setText("");
                       }catch (UnsupportedEncodingException e) {
                           e.printStackTrace();
                   }
               }
            }
        });

    }

    private void searchWeather(String url_adress) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(url_adress);
    }


    public class WeatherTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {

            String result="";

            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                InputStream inputStream = connection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while ( data != -1 ){

                    result += (char)data ;

                    data = reader.read();

                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                weather_type_txt.setText("Data Not found !");
                weather_type_txt.setTranslationX(-500);
                weather_type_txt.animate().translationXBy(500).setDuration(500);
                return null;
            } catch (IOException e) {
                weather_type_txt.setText("retry !");
                weather_type_txt.setTranslationX(-500);
                weather_type_txt.animate().translationXBy(500).setDuration(500);
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute(String json_result) {
            super.onPostExecute(json_result);

            try {

                JSONObject jsonObject = new JSONObject(json_result);

                JSONObject mainweather_obj = jsonObject.getJSONObject("main");
                Double temperature = mainweather_obj.getDouble("temp");
                current_temp_txt.setText(temperature+" °C");
                current_temp_txt.setTranslationX(-500);
                current_temp_txt.animate().translationXBy(500).setDuration(1900);

                JSONArray jsonweatherArray = jsonObject.getJSONArray("weather");
                for(int i=0; i<jsonweatherArray.length() ;i++){
                   JSONObject weather_obj =  jsonweatherArray.getJSONObject(i);
                   String weather_desc = weather_obj.getString("description");
                   weather_type_txt.setText(weather_desc);
                   weather_type_txt.setTranslationX(-500);
                   weather_type_txt.animate().translationXBy(500).setDuration(1500);
                }
            } catch (JSONException e) {
                weather_type_txt.setText("Data Not found !");
                weather_type_txt.setTranslationX(-500);
                weather_type_txt.animate().translationXBy(500).setDuration(500);
                e.printStackTrace();
            }

        }
    }
}
