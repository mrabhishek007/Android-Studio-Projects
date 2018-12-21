//Working with Menu Resources (a)Options Menu
package com.example.android.cw28;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
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
         MenuInflater mi = getMenuInflater();
         mi.inflate(R.menu.example1,menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if(id==R.id.it1)
        {
            Intent i = new Intent();
            i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(i);
        }
        else
            if(id==R.id.it2)
            {

                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri ref = Uri.parse("http://www.google.com");
                i.setData(ref);
                startActivity(i);
            }
            else
            if(id==R.id.it3)
            {
                String photoid ="com.google.android.apps.photos";

                    PackageManager pm = getPackageManager();
                    Intent i = pm.getLaunchIntentForPackage(photoid);

                    if(i!= null) {
                        startActivity(i);
                    }

                   else
                   {
                       Log.e("app not found",""+e);
                       Toast.makeText(this, "Plz install app from playstore", Toast.LENGTH_SHORT).show();
                   }

            }



        return  true;
    }
}
