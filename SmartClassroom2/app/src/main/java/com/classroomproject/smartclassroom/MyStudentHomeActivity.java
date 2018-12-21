package com.classroomproject.smartclassroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MyStudentHomeActivity extends AppCompatActivity
{

   public  ArrayList arrayList = new ArrayList();
    String student_id,student_name,student_email;
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList ax = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_student_home);

        tabLayout = findViewById(R.id.my_student_home_tl);
        viewPager = findViewById(R.id.my_student_home_vp);


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        MyStudentPageAdapter mspa = new MyStudentPageAdapter(this, fragmentManager);

        viewPager.setAdapter(mspa);
        tabLayout.setupWithViewPager(viewPager);

        //Geting Student Login data from LoginActivity()

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        student_id =   bundle.getString("K1");
        student_name = bundle.getString("K2");
        student_email= bundle.getString("K3");

    }

    /**

           //getting the subject list from Firebase in Teacher_Registration

      public ArrayList getSubjectsList()
      {

         FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
         DatabaseReference dr1 = firebaseDatabase.getReference("Teacher_Registration");

         dr1.addListenerForSingleValueEvent(new ValueEventListener()
         {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot)
             {
                long count =  dataSnapshot.getChildrenCount();

                if(count>0)
                {
                    for( DataSnapshot childDataSnapshot :dataSnapshot.getChildren())
                    {

                        String teacher_id = childDataSnapshot.getKey();

                             //Getting teacher_sub data

                        DatabaseReference dr2 = FirebaseDatabase.getInstance().getReference("Teacher_Registration").child(teacher_id).child("Personal_Details");
                        dr2.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                            MyTeacherRegistartionHandler objhandler =  dataSnapshot.getValue(MyTeacherRegistartionHandler.class);
                            String sub = objhandler.getTeacher_subject();
                            Log.e(">>>",sub);
                            arrayList.add(sub);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError)
                            {
                                Toast.makeText(MyStudentHomeActivity.this, "Database Error !", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }//end of loop
                }
             }
             @Override
             public void onCancelled(DatabaseError databaseError)
             {

                 Toast.makeText(MyStudentHomeActivity.this, "Database Error !", Toast.LENGTH_SHORT).show();
             }
         });

         Log.e("---",""+arrayList.size());
        return  arrayList;
      }

      */

        //Storing student_login data into Arraylist,so fragments can retrieve the data

       public ArrayList studentLoginData()
        {
           ArrayList arrayList = new ArrayList();

           arrayList.add(student_id);
           arrayList.add(student_name);
           arrayList.add(student_email);
           return arrayList;
       }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this,R.style.MyDialogTheme);

            builder.setMessage("Exit");
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Are You Sure ?");


            //Changing the default font color of Negative button in Alertdialog

            builder.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });

            builder.setPositiveButton((Html.fromHtml("<font color='#FF7F27'>Yes</font>")), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                    System.exit(0);
                }
            });

            builder.create().show();
        }
            return super.onKeyDown(keyCode, event);

    }

    //refresh whole activity

    public void refreshActivity()
    {
        finish();
        startActivity(getIntent());
    }






}


