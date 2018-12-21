package com.example.android.cw40;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Vikash on 11/18/2017.
 */

public class MyBr2 extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        MyClass mc = new MyClass(context);
        Object  o  = context.getSystemService(Context.TELEPHONY_SERVICE);
       TelephonyManager tm = (TelephonyManager)o;
       tm.listen(mc,PhoneStateListener.LISTEN_CALL_STATE);

    }

    public class MyClass extends PhoneStateListener
    {
        long ptime,etime;
        Context ct1;
        public  MyClass(Context ct)
        {
           ct1=ct;
        }
        @Override
        public void onCallStateChanged(int state, String incomingNumber)

        {

            super.onCallStateChanged(state, incomingNumber);
/**
            if(state == TelephonyManager.CALL_STATE_RINGING)
            {
                Toast.makeText(ct1, "Call from "+incomingNumber, Toast.LENGTH_LONG).show();

            }
            else
                if(state == TelephonyManager.CALL_STATE_OFFHOOK)
                {
                    Toast.makeText(ct1, "Call disconnected", Toast.LENGTH_SHORT).show();
                }


 */
               switch (state) {

                   case TelephonyManager.CALL_STATE_RINGING:
                   {
                      Toast.makeText(ct1, "Call from " + incomingNumber, Toast.LENGTH_SHORT).show();
                       break;
                   }

                   case TelephonyManager.CALL_STATE_OFFHOOK:
                   {

                       Toast.makeText(ct1, "Call Answered", Toast.LENGTH_SHORT).show();
                       // ptime= System.currentTimeMillis();

                       break;
                   }
                   case TelephonyManager.CALL_STATE_IDLE:
                   {
                       etime=System.currentTimeMillis();
                     //  long timespend = etime-ptime;
                     //  long sec = (timespend/1000);
                      Toast.makeText(ct1, "Call Disconnected", Toast.LENGTH_SHORT).show();
                      // Toast.makeText(ct1, "Call Duration - "+sec, Toast.LENGTH_SHORT).show();

                       break;
                   }



               }
        }
    }
}
