package com.example.android.cwp14;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed1, ed2, ed3;
    MyDatabase md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    public void viewAll(View v)
    {
        Intent i1 = new Intent(this,ViewAll.class) ;
        startActivity(i1);

    }

    public void updateDatabase(View v)
    {
        Intent iu = new Intent(this,UpdateActivity.class);
        startActivity(iu);
    }

    public void onClick(View v)

    {

        int bid=v.getId();
        if(bid==R.id.button)
        {
            String s1 = ed1.getText().toString().trim();
            String name = ed2.getText().toString().trim();
            String s3 = ed3.getText().toString().trim();
            if (s1.isEmpty()) {
                ed1.setError("Empty ID Not Allowed");
                ed1.requestFocus();
            } else if (name.isEmpty()) {
                ed2.setError("Empty Name Not Allowed");
                ed2.requestFocus();
            } else if (s3.isEmpty()) {
                ed3.setError("Empty Salary Not Allowed");
                ed3.requestFocus();
            } else {
                int id = Integer.parseInt(s1);
                double salary = Double.parseDouble(s3);
                md = new MyDatabase(this);
               SQLiteDatabase sld = md.getWritableDatabase();

                try
                {
                    String qry="insert into "+MyDatabase.TABLENAME+" values("+id+",'"+name+"',"+salary+")";
                    sld.execSQL(qry);
                    Toast.makeText(this,"Insertion Successfull", Toast.LENGTH_SHORT).show();
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                }
                catch(SQLiteConstraintException s)
                {
                    ed1.setError("Duplicate Id not Allowed");
                    ed1.requestFocus();
                }
                catch(Exception e)
                {
                    Log.e("Value Insertion",""+e);
                }
            }// end of else*/
        }
        else if(bid==R.id.button2)
        {
            Intent i = new Intent(this,SecondActivity.class);
            startActivity(i);
        }//end of else if
    }//end of onClick()
}//end of class
