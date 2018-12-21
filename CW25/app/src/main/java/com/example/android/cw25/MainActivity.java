package com.example.android.cw25;

import android.graphics.Color;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.Gravity.LEFT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Button b1= findViewById(R.id.button1);
        Button b2= findViewById(R.id.button2);
        Button b3= findViewById(R.id.button3);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)   // Showing TextView as a Toast
           {

               TextView tv= new TextView(MainActivity.this);
               tv.setText("Showing TextView as a Toast");
               tv.setTextColor(Color.BLUE);
               tv.setTextSize(20);
               tv.setBackgroundColor(Color.LTGRAY);
               Toast t = new Toast(MainActivity.this);
               t.setDuration(2000);
               t.setGravity(Gravity.TOP|LEFT,0,0);
               t.setView(tv);
               t.show();
           }
       });

       b2.setOnClickListener(new View.OnClickListener()   //Showing image as a toast
       {
           @Override
           public void onClick(View view)
           {
               ImageView iv = new ImageView(MainActivity.this);
               iv.setImageResource(R.drawable.gg);
               Toast t = new Toast(MainActivity.this);
               t.setDuration(1000);
               t.setGravity(Gravity.TOP,150,150);
               t.setView(iv);
               t.show();

           }
       });

       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               LayoutInflater li = getLayoutInflater();
               View x = li.inflate(R.layout.new_layout,null,false);
               Toast t = new Toast(MainActivity.this);
               t.setDuration(1000);
               t.setGravity(Gravity.CENTER,150,150);
               t.setView(x);
               t.show();

           }
       });
    }
}
