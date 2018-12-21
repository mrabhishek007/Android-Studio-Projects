
package com.example.vikash.cw66;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView adview = findViewById(R.id.adView1);



        AdRequest.Builder arb =  new AdRequest.Builder();
        AdRequest adRequest =  arb.setRequestAgent("android:studio:ad_template").build();
        adview.loadAd(adRequest);


       // AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android:studio:ad_template").build();


    }
}
