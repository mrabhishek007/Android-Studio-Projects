package com.milk.project.com.milkproject.milkdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Vikash on 12/7/2017.
 */

public class MilkDatabase extends SQLiteOpenHelper
{
    public static final String DB_NAME ="MILK";
    public static final int  DB_VERSION =1;
    public static final String TABLE_NAME = "TABLE1";
    public static final String COL1 = "NAME";
    public static final String COL2 = "MOB_NO";
    public static final String COL3 = "EMAIL";
    public static final String COL4 = "OTP";

    public MilkDatabase(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

      //Creating the Table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            String qry = "create table " + TABLE_NAME + "( " + COL1 + " text," + COL2 + " number," + COL3 + " text,"+ COL4 + " text)";
            db.execSQL(qry);
            Log.e("Table1","Table created");
        }
        catch(Exception e)
        {
            Log.e("Table1-->","Problem in table creation Exception :: "+e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
