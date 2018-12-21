//Working with Android  Touch

package com.example.android.cw60;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends Activity
{
     TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView2);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        super.onTouchEvent(event);
       float X_axis =  event.getX();
       float Y_axis =  event.getY();
        tv.setText("Current pos : X axis = "+X_axis+", Y axis = "+Y_axis);
        return  true;
    }
}
