package com.example.android.hw10;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
     Button b1,b2,b3,b4,b5,b7,b8;
    boolean bt = false;
    EditText et1,et2;
    TextView cvt;
    String places[]={"Select","Hyderabad","New Delhi","Patna","Ranchi"};
    String uname,password,sorigin,sdestination,date;
    int upassword,pwd;
    Spinner sp1,sp2;
    ImageButton b6,ib2;
    View v1;
    ArrayList al,al2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         b1 =(Button)findViewById(R.id.button4);
        b2 =(Button)findViewById(R.id.button);
        b3 =(Button)findViewById(R.id.button2);
        b4 =(Button)findViewById(R.id.button3);
        b3.setEnabled(bt);
        b2.setEnabled(bt);
        b4.setEnabled(bt);
    }


    void loginTask(View v)
    {
        LayoutInflater li =getLayoutInflater();
        v1=li.inflate(R.layout.login_layout,null,false);
        et1=(EditText)v1.findViewById(R.id.et1);

        et2=(EditText)v1.findViewById(R.id.et2);
        b5=(Button)v1.findViewById(R.id.b1);
        b6=(ImageButton)v1.findViewById(R.id.ib);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setView(v1);
        adb.setCancelable(false);
        final AlertDialog ad =  adb.create();
        ad.show();
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                  uname=  et1.getText().toString().trim();
                password=et2.getText().toString().trim();
                if(uname.isEmpty())
                {
                    et1.setError("Empty Username Not Allowed");
                }
                else if(password.isEmpty())
                {
                    et2.setError("Empty Password Not Allowed");
                }
                else
                {
                    int pwd=Integer.parseInt(password);
                    try {
                        MyDatabase md = new MyDatabase(MainActivity.this);
                        SQLiteDatabase sdb = md.getReadableDatabase();
                        String col[]={MyDatabase.COL1,MyDatabase.COL2};
                        Cursor c1 = sdb.query(MyDatabase.TABLENAME,col,null,null,null,null,null);
                        boolean check=c1.moveToNext();
                        if(check)
                        {
                            String name= c1.getString(0);
                            int  pass =c1.getInt(1);
                            if(name.equalsIgnoreCase(uname) && pass==pwd )
                            {
                                Toast.makeText(MainActivity.this," Login Successfull",Toast.LENGTH_SHORT).show();
                                b3.setEnabled(true);
                                b2.setEnabled(true);
                                b4.setEnabled(true);

                                ad.dismiss();
                            }
                            else
                            {
                                et1.setError("Username or Password Incorrect");
                                et1.setText("");
                                et2.setText("");
                                et1.requestFocus();
                            }
                        }
                         else {
                            SQLiteDatabase sdb2 = md.getReadableDatabase();
                            ContentValues cv = new ContentValues();
                            cv.put(MyDatabase.COL1, "Vikash");
                            cv.put(MyDatabase.COL2, 12345);
                            long ins = sdb2.insert(MyDatabase.TABLENAME, null, cv);
                            if(ins!=-1) {
                                Toast.makeText(MainActivity.this, " Uname/Password Created", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Error While Creating Uname/Password",Toast.LENGTH_SHORT).show();
                            }
                             }
                    }
                       catch(Exception e)
                       {
                           Log.e("inserting Table",""+e);
                           Toast.makeText(MainActivity.this," Exception",Toast.LENGTH_SHORT).show();

                       }
                }//main else logic
            }
        });
    }
    void bookTask(final View v)
    {
        LayoutInflater lf2 = getLayoutInflater();
       View v3=  lf2.inflate(R.layout.book_layout,null,false);
        b7=(Button)v3.findViewById(R.id.b7);
        b8=(Button)v3.findViewById(R.id.b8);
        sp1=(Spinner)v3.findViewById(R.id.s1);
        sp2=(Spinner)v3.findViewById(R.id.s2);
        b8 =(Button)v3.findViewById(R.id.b8);
        ib2 =(ImageButton)v3.findViewById(R.id.ib2);
        cvt=(TextView)v3.findViewById(R.id.cvt);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(v3);
        final AlertDialog ad = adb.create();
        ad.show();
        ArrayAdapter ad1 = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,places);
        sp1.setAdapter(ad1);
        al2=new ArrayList();
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position!=0)
                {
                    if(places[position]!=sdestination) {
                        sorigin = places[position];
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Origin and destination are same",Toast.LENGTH_SHORT).show();
                        bookTask(v);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter ad2 = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,places);
        sp2.setAdapter(ad2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position!=0)
                {
                    if(places[position]!=sorigin) {
                        sdestination = places[position];
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Origin and destination are same",Toast.LENGTH_SHORT).show();
                        bookTask(v);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                final int c_date = c.get(Calendar.DAY_OF_MONTH);
                int c_month = c.get(Calendar.MONTH);
                int c_year = c.get(Calendar.YEAR);
                // int c_hour =c.get(Calendar.HOUR_OF_DAY);
                //  int c_min = c.get(Calendar.MINUTE);
                DatePickerDialog dpb = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        cvt.setText(dayOfMonth + "/" + (month +1 ) + "/" + year);
                        date=dayOfMonth+"/"+(month+1)+"/"+year;
                    }
                }, c_year, c_month, c_date);
                DatePicker dp = dpb.getDatePicker();
               dp.setMaxDate(System.currentTimeMillis()+(45*24*60*3600*1000));
                dp.setMinDate(System.currentTimeMillis());
                dpb.show();
            }
            });

         b8.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 try{
                 Booking_Databse bd = new Booking_Databse(MainActivity.this);
                 SQLiteDatabase sldb = bd.getWritableDatabase();
                     ContentValues ctv = new ContentValues();
                 ctv.put(Booking_Databse.COL1,sorigin);
                 ctv.put(Booking_Databse.COL2,sdestination);
                 ctv.put(Booking_Databse.COL3,date);
                 long bdt =sldb.insert(Booking_Databse.TABLENAME2,null,ctv);
                     if(bdt!=-1)
                 {
                     Toast.makeText(MainActivity.this,"Details Successfully Saved",Toast.LENGTH_SHORT).show();
                     bookTask(v);
                 }}
                 catch(Exception e)
                 {

                      Log.e("Insert Booking Details",""+e)  ;
                     Toast.makeText(MainActivity.this,"Error whie inserting Booking details",Toast.LENGTH_SHORT).show();
                 }
             }
         });
}//BookTask

    public void viewTask(View v)
    {
        Intent i = new Intent(this,ViewActivity.class);
        startActivity(i);
    }

    public void logoutTask(View v)
    {

        Toast.makeText(MainActivity.this,"Logout Successfull",Toast.LENGTH_SHORT).show();
        b3.setEnabled(false);
        b2.setEnabled(false);
        b4.setEnabled(false);

    }

}//MainActivity
