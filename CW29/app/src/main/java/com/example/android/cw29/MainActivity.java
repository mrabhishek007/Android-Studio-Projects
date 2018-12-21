package com.example.android.cw29;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

        MenuInflater mi =  getMenuInflater();
        mi.inflate(R.menu.ex2,menu);
        return  true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch (id)
        {
            case R.id.it2:
                    {

                            Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                            startActivity(i);

                            break;
                    }
            case R.id.it3:
            {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
                break;
            }
            case R.id.it4:
            {
                String pid ="com.google.android.apps.photos";
                try
                {
               PackageManager pkm =  getPackageManager();
               Intent i = pkm.getLaunchIntentForPackage(pid);
               startActivity(i);
               break;
                }
                catch(Exception e )
                {
                    Log.e("Photo app ",String.valueOf(e));
                    Toast.makeText(this, "Plz first download the app", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+pid ));
                    startActivity(i);
                    break;
                }}
            case R.id.it6:
            {
                String wid = "com.whatsapp";
                try
                {
                    PackageManager pkm =  getPackageManager();
                    Intent i = pkm.getLaunchIntentForPackage(wid);
                    startActivity(i);
                    break;
                }
                catch(Exception e )
                {
                    Log.e("Photo app ",String.valueOf(e));
                    Toast.makeText(this, "Plz first download the app", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+wid ));
                    startActivity(i);
                    break;
                }
            }
            case R.id.it7:
            {
                String mid="com.facebook.orca";
                try
                {
                    PackageManager pkm =  getPackageManager();
                    Intent i = pkm.getLaunchIntentForPackage(mid);
                    startActivity(i);
                    break;
                }
                catch(Exception e )
                {
                    Log.e("Photo app ",String.valueOf(e));
                    Toast.makeText(this, "Plz first download the app", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+mid ));
                    startActivity(i);
                    break;
                }
            }
            case R.id.it8:
            {
                String gid= "com.google.android.gm";
                try
                {
                    PackageManager pkm =  getPackageManager();
                    Intent i = pkm.getLaunchIntentForPackage(gid);
                    startActivity(i);
                    break;
                }
                catch(Exception e ) {
                    Log.e("Photo app ", String.valueOf(e));
                    Toast.makeText(this, "Plz first download the app", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + gid));
                    startActivity(i);
                    break;
                }
            }
            case R.id.it9:
            {
                Intent i = new Intent(Settings.ACTION_SETTINGS);
                startActivity(i);
            }





        }// end of switch
        return  true;
    }//end of onOptionsItemSelected
}
