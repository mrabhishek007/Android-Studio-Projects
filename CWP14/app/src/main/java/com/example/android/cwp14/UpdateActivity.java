package com.example.android.cwp14;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Vikash on 10/11/2017.
 */

public class UpdateActivity extends AppCompatActivity
{
    EditText et1,et2,et3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatelayout);
        et1=(EditText)findViewById(R.id.editText6);
        et2=(EditText)findViewById(R.id.editText7);
        et3=(EditText)findViewById(R.id.editText8);
    }
    public void updateDb(View v)
    {
        String id=et1.getText().toString();
        String name=et2.getText().toString();
        String salary=et3.getText().toString();
        if(id.isEmpty())
        {
            et1.setError("Empty ID NO Not Allowed");
            et1.requestFocus();
        }
        else
        if(name.isEmpty())
        {
            et2.setError("Empty Name Not Allowed");
            et2.requestFocus();
        }
        else
        if(salary.isEmpty())
        {
            et3.setError("Empty Salary Not Allowed");
            et3.requestFocus();
        }
        else
        {
             int eid=Integer.parseInt(id);
            double esalary=Double.parseDouble(salary);

            try {
                MyDatabase db = new MyDatabase(this);
                SQLiteDatabase sd = db.getWritableDatabase();
                String qry="Update "+MyDatabase.TABLENAME+" set "+MyDatabase.EMPNAME+" = '"+name+"',"+MyDatabase.EMPSAL+" = "+esalary+" where "+MyDatabase.EMPID+" = "+eid;
                 sd.execSQL(qry);
                Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");

            }

            catch(Exception ee)
            {
                Log.e("Updateing Databse",""+ee);
                Toast.makeText(this,"Exception occuring while Updating Both column in DB",Toast.LENGTH_SHORT).show();
            }

        }//end of else
    }//end of updateDb

    public void deleteDb(View v)
    {
        String eid=et1.getText().toString();
        if(eid.isEmpty())
        {
            et1.setError("ID NO. Needed To Delete Records");
            et1.requestFocus();
        }
        else
        {
            boolean res ;
           int eid2 =Integer.parseInt(eid);
            et2.setText("");
            et2.setText("");
            MyDatabase db3 = new MyDatabase(this);
                SQLiteDatabase sb = db3.getReadableDatabase();
                String qry = "Select * from " + MyDatabase.TABLENAME + " where " + MyDatabase.EMPID + " = " + eid2;
                Cursor c = sb.rawQuery(qry, null);
                 res = c.moveToNext();
            if(res)
            {
             try {
                    MyDatabase db2 = new MyDatabase(this);
                    SQLiteDatabase sd2 = db2.getWritableDatabase();
                    String qry2 = "Delete from " + MyDatabase.TABLENAME + " where " + MyDatabase.EMPID + " = " + eid2;
                    sd2.execSQL(qry2);
                    Toast.makeText(this, "Details Successfully Deleted", Toast.LENGTH_SHORT).show();
                    et1.setText("");
                } catch (Exception ee) {
                    Log.e("Deleting in Databse", "" + ee);
                    Toast.makeText(this, "Exception occuring while Deleting in DB", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                et1.setText("");
               et1.setError("Detail Not Found");
                et1.requestFocus();
            }

        }


    }



}
