package com.example.vikash.imageslideshow;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    Timer timer;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting toolbar
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Image Slideshow");

        circleIndicator = findViewById(R.id.circle_indicator_id) ;

        viewPager = findViewById(R.id.viewpager_id);
        final SlideShowAdapter slideShowAdapter = new SlideShowAdapter(this);
        viewPager.setAdapter(slideShowAdapter);

        //Circle indicator on Viewpager using CircleIndicator 3rd party library
        circleIndicator.setViewPager(viewPager);


        //Setting a timer for Image Slide
        handler = new Handler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                     int i = viewPager.getCurrentItem();

                     if( i==(slideShowAdapter.getCount()-1) ){   //When selected item is last image,resetting count again to 1st image
                         i =0;
                         viewPager.setCurrentItem(i);
                     }

                     else{
                         i++;
                         viewPager.setCurrentItem(i);
                     }

                    }
                });

            }
        },3000,3000);


    }
}
