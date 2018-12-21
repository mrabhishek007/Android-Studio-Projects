package com.example.android.cpw7;

/**
 * Created by Vikash on 10/5/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Vikash on 10/5/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText et;
    Button bt;
    TextView tv;
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.main_layout);
        /**
         View v=findViewById(R.id.et);
         et=(EditText)v;                           //Downcasting
         OR
         */
        et=(EditText)findViewById(R.id.et);//DownCasting
        bt=(Button)findViewById(R.id.b1);//Downcasting
        tv=(TextView)findViewById(R.id.tv2);//Downcasting
        bt.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Editable e= et.getText();
        String s =e.toString();
        tv.setText(s);
        et.setText("");
    }

}
