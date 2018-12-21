//WORKING WITH SPINNERS
package com.example.android.cwp17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TextView tv;
    Spinner sp;
    String items[]={"Select","India","China","US"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=(Spinner)findViewById(R.id.spinner);
        tv=(TextView)findViewById(R.id.textView);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,items);
        sp.setAdapter(ad);
    }

    void arrayAdapter(View v)
    {

         Object o=sp.getSelectedItem();
        String  ss = o.toString();
        if(ss.equals(items[0]))
        {
            tv.setText("-----");
        }
        else
        {
            tv.setText(ss);
        }

    }
}
