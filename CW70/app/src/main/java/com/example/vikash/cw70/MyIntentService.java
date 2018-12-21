package com.example.vikash.cw70;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vikash on 12/6/2017.
 */

public class MyIntentService extends IntentService//IntentService extends Service
{

    protected MyIntentService()
    {
        super("IntentService Thread");//Worker Thread Name

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)//Creates a seperate worker Thread other than MainThread
    {
        Log.e("Intent Service","Thread id - "+Thread.currentThread());
        SystemClock.sleep(5000);//No need to write interrupted Exception
        Log.e("Intent Service" , "I am from Intent Service");
            /**
        try {
            Thread.sleep(5000);
             Log.e("Intent Service" , "I am from Intent Service");
        }
        catch(InterruptedException e)
        {
            Log.e(" Thread Exception",""+e);
        }

             */
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
