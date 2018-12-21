package com.example.android.cwp9;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivityActivity extends AppCompatActivity implements View.OnClickListener

{
    LinearLayout ll;
    int x=1;
    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button b =(Button)findViewById(R.id.b1);
        ll=(LinearLayout)findViewById(R.id.ll);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        if(x==1)
        {
            color= Color.RED;
            x=2;
        }
        else
            if (x == 2)
            {
                color = Color.BLUE;
                x = 3;
            }
               else if(x==3)
            {
                color=Color.YELLOW;
                x=4;
            }
            else  if(x==4)
            {
                color=Color.WHITE;
                x=1;
            }

        ll.setBackgroundColor(color);
    }
}
