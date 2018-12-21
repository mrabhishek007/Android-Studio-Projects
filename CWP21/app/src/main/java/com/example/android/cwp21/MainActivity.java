package com.example.android.cwp21;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity
{
    View v1;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fb = (FloatingActionButton)findViewById(R.id.fab);

    }

    void viewLogin(View v)
    {
        LayoutInflater li  = getLayoutInflater();
         v1 = li.inflate(R.layout.loginwindow,null,true);
         et1 = (EditText)v1.findViewById(R.id.et1);
         et2 = (EditText)v1.findViewById(R.id.et2);
         Button b1= (Button)v1.findViewById(R.id.b1);
        ImageButton b2= (ImageButton)v1.findViewById(R.id.ib);
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setView(v1);
       final AlertDialog adb = ad.create();
        adb.show();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               adb.dismiss();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             if((et1.getText().toString().trim()).equalsIgnoreCase("Vikash") && et2.getText().toString().trim().equals("android"))
             {
                 Toast.makeText(MainActivity.this,"User Successfully Logged in",Toast.LENGTH_SHORT).show();
                 adb.dismiss();
             }
             else
             {
                 Toast.makeText(MainActivity.this,"Invalid User",Toast.LENGTH_SHORT).show();
                 et1.setText("");
                 et2.setText("");
             }
            }
        });
    }
}
