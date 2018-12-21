package com.classroomproject.smartclassroom;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;

public class MyTeacherHomeActivity extends AppCompatActivity {

    String teacher_name,teacher_id,teacher_email,teacher_subject;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_teacher_home);

        tabLayout = findViewById(R.id.teacher_home_tl);
        viewPager = findViewById(R.id.teacher_home_vp);

       FragmentManager fragmentManager = getSupportFragmentManager();
       PagerAdapter pagerAdapter = new MyTeacherPageAdapter(this,fragmentManager);
       viewPager.setAdapter(pagerAdapter);
       tabLayout.setupWithViewPager(viewPager);



       //Get intent data from Login Activity

        Intent intent = getIntent();
         Bundle bundle = intent.getExtras();
        teacher_name =  bundle.getString("KEY1");
        teacher_id =  bundle.getString("KEY2");
        teacher_subject =  bundle.getString("KEY3");
        teacher_email=bundle.getString("KEY4");

    }

    public ArrayList  teacherLoginDetails()
    {
        ArrayList al = new ArrayList();
        al.add(teacher_name);
        al.add(teacher_id);
        al.add(teacher_subject);
        al.add(teacher_email);
        return al;

    }

    //refresh whole activity

    public void refreshActivity()
    {
        finish();
        startActivity(getIntent());
    }



    //Showing dialog when back key is pressed

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
            builder.setMessage("Exit");
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Are You Sure ?");

               //Changing the default font color of Negative button in Alertdialog
            builder.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    dialog.dismiss();
                }
            });

            builder.setPositiveButton((Html.fromHtml("<font color='#FF7F27'>Yes</font>")), new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                    finish();
                    System.exit(0);
                }
            });

            builder.create().show();

        }

        return super.onKeyDown(keyCode, event);

    }

    //Reload Current Fragment


}
