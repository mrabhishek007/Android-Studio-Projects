package com.example.android.cwp18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    String country[]={"Select Country","India","Australia","Zimbabe","Canada"};
     Spinner sp;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=(Spinner)findViewById(R.id.spinner);
        tv=(TextView)findViewById(R.id.textView);
        Button bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(this);
        ArrayAdapter ad =new ArrayAdapter(this,R.layout.spinner_layout,country);
        sp.setAdapter(ad);
    }

    @Override
    public void onClick(View v)
    {
        int id =v.getId();//Not necessary when there is only one button to click
        if(id==R.id.button) {
            Object op = sp.getSelectedItem();
            String ss = (String) op;//OR op.toString() WE can use downcasting for converting object to string because Object Class is the superclass of String
            if (ss.equals(country[0])) //OR if(ss.equals)("Select Country")
            {
                Toast.makeText(this, "Plz Select Country",Toast.LENGTH_SHORT).show();
            } else
                {
                tv.setText(ss);
            }
        }
    }
}
