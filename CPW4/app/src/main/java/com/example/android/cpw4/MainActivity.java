package com.example.android.cpw4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Vikash on 10/4/2017.
 */

public class MainActivity extends Activity implements View.OnClickListener
{
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.main_layout);
        View v =findViewById(R.id.b1);
        Button bb =(Button)v;//downcasting
       bb.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        Toast.makeText(this,"Hello Android",Toast.LENGTH_LONG).show();
    }

}
