package com.example.android.hw10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
     ArrayList al;
    ListView lt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lt=(ListView)findViewById(R.id.lv);
        loadList();
    }

    void loadList()
    {

        al=new ArrayList();
        Booking_Databse bd= new Booking_Databse(this);
        SQLiteDatabase sldb = bd.getWritableDatabase();
        Cursor c= sldb.query(Booking_Databse.TABLENAME2,null,null,null,null,null,null);
        if(c.moveToFirst())
        {
         do
         {
           String origin=c.getString(0) ;
             String destination=c.getString(1) ;
             String date=c.getString(2) ;
             al.add(origin);
             al.add(destination);
             al.add(date);
        }
        while(c.moveToNext());
            ArrayAdapter ad =new ArrayAdapter(this,R.layout.text_layout,al);
            lt.setAdapter(ad);

    }
        else
        {
            Toast.makeText(this,"No Data found",Toast.LENGTH_SHORT).show();
        }
     }}
