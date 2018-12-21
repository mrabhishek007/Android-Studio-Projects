//Accessing the langitude and longitude using GPS & Network provider(Without Runtime Permission)
package com.example.vikash.cw64;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener
{
    LocationManager lm;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       tv1 = findViewById(R.id.tv1);

        Object o1 = getSystemService(LOCATION_SERVICE);
       lm =  (LocationManager)o1;


      //  lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);//Provides the cell tower location
       lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);//We can also use anonymous class instead of current class object as 4th parameter

    }


    @Override
    public void onLocationChanged(Location location)
    {
     double latitude = location.getLatitude();
     double langitude  = location.getLongitude();
     tv1.setText("Latitude : "+langitude+",Langitude : "+latitude);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {


    }

    @Override
    public void onProviderEnabled(String provider)
    {

       ImageView iv = new ImageView(this);

            /** Method-2 to set Toast
        Resources rs = getResources();
        Drawable drawable = rs.getDrawable(R.drawable.gpson);
        iv.setImageDrawable(drawable);  */

        iv.setImageResource(R.drawable.gpson);
        Toast t =  new Toast(this);
        t.setDuration(Toast.LENGTH_LONG);
        t.setView(iv);
        t.show();
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        ImageView iv =new ImageView(this);
        iv.setImageResource(R.drawable.gpsoff);
        Toast t = new Toast(this);
                t.setDuration(Toast.LENGTH_LONG);
                t.setView(iv);
                t.show();
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        lm.removeUpdates(this);//remove location updates to save battery
    }
}
