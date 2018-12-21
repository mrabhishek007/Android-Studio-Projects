//Working With SharedPrefences using MediaPlayer(Similar to background music in games)

package com.example.android.cw59;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch switch1;
    MediaPlayer mp;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switch1 =  findViewById(R.id.s1);
        mp = MediaPlayer.create(this,R.raw.uska_hi_bana);

        //sp =  getPreferences(MODE_PRIVATE); used when there is only Single Sharedprefences in an app.

        sp = getSharedPreferences("Background_music",MODE_PRIVATE);
        spe = sp.edit();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                if(isChecked)
                {
                   boolean res =  switch1.isChecked();
                   spe.putBoolean("key1",true);
                   spe.commit();
                   mp =  MediaPlayer.create(MainActivity.this,R.raw.uska_hi_bana);
                   mp.start();
                }
                else
                {
                    spe.putBoolean("key1",false);
                    spe.commit();
                    mp.stop();//if We use mp.stop() mp will be destroyed.So we can't call mp.start() using mp
                    //mp.pause();//if we use mp.pause() mp will not be destoyed.so we can use mp.start()
                }
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        boolean switch_status =  sp.getBoolean("key1",true);
        if(switch_status)
        {
           switch1.setChecked(true);
            mp.start();
        }
        else
        {
            switch1.setChecked(false);
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
