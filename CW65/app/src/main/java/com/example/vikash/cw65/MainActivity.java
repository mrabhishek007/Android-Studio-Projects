//Runtime Permission
package com.example.vikash.cw65;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,LocationListener
{
     TextView tv;
     LocationManager lm;
     String mypermission[] = {Manifest.permission.ACCESS_FINE_LOCATION};
     int myrequest_code = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.b1);
        tv = findViewById(R.id.tv1);
        b1.setOnClickListener(this);

    }

                     //Called On Button Click

    @Override
    public void onClick(View v)
    {
        int  per_check = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
         if(per_check == PackageManager.PERMISSION_GRANTED)
         {
             lm =  (LocationManager)getSystemService(LOCATION_SERVICE);
             lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
         }
         else
         {
             ActivityCompat.requestPermissions(this,mypermission,myrequest_code);

         }

    }

                  //Called on User Permission response

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==myrequest_code)
        {

            if(mypermission[0].equals(permissions[0]))
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    View v1 = findViewById(R.id.ll);//finding the current Layout view which is Linear Layout
                   onClick(v1);


                }
                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

                }

            }

        }

    }

                 //Location Listener
    @Override
    public void onLocationChanged(Location location)
    {
        tv.setText("Langitude = "+location.getLatitude()+" Longitude =  "+location.getLongitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {


    }

    @Override
    public void onProviderEnabled(String provider)
    {
        Toast.makeText(this, "GPS is Enabled", Toast.LENGTH_SHORT).show();
        ImageView iv = new ImageView(this);
        iv.setImageDrawable(getResources().getDrawable(R.drawable.gpson));
        Toast.makeText(this, "GPS disabled", Toast.LENGTH_SHORT).show();
        Toast t = new Toast(this);
        t.setDuration(Toast.LENGTH_LONG);
        t.setGravity(Gravity.NO_GRAVITY,280,720);
        t.setView(iv);
        t.show();
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        ImageView iv = new ImageView(this);
        iv.setImageDrawable(getResources().getDrawable(R.drawable.gpsoff));
        Toast.makeText(this, "GPS disabled", Toast.LENGTH_SHORT).show();
        Toast t = new Toast(this);
        t.setDuration(Toast.LENGTH_LONG);
        t.setGravity(Gravity.NO_GRAVITY,280,720);
        t.setView(iv);
        t.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lm.removeUpdates(this);
        Toast.makeText(this, "GPS Update Removed", Toast.LENGTH_SHORT).show();
    }
}
