//Working with Notification Services

package com.milk.project.com.cw74;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NotificationManager nm;
    PackageManager pm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);

        nm =  (NotificationManager)getSystemService(NOTIFICATION_SERVICE);



          //Use target sdk version -19  in app build.gradle file for colourful image in notification otherwise it be in black&white color

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NotificationCompat.Builder nb = new NotificationCompat.Builder(MainActivity.this);
                nb.setSmallIcon(R.drawable.phone_call);
                nb.setContentTitle("1st Notification");
                nb.setContentText("This is a phone notification");
                Notification notf = nb.build();
                nm.notify(101,notf);


            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                NotificationCompat.Builder nb = new NotificationCompat.Builder(MainActivity.this);
                nb.setSmallIcon(R.drawable.notification2);
                nb.setContentTitle("Notification 2");
                nb.setContentText("I am a Notification service");
                Notification nf = nb.build();
                nm.notify(102,nf);
            }
        });

        //Listening action from notifications and changing color of notification(on any api level)

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                NotificationCompat.Builder nb = new NotificationCompat.Builder(MainActivity.this);
                nb.setSmallIcon(R.drawable.ic_stat_name);
                nb.setContentTitle("Music");
                nb.setContentText("Music Notification ");
                nb.setColor(Color.BLUE);
                nb.setSubText("I am SubText"); //Adding Subtext to notification

                 //Pending Intent will be called when notification is clicked
                Intent i = new Intent("android.intent.action.MUSIC_PLAYER");
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,i,0);

                 nb.setContentIntent(pi);
                 nb.setAutoCancel(true);//Automatically removes notification when otification is clicked

                 Notification nf = nb.build();
                nm.notify(103,nf);

            }
        });


             //Providing BigPicture Style to Notifications

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Bitmap bitmap =  BitmapFactory.decodeResource(getResources(),R.drawable.amazon);


                NotificationCompat.Builder nb = new NotificationCompat.Builder(MainActivity.this);
                nb.setSmallIcon(R.drawable.amazon);
                nb.setContentTitle("Amazon Dhamaka");
                nb.setContentText("Upto 90% off on Products");
                nb.setSubText("Don't miss this Chance");
                nb.setAutoCancel(true);
                nb.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));

                String package_name="in.amazon.mShop.android.shopping";

                pm = getPackageManager();
                Intent i = pm.getLaunchIntentForPackage(package_name);//Launching Amazon App if installed in device,i will return null if app is not found in device

                if(i!=null)
                {
                    PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, i, 0);
                    nb.setContentIntent(pi);

                    Notification nf = nb.build();
                    nm.notify(104, nf);
                }

                else
                {
                       //If amazon is not installed launches amazon in google play store or in browser


                     Intent x = new Intent();
                     x.setAction(Intent.ACTION_VIEW);

                     Uri uri = Uri.parse("market://details?id="+package_name);//Launches in PlayStore
                    //Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+package_name);//Launches in installled browser
                    // Uri uri =Uri.parse("http://amazon.in");//Launches amazon.in in installed browser

                     x.setData(uri);

                   // x.setPackage("com.android.chrome");//launches amazon.in in Only Google chrome

                    PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,x,0);
                    nb.setContentIntent(pi);
                    Notification nf =  nb.build();
                    nm.notify(104,nf);
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent();
                i.setAction("android.intent.action.MUSIC_PLAYER");
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,i,0);
                NotificationCompat.Builder nb = new NotificationCompat.Builder(MainActivity.this);
                nb.setAutoCancel(true);
                nb.setSmallIcon(R.drawable.firangi);
                nb.setContentText("Firangi(2017) Music");
                nb.setContentTitle("Music");
                nb.setSubText("Kapil Sharma");
                nb.setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle());
                nb.addAction(android.R.drawable.ic_media_play,"Play",pi);
                nb.addAction(android.R.drawable.ic_media_pause,"Pause",pi);
                nb.addAction(android.R.drawable.ic_media_next,"Stop",pi);

               Notification nf = nb.build();
               nm.notify(105,nf);

            }
        });
    }
}
