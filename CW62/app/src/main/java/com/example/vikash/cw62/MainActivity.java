//Setting x and y axis using android touch
package com.example.vikash.cw62;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{

    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.b1);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        super.onTouchEvent(event);

        float x_axis = event.getX();
        float y_axis = event.getY();
        b.setX(x_axis);
        b.setY(y_axis);

        return  true;
    }
}
