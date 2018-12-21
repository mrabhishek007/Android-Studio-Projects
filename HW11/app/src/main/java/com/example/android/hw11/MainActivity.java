package com.example.android.hw11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ListView l;
    String movie_names[] = {"Dawn of the Planet of the Apes","District 9","Transformers: Age of Extinction","X-Men: Days of Future Past","The Machinist","The Last Samurai","The Amazing Spider-Man 2","Tangled", "Rush","Drag Me to Hell"};
    String dir_names[]= {"Matt Reeves","Neill Blomkamp","Michael Bay","Bryan Singer","Brad Anderson","Edward Zwick","Marc Webb","Nathan Greno","Ron Howard","Sam Raimi"};
    int m_images[] = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten};
    int d_images[] = {R.drawable.one_1,R.drawable.two_2,R.drawable.three_3,R.drawable.four_4,R.drawable.five_5,R.drawable.six_6,R.drawable.seven_7,R.drawable.eight_8,R.drawable.nine_9,R.drawable.ten_10};
    String key[] ={"m_img","d_ig","m_name","d_name"};
    int id[]={R.id.m_image,R.id.d_image,R.id.m_name,R.id.d_name};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList al = new ArrayList();
        l = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < m_images.length; i++) {
            HashMap hm = new HashMap();
            hm.put(key[0], m_images[i]);
            hm.put(key[1], d_images[i]);
            hm.put(key[2], movie_names[i]);
            hm.put(key[3], dir_names[i]);
            al.add(hm);
        }
        SimpleAdapter sa = new SimpleAdapter(this, al, R.layout.text_view, key, id);
        l.setAdapter(sa);


        /**
         *
         * USE CUSTOM ADAPTER FOR BUTTON FUNCTIONALITY
         *
         *
        LayoutInflater lf=  getLayoutInflater();
      View v1=  lf.inflate(R.layout.text_view,null,false);
        ImageButton dislike = (ImageButton)v1.findViewById(R.id.dislike);
        ImageButton like = (ImageButton)v1.findViewById(R.id.like);
        final TextView tv =(TextView)v1.findViewById(R.id.like_status);
        like.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tv.setText("You liked this");
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tv.setText("You Dislike this");
            }
        });
         */

    }
    }

