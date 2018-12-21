//Working with onStartCommand() of Services

package com.example.vikash.cw69;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Button start_music = findViewById(R.id.button);
       Button stop_music = findViewById(R.id.button2);

       //Starting a service

       start_music.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {

               startService(new Intent(MainActivity.this,MyService.class));

           }
       });

        //Stopping a service

       stop_music.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {

               stopService(new Intent(MainActivity.this,MyService.class));
           }
       });
    }
}
