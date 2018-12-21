package com.example.android.hw13;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText et;
    String ename;
    int eid;
    double esal;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et= (EditText)findViewById(R.id.et4);
    }

    public void searchDatabase(View v)
    {
        String s1 = et.getText().toString().trim();
        if(s1.isEmpty())
        {
            et.setError("Empty ID");
            et.requestFocus();
        }
        else
        {
            boolean x =false;
            int id =Integer.parseInt(s1);
            try {
                MyDatabase md = new MyDatabase(this);
                SQLiteDatabase sld = md.getWritableDatabase();
                c = sld.query(MyDatabase.TABLE_NAME, null, null, null, null, null, null, null);
            }
            catch (Exception e)
            {
                Log.e("Retriving from Database",""+e);
            }
           if(c.moveToFirst()) {
               do {
                   int dbid = c.getInt(0);
                   String name = c.getString(1);
                   double sal = c.getDouble(2);
                   if (id == dbid) {
                       x = true;
                       eid = dbid;
                       ename = name;
                       esal = sal;
                       Intent it = new Intent(this,RetrieveDataActivity.class);
                       it.putExtra("key1", eid);
                       it.putExtra("key2", ename);
                       it.putExtra("key3", esal);
                       startActivity(it);
                   }
               }
               while (c.moveToNext());

               if (x == true) {
                   Toast.makeText(this, "ID Found", Toast.LENGTH_SHORT).show();
               } else {
                   et.setError("Id Not Found");
                   et.setText("");
                   et.requestFocus();
               }


           }
















        }


    }





}
