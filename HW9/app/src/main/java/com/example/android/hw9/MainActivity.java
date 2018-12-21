package com.example.android.hw9;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        md= MediaPlayer.create(this,R.raw.song);
        md.start();
       Toast t=  Toast.makeText(getApplicationContext(),"Music Started",Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast t=  Toast.makeText(getApplicationContext(),"Music Stopped",Toast.LENGTH_SHORT);
        t.show();
        md.stop();

    }
}
