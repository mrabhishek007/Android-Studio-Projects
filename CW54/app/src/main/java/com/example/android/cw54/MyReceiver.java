package com.example.android.cw54;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public String PROTOCOL_DATA_UNIT="pdus";

    public void onReceive(Context context, Intent intent)
    {
        Bundle b = intent.getExtras();
        if (b != null)
        {

            String msg = "";
            String from = "";
            Object o = b.get(PROTOCOL_DATA_UNIT);
            Object[] objarray = (Object[]) o;

            for (int i = 0; i < objarray.length; i++)
            {
                byte bt[] = (byte[]) objarray[i];
                SmsMessage sm = SmsMessage.createFromPdu(bt);
                msg += sm.getDisplayMessageBody();
                from = sm.getDisplayOriginatingAddress();

            }

            Intent i = new Intent();
            i.setClassName("com.example.android.cw54", "com.example.android.cw54.MainActivity");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("message",msg);
            i.putExtra("from",from);
            context.startActivity(i);
        }
    }
}