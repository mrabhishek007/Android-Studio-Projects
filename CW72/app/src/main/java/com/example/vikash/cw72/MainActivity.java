//Working with Alarm Manager

package com.example.vikash.cw72;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    EditText et;
    PendingIntent pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        et = findViewById(R.id.editText);

                 //Generating Pending Intent
        Intent i = new Intent(this,AlarmActivity.class);
        pi =  PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

       alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String string_dur  = et.getText().toString().trim();

                    if(string_dur.isEmpty())
                     {
                         et.setError("Empty");
                     }
                 else
                     {
                       int sec =   Integer.parseInt(string_dur);
                       long millis = (sec*1000) ;
                       alarmManager.set(AlarmManager.RTC_WAKEUP,millis,pi);
                         Toast.makeText(MainActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
                         finish();
                     }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                alarmManager.cancel(pi);
                Toast.makeText(MainActivity.this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
