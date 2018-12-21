package com.example.android.cwp6;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Vikash on 10/4/2017.
 */

public class MainActivity extends Activity implements View.OnClickListener
{
    LinearLayout l1;
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.main_layout);
        View v1= findViewById(R.id.b1);
        Button bb =(Button)v1;
        View v2=findViewById(R.id.ll);
        l1 =(LinearLayout)v2;
        bb.setOnClickListener(this);
    }
    public void onClick(View v)
    {
      l1.setBackgroundColor(Color.RED);
    }
}
