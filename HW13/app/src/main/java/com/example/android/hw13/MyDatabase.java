package com.example.android.hw13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Vikash on 10/25/2017.
 */

public class MyDatabase extends SQLiteOpenHelper

{
    public final static String DATABASE_NAME="MYDATABASE";
    public final static String TABLE_NAME="EMP";
    public final static String COL1="IDNO";
    public final static String COL2="EMPNAME";
    public final static String COL3="EMPSAL";
    public final static int VER=1;


    public MyDatabase(Context ct)
    {
        super(ct,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String qry = "create table "+TABLE_NAME+"("+COL1+" number primary key,"+COL2+" text , "+COL3+" real)";
        try {
            db.execSQL(qry);
        }
        catch(Exception e)
        {
            Log.e("Table Creation",String.valueOf(e));
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
