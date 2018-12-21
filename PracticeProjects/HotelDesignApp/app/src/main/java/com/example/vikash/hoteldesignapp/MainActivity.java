package com.example.vikash.hoteldesignapp;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vikash.hoteldesignapp.Adapter.ImageSlideAdapter;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    ViewPager viewPager;
    ImageSlideAdapter slideShowAdapter;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =  findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hotel App");

        drawerLayout =  findViewById(R.id.drawer_layout);
        navigationView  = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setImageSlider();
    }

    private void setImageSlider() {

        CircleIndicator circleIndicator = findViewById(R.id.circle_indicator_id) ;
        viewPager = findViewById(R.id.viewpager_id);
        slideShowAdapter = new ImageSlideAdapter(this);
        viewPager.setAdapter(slideShowAdapter);

        //Circle indicator on Viewpager using CircleIndicator 3rd party library
        circleIndicator.setViewPager(viewPager);


        //Setting a timer for Image Slide
        handler = new Handler();
        Timer timer = new Timer();
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
        },5000 ,5000);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id =  item.getItemId();

        switch (id){
            case (R.id.contact_us_menu):
                Toast.makeText(this, "Inbox selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.gallery_menu):
                Toast.makeText(this, "Starred Selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.amenities_menu):
                Toast.makeText(this, "Send Mail Selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.rooms_menu):
                Toast.makeText(this, "Drafts Selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.location_menu):
                Toast.makeText(this, "All mail selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.travel_guide_menu):
                Toast.makeText(this, "Spam selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.directory_menu):
                Toast.makeText(this, "Drafts Selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.book_now_menu):
                Toast.makeText(this, "All mail selected", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.reach_us_menu):
                Toast.makeText(this, "Spam selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }



    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }



}
