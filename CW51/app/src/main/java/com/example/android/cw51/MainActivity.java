//Program to display number of clicks on button using SavingActivityState
package com.example.android.cw51;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button bt;
    int counter=0;
    Bundle bd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bd=savedInstanceState;
        setContentView(R.layout.activity_main);
        tv= findViewById(R.id.tv);
        bt= findViewById(R.id.b1);

        /**    Method 1
        if(bd!=null)
        {
            counter=bd.getInt("counter");
            tv.setText(String.valueOf("Count = "+counter));
        }

        */


        bt.setOnClickListener(new View.OnClickListener()
        {

              @Override
              public void onClick(View v)
              {
                  counter++;
                 tv.setText(String.valueOf("Count = "+counter));

              }
          });
    }
       //Method 2
    protected void onStart() {
        super.onStart();
        if(bd!=null)
        {
            counter=bd.getInt("counter");
            tv.setText(String.valueOf("Count = "+counter));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);
    }
}
