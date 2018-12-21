package com.example.android.hw16;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    EditText et1,et2,et3;
    public static final String  mappackage ="com.google.android.apps.maps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1= (Button)findViewById(R.id.b1);
        b2= (Button)findViewById(R.id.b2);
        b3= (Button)findViewById(R.id.b3);
        b4= (Button)findViewById(R.id.b4);
        b5= (Button)findViewById(R.id.b5);
        b6= (Button)findViewById(R.id.b6);
        b7= (Button)findViewById(R.id.b7);
        b8= (Button)findViewById(R.id.b8);
        b9= (Button)findViewById(R.id.b9);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=hotels");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=atms");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=restaurant");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=schools");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=pharmacy");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=hospitals");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=bus stop");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ref =Uri.parse("geo:0,0?q=Railway stations");
                Intent i = new Intent(Intent.ACTION_VIEW,ref);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               LayoutInflater lf = getLayoutInflater();
                View v1 = lf.inflate(R.layout.search_style,null,false);
                b10 = (Button)v1.findViewById(R.id.b10);
                b11 = (Button)v1.findViewById(R.id.b11);
                 et1 = (EditText) v1.findViewById(R.id.et1);
                 et2 = (EditText) v1.findViewById(R.id.et2);
                 et3 = (EditText) v1.findViewById(R.id.et3);
                ImageView iv = (ImageView) v1.findViewById(R.id.iv);
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setView(v1);
                ad.setCancelable(true);
                ad.create().show();

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String search = et1.getText().toString().trim();
                        if(search.isEmpty())
                        {
                            et1.setError("empty");
                        }
                        else {
                            Uri ref =Uri.parse("geo:0,0?q="+search);
                            Intent i = new Intent(Intent.ACTION_VIEW,ref);
                            i.setPackage(mappackage);
                            startActivity(i);
                        }
                    }
                });

                b11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        String source =  et2.getText().toString().trim();
                        String destination =  et3.getText().toString().trim();
                        if(source.isEmpty())
                        {
                            et2.setError("empty source loc");
                            et2.requestFocus();
                        }
                        else
                            if(destination.isEmpty())
                            {
                                et3.setError("empty destination loc");
                                et3.requestFocus();
                            }
                        else
                        {
                            Uri sourceuri = Uri.parse("google.navigation:q="+source);
                            //Uri sourceuri = Uri.parse("google.navigation:q="+source+"&avoid=t");//avoiding tolls
                           // Uri desturi = Uri.parse("google.navigation:q="+destination+"&avoid=t");
                            
                            Uri desturi = Uri.parse("google.navigation:q="+destination);
                            Intent nav = new Intent(Intent.ACTION_VIEW,desturi);
                            nav.setPackage(mappackage);
                            startActivity(nav);

                        }
                    }
                });







            }
        });

    }
}
