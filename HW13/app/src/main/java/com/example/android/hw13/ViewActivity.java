package com.example.android.hw13;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    Button b2,b3;
    TextView tv1,tv2,tv3;
    String name;
    double sal;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        Intent i = getIntent();
        Bundle bd = i.getExtras();
        id = bd.getInt("key1");
        name = bd.getString("key2");
        sal = bd.getDouble("key3");
        tv1.setText("Idno - " + String.valueOf(id));
        tv2.setText("Name - " + name);
        tv3.setText("Salary - " + String.valueOf(sal));

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MyDatabase md = new MyDatabase(ViewActivity.this);
                    SQLiteDatabase sld = md.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(MyDatabase.COL1, id);
                    cv.put(MyDatabase.COL2, name);
                    cv.put(MyDatabase.COL3, sal);
                    long res = sld.insert(MyDatabase.TABLE_NAME, null, cv);
                    if (res != -1) {
                        Toast.makeText(ViewActivity.this, "Details saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ViewActivity.this, "Details already available", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("Error in insertion", String.valueOf(e));
                }


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

    }

    }





