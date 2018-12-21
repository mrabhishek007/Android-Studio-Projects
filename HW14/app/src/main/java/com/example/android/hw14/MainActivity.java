package com.example.android.hw14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    Spinner s1;
    Button b1,b2;
    EditText et1;
    ImageView iv;
    int pos=0;
    String getmname;
    double  rating;


    String movie_names[] = {"Select Movie","Transformers: Age of Extinction","The Amazing Spider-Man 2","The Last Samurai","X-Men: Days of Future Past","Tangled"};
    int images[]={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1 = (Spinner)findViewById(R.id.s1);
        iv = (ImageView)findViewById(R.id.iv);
        et1 = (EditText)findViewById(R.id.et1);
        b1= (Button)findViewById(R.id.b1);
        b2 =(Button)findViewById(R.id.b2);


        ArrayAdapter ad = new ArrayAdapter(this,R.layout.spinner_style,movie_names);
        s1.setAdapter(ad);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                pos=position;
                if(pos!=0)
                {

                    iv.setImageResource(images[position - 1]);
                    getmname= movie_names[position];
                }
                else
                {
                    iv.setImageResource(android.R.color.transparent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String rat = et1.getText().toString().trim();
                if(pos==0)
            {
                et1.setError("Plz select movie_name");
                et1.setText("");
            }
              else if(rat.isEmpty())
              {
                  et1.setError("Empty");
              }

                else  if(Double.parseDouble(rat)>5)
                  {
                      et1.setText("");
                      et1.setError("Plz rate between 1-5");
                  }

                  else
                  {
                      rating = Double.parseDouble(rat);

                      iv.setImageResource(android.R.color.transparent);
                      et1.setText("");
                      Toast.makeText(MainActivity.this,"Details saved",Toast.LENGTH_SHORT).show();



                  }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rating!=0.0) {
                    Intent i = new Intent(MainActivity.this, ViewActivity.class);
                    i.putExtra("key1", getmname);
                    i.putExtra("key2", rating);
                    i.putExtra("key3", pos - 1);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Plz Enter Rating",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
