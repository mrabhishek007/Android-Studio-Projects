package com.example.android.cw41;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vikash on 11/18/2017.
 */

public class MyBr extends BroadcastReceiver {
    Context ct;
    ProgressDialog pd ;

    @Override
    public void onReceive(Context context, Intent intent) {
        Listen ls = new Listen(context);
        Object o1 = context.getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyManager tm = (TelephonyManager) o1;
        tm.listen(ls, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public  class Listen extends PhoneStateListener
    {
        int count=0;
        String inumber;
        double lat,lang;

        public Listen(Context con)
        {
            ct = con;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber)
        {

            inumber = incomingNumber;

            super.onCallStateChanged(state, incomingNumber);
            if (state == TelephonyManager.CALL_STATE_RINGING)
            {
                sendMsg();
            }

        }


        public void sendMsg() {
            if (inumber.equals("+917544830374"))
            {
                Log.e("iclltrigger","trigger");
                getLatLang();
            }
        }

        public void getLatLang()
        {
            Object obj = ct.getSystemService(Context.LOCATION_SERVICE);
            LocationManager lm = (LocationManager) obj;
            if (ActivityCompat.checkSelfPermission(ct, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ct, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 50, new LocationListener()
            {
                @Override
                public void onLocationChanged(Location location)
                {

                        Log.e("latlangfind","latlangfound");

                            lat = location.getLatitude();
                            lang = location.getLongitude();
                            Toast.makeText(ct, "Lat and Lang found " + lat + lang, Toast.LENGTH_SHORT).show();
                            if (lat != 0)

                            {
                                SmsManager sm = SmsManager.getDefault();
                                sm.sendTextMessage("+917544830374", null, "You got a call from : " + inumber + ",Lang-" + lang + ",Lat-" + lat, null, null);
                                Log.e("msg_send", "msg sent to no");
                                Toast.makeText(ct, "Message send to +917544830374 ", Toast.LENGTH_SHORT).show();
                            }


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras)
                {

                }

                @Override
                public void onProviderEnabled(String provider)
                {
                    Toast.makeText(ct, "GPS On", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProviderDisabled(String provider)
                {
                    Toast.makeText(ct, "GPS Disabled,plz turn on the GPS", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}
