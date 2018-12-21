package com.example.android.cw48;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button bt;
   FragmentTransaction ft;
   FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv1);
        bt=findViewById(R.id.bt1);
        fm =  getSupportFragmentManager();
        tv.setText("Fragment Count in BackStack = "+fm.getBackStackEntryCount());
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged()
            {

                tv.setText("Fragment Count in BackStack = "+fm.getBackStackEntryCount());
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               processFragemnt();

            }
        });
    }

    public void processFragemnt()
    {
        Fragment fg;
        Toast.makeText(this, ""+fm.getBackStackEntryCount(), Toast.LENGTH_SHORT).show();
        switch (fm.getBackStackEntryCount())
         {
             case 0 :
           {
                fg = new Fragment1();
                break;
           }
             case 1 :
             {
                 fg = new Fragment2();
                 break;
             }
             case 2 :
             {
                 fg = new Fragment3();
                 break;
             }
             default:
                 fg= new Fragment1();
                 break;
        }


       ft=fm.beginTransaction();
       ft.add(R.id.fl,fg,null);
       ft.addToBackStack(null);
       ft.commit();



    }
}
