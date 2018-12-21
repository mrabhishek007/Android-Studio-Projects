//Working with Androidtouch Actions
package com.example.vikash.cw63;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv1);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        super.onTouchEvent(event);
        int action = event.getAction();


       if(action==MotionEvent.ACTION_DOWN)
        {
            tv.setText("Touched");
        }
        if(action==MotionEvent.ACTION_UP)
        {
            tv.setText("Released");
        }

        if(action==MotionEvent.ACTION_MOVE)
        {
            tv.setText("Move");
        }

                    /** Not Working Properly (Confusion)
          if(action==MotionEvent.ACTION_POINTER_1_UP)
            {
                tv.setText("Released 2 Finger");
            }

                if(action==MotionEvent.ACTION_POINTER_1_DOWN)
                {
                    tv.setText("Triggered 2 finger");
                }

                if(action==MotionEvent.ACTION_POINTER_2_UP)
                {
                    tv.setText("Released 3 Finger");
                }

                if(action==MotionEvent.ACTION_POINTER_2_DOWN)
                {
                    tv.setText("Triggered 3 finger");
                }

                if(action==MotionEvent.ACTION_POINTER_3_UP)
                {
                    tv.setText("Released 4 Finger");
                }

                if(action==MotionEvent.ACTION_POINTER_3_DOWN)
                {
                    tv.setText("Triggered 4 finger");
                }


                        */

        return true;
    }
}