package com.example.vikash.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*        final Handler handler  = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                L


                og.i("Handler ","I will execute every second .");
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);

        */

        CountDownTimer timer = new CountDownTimer(10000,1000)  //Code will execute every 1000 millisec till 10000sec is over
        {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("millis left",String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Log.e("We are done","No more timeouts");
            }
        };
        timer.start();




    }
}
