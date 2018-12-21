//Messaging using service

package com.example.vikash.cw73;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button);


        //Creating Pending Intent for Message Send and Delivered

        Intent i1 = new Intent(this,MessegeSentActivity.class);
        Intent i2 = new Intent(this,MessageDeliveredActivity.class);
       final PendingIntent msg_sent  =  PendingIntent.getActivity(this,0,i1,0);
       final PendingIntent msg_delivered  =  PendingIntent.getActivity(this,0,i2,0);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String msg = "Hello,I am sending msg using pending Intent";
                SmsManager  sm = SmsManager.getDefault();
                sm.sendTextMessage("7544830374",null,msg,msg_sent,msg_delivered);
                Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}
