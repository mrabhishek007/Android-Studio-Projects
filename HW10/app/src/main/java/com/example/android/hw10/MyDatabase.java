package com.example.android.hw10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vikash on 10/15/2017.
 */

public class MyDatabase extends SQLiteOpenHelper
{
    public static final  String DATABASENAME="TRAVEL";
    public static final  int version=1;
    public static final  String TABLENAME="CLIENTRECORD";
    public static final  String COL1="USERNAME";
    public static final  String COL2="PASSWORD";




    public  MyDatabase(Context ct)
    {
        super(ct,DATABASENAME,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            String qry = "create table " + TABLENAME + "(" + COL1 + " text primary key ," + COL2 + " number)";
            db.execSQL(qry);
        }
        catch(Exception e)
        {
            Log.e("Creating Table",""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {



    }
}
