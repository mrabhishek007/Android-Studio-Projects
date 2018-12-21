package com.example.android.cwp16;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    Button b,b1;
    int set=-1;
    ArrayList<String> al = new ArrayList<String>();;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b= (Button) findViewById(R.id.button);
        b1= (Button) findViewById(R.id.button2);
        tv =(TextView)findViewById(R.id.tv);

    }

    void view1(View v)//Working with traditional single choice list
    {
        String color[]={"GREY","Green","YELLOW"};
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setItems(color, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                tv.setText("");

                switch (which)
                {
                    case 0:
                        b.setBackgroundColor(Color.GRAY);
                        break;
                    case 1 :
                        b.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        b.setBackgroundColor(Color.YELLOW);
                        break;
                }
            }
        });
        adb.setTitle("COLORS");
        adb.setIcon(R.drawable.camera);
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();

    }

    void view2(View v)//working with Presistance Single Choice List
    {String color[]={"GRAY","GREEN","YELLOW"};
        AlertDialog.Builder adb =  new AlertDialog.Builder(this);
        adb.setTitle("Colors");
        adb.setIcon(R.drawable.camera);
        adb.setCancelable(false);
        adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.setSingleChoiceItems(color, set, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                tv.setText("");
                set=which;
                switch (which)
                {
                    case 0:
                        b1.setBackgroundColor(Color.GRAY);
                        break;
                    case 1 :
                        b1.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        b1.setBackgroundColor(Color.YELLOW);
                        break;

                }
                dialog.dismiss();
            }
        });
        adb.create().show();
    }


    void view3(View v)//Working with Traditional Multi Choice List
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final String[] items =getResources().getStringArray(R.array.select_color);
        adb.setTitle("COLORS");
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.setIcon(R.drawable.camera);
        adb.setMultiChoiceItems(R.array.select_color, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked)
            {
                if(isChecked)
                {
                    // If the user checked the item, add it to the selected item list
                    al.add(items[which]);
                }
                else if(al.contains(items[which]))
                {
                    //If the user already checked the item, remove it
                    al.remove(which);
                }
            }
        });
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String sle="";
                for(String ms : al)
                {
                    sle  = sle + "\n"+ms;
                }
                tv.setText(sle);

            }
        });
        adb.create().show();
    }
    void view4(View v)
    {
        LayoutInflater lf =getLayoutInflater();
        View v1=lf.inflate(R.layout.login,null,false);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(v1);
        adb.create().show();




    }






}
