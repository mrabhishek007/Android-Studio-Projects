package com.example.android.cwp13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vikash on 10/8/2017.
 */

public class MyDatabase extends SQLiteOpenHelper
{

    Context c;
    public  MyDatabase (Context ct)
    {
        super(ct,"Sathya",null,1);
        c=ct;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            String sql = "create table emp_details(id number primary key,name text,salary real )";
            db.execSQL(sql);
            Toast.makeText(c,"Table Created Successfully",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(c,"Problem while creating table",Toast.LENGTH_SHORT).show();
            Log.e("Table Creation",""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
