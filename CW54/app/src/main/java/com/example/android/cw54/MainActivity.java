//Getting Data from BroadcastReciever to Activity
package com.example.android.cw54;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    TextView tv,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv1);
        receive_messageData();
    }

    public void receive_messageData()
   {
       Intent i = getIntent();
       Bundle bundle = i.getExtras();
       if(bundle!=null)
       {
           Toast.makeText(this, "Message Received", Toast.LENGTH_SHORT).show();
           String msg =  bundle.getString("message");
           String sender_name = bundle.getString("from");
           tv.setText("From : "+sender_name);
           tv2.setText("Message : "+msg);
       }

   }

}



