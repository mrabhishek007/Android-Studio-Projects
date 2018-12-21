package com.example.android.cpw5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
        View v = findViewById(R.id.ll);
        LinearLayout l=(LinearLayout)v;
        l.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        Toast.makeText(this,"HELLO VIKASH!",Toast.LENGTH_SHORT).show();
    }
}
