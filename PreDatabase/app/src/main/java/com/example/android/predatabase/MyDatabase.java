package com.example.android.predatabase;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

/**
 * Created by Naveen on 10/15/2017.
 */

public class MyDatabase extends SQLiteOpenHelper
{
    public static final String DBNAME = "sathya";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "EMP";
    public static final String COL1 = "IDNO";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SALARY";


    public MyDatabase(Context context)
    {
        super(context,DBNAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            String qry = "CREATE TABLE " + TABLE_NAME + "(" + COL1 + " NUMBER PRIMARY KEY," + COL2 + " TEXT," + COL3 + " REAL)";
            db.execSQL(qry);
        }
        catch (Exception e)
        {
            Log.e("Table creation",""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
