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

public class MyBroadcastDemo extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        ImageView iv = new ImageView(context);
        iv.setImageResource(R.drawable.pon);
        Toast t = new Toast(context);
        t.setView(iv);
        t.setGravity(Gravity.BOTTOM,0,0);
        t.show();
        Toast.makeText(context,"Power Connected",Toast.LENGTH_SHORT).show();
    }
}
