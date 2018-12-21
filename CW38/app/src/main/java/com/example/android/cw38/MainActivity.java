package com.example.android.cw38;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{

    Animation an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv = findViewById(R.id.iv);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                an= AnimationUtils.loadAnimation(MainActivity.this,R.anim.filename);
                iv.startAnimation(an);
            }
        });


    }
}
