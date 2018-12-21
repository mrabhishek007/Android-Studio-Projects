package com.example.android.cwp8;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Vikash on 10/5/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    int x=1;
    LinearLayout ll;
   public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.main_layout);

        Button bt =(Button)findViewById(R.id.b1);//downcasting
        ll= (LinearLayout)findViewById(R.id.ll);//downcasting
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        int color=0;
        if(x==1)
        {
           color=Color.BLUE;
          x=2;
        }
        else if(x==2)
        {
            color=Color.WHITE;
            x=1;
        }
        ll.setBackgroundColor(color);
        }
}
