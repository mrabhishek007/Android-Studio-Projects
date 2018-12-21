package com.example.vikash.locationdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    LocationListener locationListener;
    private String[] myPermission = {Manifest.permission.ACCESS_FINE_LOCATION};
    int myReqCode = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.e("Location :",location.getLatitude()+location.getLongitude()+"");
                Toast.makeText(MainActivity.this, location.getLatitude()+location.getLongitude()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        // Check permission status

        if( ContextCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ){

            ActivityCompat.requestPermissions(this , myPermission , myReqCode);

        }else{

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER , 0 , 0 ,locationListener);

        }

    }

    //Called After User Permission response

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == myReqCode){

            if(myPermission[0].equals(permissions[0])){

                if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    //Permission is granted by User

                    //Rechecking the permission
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER , 0 , 0 ,locationListener);

                    }

                }

            }

        }
    }
}


