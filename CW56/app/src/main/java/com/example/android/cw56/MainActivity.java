//Material Design(Tab Layout)
package com.example.android.cw56;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    TabLayout tl;
    FragmentManager fm;
    Fragment ref ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tl = findViewById(R.id.tl);
        fm =  getSupportFragmentManager();

       tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab)
           {

              int tab_item_position =  tab.getPosition();
               switch (tab_item_position)
               {
                   case 0:
                   {
                        ref = new FragmentRed();
                       break;
                   }
                   case 1:
                   {
                       ref =new FragmentGreen();
                       break;
                   }
                   case 2:
                   {
                       ref = new FragmentBlue();
                       break;
                   }
                   case 3:
                   {
                       ref = new FragmentVoilet();
                       break;
                   }
                   case 4:
                   {
                       ref = new FragmentOrange();
                       break;

                   }
                   case 5:
                   {
                       ref =new FragmentBrown();
                       break;
                   }
               }
               FragmentTransaction  ft = fm.beginTransaction();
               ft.add(R.id.fg1,ref,null);
               ft.commit();
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab)
           {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });

    }
}
