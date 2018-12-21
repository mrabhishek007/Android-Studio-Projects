//Radio Button with Automatic Listener
package com.example.android.cw24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    RadioButton rb1,rb2,rb3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= findViewById(R.id.tv2);
         rb1 =findViewById(R.id.rb1);
         rb2 =findViewById(R.id.rb2);
        rb3 =findViewById(R.id.rb3);


    }

    void display(View v)
    {
        int id=v.getId();
        if(id==R.id.rb1)
        {
            tv.setText(rb1.getText().toString());
        }
        else
        if(id==R.id.rb2)
        {
            tv.setText(rb2.getText().toString());
        }
        else
        {
            tv.setText(rb3.getText().toString());
        }




    }
}
