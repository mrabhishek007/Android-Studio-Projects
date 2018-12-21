package com.example.vikash.cw69;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Vikash on 12/6/2017.
 */

public class MyService extends Service
{
    MediaPlayer mp;
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mp =  MediaPlayer.create(this,R.raw.pyar_mein);

    }

      //It will be executed first when a service is started
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent,flags,startId);
        mp.start();
        return START_NOT_STICKY;//Service will be stopped when Application will be killed
        //return START_STICKY;//Service will be restarted even Application is killed
        //return  START_REDELIVER_INTENT;//Service will be restarted even Application is killed and Intent is redelivered.

    }


    @Override
    public void onDestroy()
    {

        super.onDestroy();
        mp.stop();

    }
}
