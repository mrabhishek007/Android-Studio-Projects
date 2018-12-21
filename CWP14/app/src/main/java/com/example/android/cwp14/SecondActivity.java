package com.example.android.cwp14;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vikash on 10/9/2017.
 */


public class SecondActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText et4;
    TextView tv1,tv2;
    Button b3;
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.second_layout);
        et4=(EditText)findViewById(R.id.editText4);
        b3=(Button)findViewById(R.id.button3);
        tv1=(TextView)findViewById(R.id.textView2);
        tv2=(TextView)findViewById(R.id.textView3);
        b3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        String eid = et4.getText().toString();
        if(eid.isEmpty())
        {
            et4.setError("Plz enter IDNO");
        }
        else
        {
            int id =Integer.parseInt(eid);
            MyDatabase md = new MyDatabase(this);
            try {
                SQLiteDatabase sb = md.getReadableDatabase();
                String qry = "Select " + MyDatabase.EMPNAME + "," + MyDatabase.EMPSAL + " from " + MyDatabase.TABLENAME + " where " + MyDatabase.EMPID + " = "+id ;
                Cursor c = sb.rawQuery(qry, null);
                boolean res = c.moveToNext();

                if (res) {
                    String name = c.getString(0);
                    Double salary = c.getDouble(1);
                    tv1.setText("Name : " + name);
                    tv2.setText("Salary : " + salary);
                } else {
                    et4.setError("ID NO not found");
                    tv1.setText("");
                    tv2.setText("");
                }
            }

            catch(Exception e)
            {
                Toast.makeText(this,"Exception Occuring",Toast.LENGTH_SHORT).show();
                Log.e("Searching Data in table",""+e);
            }
        }
    }


}
