package com.example.android.hw10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vikash on 10/16/2017.
 */

public class Booking_Databse extends SQLiteOpenHelper
{
    public static final  String DATABASENAME2="TRAVELRECORDS";
    public static final  int version=1;
    public static final  String TABLENAME2="BOOKINGRECORD";
    public static final  String COL1="ORIGIN";
    public static final  String COL2="DESTINATION";
    public static final  String COL3="DATE";

    public Booking_Databse(Context ct2)
    {
        super(ct2,DATABASENAME2,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            String qry = "create table "+TABLENAME2+"("+COL1+" text,"+COL2+" text,"+COL3+" text)";
            db.execSQL(qry);
        }

        catch(Exception e)
        {
            Log.e("Error in Booking Table",""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
