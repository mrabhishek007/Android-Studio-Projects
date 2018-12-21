//Adding Fragments Dynamically and working qith addToBackStack()
package com.example.android.cw47;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout fl;
    Button add;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       fl =  findViewById(R.id.fl);
       add = findViewById(R.id.bt1);

       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)

           {
               BlankFragment bf = new BlankFragment();
               fl.setVisibility(View.VISIBLE);
             FragmentManager fm =  getSupportFragmentManager();
             FragmentTransaction ft = fm.beginTransaction();
             ft.add(R.id.fl,bf,"---");
             ft.addToBackStack("");//will remove the fragement when back button pressed
             ft.commit();



           }
       });
    }
}
