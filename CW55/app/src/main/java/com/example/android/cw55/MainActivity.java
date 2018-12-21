package com.example.android.cw55;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    GifImageButton gifImageButton;
    GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       gifImageButton =  findViewById(R.id.gifbut);
       gifImageView =  findViewById(R.id.gifimg);
       gifImageButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               Toast.makeText(MainActivity.this, "I am Gif ImageButton", Toast.LENGTH_SHORT).show();
           }
       });

       gifImageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               Toast.makeText(MainActivity.this, "I am Gif ImageView", Toast.LENGTH_SHORT).show();
           }
       });

    }
}
