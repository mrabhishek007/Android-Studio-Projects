package com.example.android.cw45;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("----","onCreate--Activity");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("----","onStart--Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("----","onResume--Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("----","onPause--Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("----","onStop--Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("----","onDestroy--Activity");
    }
}
