//Working with ContextMenu

package com.example.android.cw30;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.b1);
        registerForContextMenu(b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Long press to access ContextMenu", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.ex2,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
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

