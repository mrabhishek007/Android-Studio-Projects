package com.example.android.cwp13;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        et3 = (EditText)findViewById(R.id.et3);
    }

    void myButton(View v)
    {
        String s1=et1.getText().toString();
        String name=et2.getText().toString().trim();
        String s3=et3.getText().toString();

        if(s1.isEmpty())
        {
            et1.setError("Empty Id Not Allowed");
            et1.requestFocus();
        }
        else if(name.isEmpty())
        {
            et2.setError("Empty Name Not Allowed");
            et2.requestFocus();
        }

        else if(s3.isEmpty())
        {
            et3.setError("Empty Salary Not Allowed");
            et3.requestFocus();
        }
        else
        {
            int id=Integer.parseInt(s1);
            double sal=Double.parseDouble(s3);

            MyDatabase md = new MyDatabase(this);
            SQLiteDatabase sqd= md.getWritableDatabase();
            try
            {
                String sql="insert into emp_details values("+ id +",'"+ name +"',"+ sal + ")";
                sqd.execSQL(sql);
                Toast.makeText(this,"Value Inserted",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
            }
            catch(Exception e)
            {
                Toast.makeText(this,"Error while Inserting Val",Toast.LENGTH_SHORT).show();
                Log.e("Insering Values",""+e);
            }

        }


    }
}
