package com.example.android.cwp14;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vikash on 10/9/2017.
 */

public class MyDatabase extends SQLiteOpenHelper
{
    public static final String DATABASENAME="SATHYA";
    public static final String TABLENAME="EMPLOYEEDETAILS";
    public static final String EMPID="IDNO";
    public static final String EMPNAME="NAME";
    public static final String EMPSAL="SALARY";
    Context ct;

    public MyDatabase(Context x)
    {
        super(x,DATABASENAME,null,1);
        ct=x;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
           try
           {
               String qry="CREATE TABLE "+TABLENAME+"("+EMPID+ " NUMBER PRIMARY KEY,"+EMPNAME+" TEXT,"+EMPSAL+" REAL)";
               db.execSQL(qry);
               Toast.makeText(ct, "Table Created", Toast.LENGTH_SHORT).show();
           }
           catch(Exception e)
        {
            Log.e("Table Creation",""+e);
            Toast.makeText(ct,"Problem in Table Creation",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
