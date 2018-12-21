package com.example.android.cwp10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainLayoutActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Button b1=(Button)findViewById(R.id.b1);
        Button b2=(Button)findViewById(R.id.b2);
        et=(EditText)findViewById(R.id.et1);
        tv=(TextView)findViewById(R.id.tv1);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Editable e= et.getText();
        String name=e.toString();
        int id=v.getId();
        if(id==R.id.b1)
        {
            tv.setText("Name : "+name);
        }
        else
        {
            int length=name.length();
            tv.setText("Length = "+length);
        }
    }
}
