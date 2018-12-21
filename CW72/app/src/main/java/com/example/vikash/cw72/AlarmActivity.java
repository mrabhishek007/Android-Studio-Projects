package com.example.vikash.cw72;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity
{
    MediaPlayer mp;
    AlarmManager am ;
    PendingIntent pi;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        final Button b1 = findViewById(R.id.button3);
        Button b2 = findViewById(R.id.button4);

        Intent i= new Intent(this,getClass());
        pi =  PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        mp = MediaPlayer.create(this,R.raw.uska);
        mp.start();

        //Snnozing the alarm for 10sec

        b1.setOnClickListener(
                new View.OnClickListener() {

            //For snoozing alarm for 10sec
            @Override
            public void onClick(View v)
            {
                mp.stop();
               long c_time =  SystemClock.currentThreadTimeMillis();
               long snooze_in_millis = (c_time+(10*1000));

               am.set(AlarmManager.RTC_WAKEUP,snooze_in_millis,pi);
               Toast.makeText(AlarmActivity.this, "Alarm Snoozed for 10sec", Toast.LENGTH_SHORT).show();
               finish();
            }

        });


                  //Cancelling the alarm

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                am.cancel(pi);
                mp.stop();
                Toast.makeText(AlarmActivity.this, "Alarm Stopped", Toast.LENGTH_SHORT).show();
                b1.setVisibility(View.GONE);

            }
        });

    }
}
