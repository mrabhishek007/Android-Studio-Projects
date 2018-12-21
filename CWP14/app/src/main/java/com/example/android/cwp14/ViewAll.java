package com.example.android.cwp14;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vikash on 10/10/2017.
 */

public class ViewAll extends AppCompatActivity
{
  public void onCreate(Bundle b)
  {
      super.onCreate(b);
      setContentView(R.layout.view_all);
      TableLayout tl=(TableLayout)findViewById(R.id.tl);
      MyDatabase md = new MyDatabase(this);
      try {
          SQLiteDatabase sd = md.getWritableDatabase();
          String qry = "Select * " + "from " + MyDatabase.TABLENAME;

          Cursor c = sd.rawQuery(qry, null);
          boolean res = c.moveToNext();
          if (res)
          {
              do
              {
                  int id=c.getInt(0);
                  String name=c.getString(1);
                  double sal=c.getDouble(2);
                  TableRow tr =new TableRow(this);
                  TextView tv1=new TextView(this);
                  tv1.setTextColor(Color.BLACK);

                  tv1.setTextSize(20);
                  tv1.setEms(6);
                  tv1.setText(String.valueOf(id));

                  TextView tv2=new TextView(this);
                  tv2.setTextColor(Color.BLACK);
                  tv2.setTextSize(20);
                  tv2.setEms(6);
                  tv2.setText(name);

                  TextView tv3=new TextView(this);
                  tv3.setTextColor(Color.BLACK);
                  tv3.setTextSize(20);
                  tv3.setEms(5);
                  tv3.setText(String.valueOf(sal));
                  tr.addView(tv1);
                  tr.addView(tv2);
                  tr.addView(tv3);
                  tl.addView(tr);
              }
              while(c.moveToNext());
          }

          else
          {
              Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
          }
      }
      catch(Exception e)
      {
          Toast.makeText(this, "Exception Occuring", Toast.LENGTH_SHORT).show();
          Log.e("Retrieving all data",""+e);
      }

  }

}
