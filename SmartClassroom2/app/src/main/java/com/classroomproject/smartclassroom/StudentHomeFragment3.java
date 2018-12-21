package com.classroomproject.smartclassroom;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class StudentHomeFragment3 extends Fragment
{

   View v1;

   MyStudentHomeActivity mshobj;
   TextView logout,welcome_msg;
   EditText complaint_text_et,subject_name_et;
   TextInputLayout complaint_text_til,subject_name_til;
   Button submit;
   Context context;
   String getstudent_id,getstudent_email,getstudent_name;
   String comp_sub_name,comp_text;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);

        v1 =inflater.inflate(R.layout.fragment_student_home_fragment3, container, false);

        logout = v1.findViewById(R.id.student_complaint_tv);
        welcome_msg = v1.findViewById(R.id.student_welcomemsg3_tv);
        complaint_text_et=v1.findViewById(R.id.student_complaintenter__et);
        complaint_text_til = v1.findViewById(R.id.student_complaintsenter_til);
        subject_name_et = v1.findViewById(R.id.student_complaintsub_search_et);
        subject_name_til = v1.findViewById(R.id.student_complaintsub_search_til);
        submit = v1.findViewById(R.id.student_submitcomplaint);


        mshobj = (MyStudentHomeActivity)context;

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
                startActivity(new Intent(getActivity(),StudentLoginActivity.class));
            }
        });

        //Getting Student Login Info from MyStudentHomeActivity

        ArrayList arrayList  = mshobj.studentLoginData();
        getstudent_id =(String) arrayList.get(0);
        getstudent_name =(String) arrayList.get(1);
        getstudent_email =(String) arrayList.get(2);
        welcome_msg.setText("Welcome "+getstudent_name);



        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              comp_sub_name =  subject_name_et.getText().toString().trim();
              comp_text  = complaint_text_et.getText().toString().trim();

                if(comp_sub_name.isEmpty())
                {
                    subject_name_til.setError("Empty");
                    subject_name_et.requestFocus();
                }
                else
                {
                    subject_name_til.setErrorEnabled(false);
                    complaint_text_til.setErrorEnabled(false);

                    if(comp_text.isEmpty())
                    {
                        complaint_text_til.setError("Empty");
                        complaint_text_et.requestFocus();
                    }
                    else
                    {
                        //When Fields are not empty
                        subject_name_til.setErrorEnabled(false);
                        complaint_text_til.setErrorEnabled(false);


                           //Checks whether selected subject is valid or not

                       DatabaseReference dr1 =  FirebaseDatabase.getInstance().getReference("Subject_list").child(comp_sub_name);
                       dr1.addListenerForSingleValueEvent(new ValueEventListener()
                       {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot)
                           {
                              if(dataSnapshot.getChildrenCount()>0)
                              {

                                  //Save Complaints into the database

                                  saveComplaints();

                              }
                              else
                              {
                                  Toast.makeText(mshobj, "Subject Not found", Toast.LENGTH_SHORT).show();
                                  complaint_text_et.setText("");
                                  subject_name_et.setText("");
                                  subject_name_et.requestFocus();
                              }
                           }

                           @Override
                           public void onCancelled(DatabaseError databaseError)
                           {

                           }
                       });


                    }
                }
            }
        });

       return  v1;

    }


    public void saveComplaints()

    {

       Calendar calendar =  Calendar.getInstance();
         int c_year =  calendar.get(Calendar.YEAR);
         int c_month =  (calendar.get(Calendar.MONTH))+1;
         int c_day =  calendar.get(Calendar.DAY_OF_MONTH);
         int c_hour =  calendar.get(Calendar.HOUR_OF_DAY);
         int c_min =  calendar.get(Calendar.MINUTE);
         int c_ms =  calendar.get(Calendar.MILLISECOND);

         String time = (c_day+"-"+c_month+"-"+c_year+","+c_hour+":"+c_min+":"+c_ms );

         StudentComplaintsHandler objc  =new StudentComplaintsHandler(comp_sub_name,time,comp_text);

         DatabaseReference dr2 =  FirebaseDatabase.getInstance().getReference("Complaints").child(comp_sub_name).child(time);
         dr2.setValue(objc);

         Toast.makeText(mshobj, " Complaint Submitted Successfully ", Toast.LENGTH_SHORT).show();
        complaint_text_et.setText("");
        subject_name_et.setText("");
        subject_name_et.requestFocus();


    }








}
