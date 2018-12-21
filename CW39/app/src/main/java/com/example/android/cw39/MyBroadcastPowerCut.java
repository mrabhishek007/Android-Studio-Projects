package com.example.android.cw39;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Vikash on 11/17/2017.
 */

public class MyBroadcastPowerCut extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        ImageView iv = new ImageView(context);
        iv.setImageResource(R.drawable.power);
        Toast t = new Toast(context);
        t.setGravity(Gravity.BOTTOM,0,0);
        t.setDuration(Toast.LENGTH_LONG);
        t.setView(iv);
        t.show();

        Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show();
    }
}
