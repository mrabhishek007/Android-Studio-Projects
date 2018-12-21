package com.example.vikash.cw68;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Vikash on 12/4/2017.
 */

public class MyService extends Service
{

     MediaPlayer mp;
    @Nullable
    @Override

    public IBinder onBind(Intent intent)
    { return null;
    }

    @Override
    public void onCreate()
    {
        //stopSelf();//stop the existong services
        Log.e("onCreate","+onCreate");
        super.onCreate();
       mp =  MediaPlayer.create(this,R.raw.rhythm_devine);//Used for initialization purposes
    }

    //It will be called after onCreate when a service is started.We no longer use onCreate().We use onStartCommand() instead of this.
    @Override
    public void onStart(Intent intent, int startId)
    {
        Log.e("onstar","+onStart");
        super.onStart(intent, startId);
         mp.start();
    }

    @Override
    public void onDestroy()
    {
        Log.e("onDestroy","+onDestroy");
        super.onDestroy();
        mp.stop();
    }


}