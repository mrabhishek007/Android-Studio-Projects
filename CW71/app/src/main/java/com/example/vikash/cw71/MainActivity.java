//Working with Vibrator Service
package com.example.vikash.cw71;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Vibrator vb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Button b1 =  findViewById(R.id.button);
       Button b2 = findViewById(R.id.button2);
              //Getting Vibrator refrence
        Object o = getSystemService(VIBRATOR_SERVICE);
        vb = (Vibrator)o;

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               if(vb.hasVibrator())//Checks whether device has vibrator or not
               {
                   vb.vibrate(5000);//Vibrate for n sec
               }
               else
               {
                   Toast.makeText(MainActivity.this, "Sorry,No Vibrator found on your device", Toast.LENGTH_SHORT).show();
               }
           }
       });

       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               vb.cancel();
               Toast.makeText(MainActivity.this, "Vibrater Stopped", Toast.LENGTH_SHORT).show();

           }
       });

    }
}
