package com.example.android.cw44;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment2 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_my_fragment2, container, false);
        final Context ct = v.getContext();


        Button home_button =  v.findViewById(R.id.b1);
        Button admin_button =  v.findViewById(R.id.b2);
        Button employee_button =  v.findViewById(R.id.b3);
        Button track_button =  v.findViewById(R.id.b4);
        Button contact_button =  v.findViewById(R.id.b5);

        admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyAdminFragment af = new MyAdminFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft  = fm.beginTransaction();
                ft.add(R.id.fg3,af,"AdminLoginPage");

                ft.commit();

            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyFragment3 mf3 = new MyFragment3();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fg3,mf3,"HomePage");

                ft.commit();
            }
        });

        employee_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               MyEmpFragmentt mf = new MyEmpFragmentt();
               FragmentManager fm =    getFragmentManager();
               FragmentTransaction ft = fm.beginTransaction();
               ft.add(R.id.fg3,mf,"AdminLoginPage");

               ft.commit();

            }
        });


        track_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

              try {
                  Uri ref = Uri.parse("geo:0,0?z=15");
                  Intent i = new Intent(Intent.ACTION_VIEW, ref);
                  i.setPackage("com.google.android.apps.maps");
                  startActivity(i);
              }
              catch(ActivityNotFoundException e)
              {
                  Toast.makeText(ct, R.string.ANF, Toast.LENGTH_SHORT).show();
              }
            }
        });

        contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                WelcomeContact wc = new WelcomeContact();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fg3,wc,"ContactUsPage");
                ft.commit();
            }
        });






        return  v;
    }

}
