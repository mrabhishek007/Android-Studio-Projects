package com.classroomproject.smartclassroom;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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

import javax.security.auth.Subject;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentHomeFragment1 extends Fragment
{

     String chapter_name,subject_name,subject_date,topic_name;
    String sub_id,selected_date;
    Context context;
    ProgressDialog progressDialog;
    EditText search_sub_et;
    TextInputLayout search_sub_til;
    View v1;
    TextView welocme_message_tv,logout_tv,date_selector_tv;
    MyStudentHomeActivity object1 ;
    Button submit,date_picker;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context =context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v1 = inflater.inflate(R.layout.fragment_student_home_fragment1, container, false);
        welocme_message_tv = v1.findViewById(R.id.student_welcomemsg_tv);



        //getting intent data from MyStudentHomeActivity

       object1 =  (MyStudentHomeActivity)context;
       ArrayList al1 = object1.studentLoginData();
       String student_name =  (String)al1.get(1);
       welocme_message_tv.setText("Welcome "+student_name);

      search_sub_et =  v1.findViewById(R.id.student_sub_search_et);
      search_sub_til =  v1.findViewById(R.id.student_sub_search_til);
      submit =   v1.findViewById(R.id.student_finddailyreport);
      logout_tv =   v1.findViewById(R.id.student_logout_tv);
      date_picker = v1.findViewById(R.id.student_dateselector);
      date_selector_tv =  v1.findViewById(R.id.student_dateselector_tv);


        date_selector_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                dateSelector();
            }
        });

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dateSelector();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
            sub_id =  search_sub_et.getText().toString().trim();
            selected_date =  date_selector_tv.getText().toString().trim();

            if(sub_id.isEmpty())
            {
                search_sub_til.setError("Empty");
                search_sub_et.requestFocus();
            }
            else
            {
                search_sub_til.setErrorEnabled(false);
                if(selected_date.isEmpty())
                {
                    //Showing Snackbar in Fragment
                    Snackbar snackbar =  Snackbar.make(getActivity().findViewById(android.R.id.content),"Date is Empty !",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else
                {
                    /**
                        //Check Whether subject)id is valid or not

                      DatabaseReference dr1 =   FirebaseDatabase.getInstance().getReference("Subject_name").child(sub_id);
                      dr1.addListenerForSingleValueEvent(new ValueEventListener() {
                          @Override
                          public void onDataChange(DataSnapshot dataSnapshot)
                          {
                              if(dataSnapshot.getChildrenCount()==0)
                              {

                                  Toast.makeText(context, "Subject not found", Toast.LENGTH_SHORT).show();
                              }

                          }

                          @Override
                          public void onCancelled(DatabaseError databaseError)
                          {

                          }
                      });

                     */

                    //Checks whether date is available on firebase or not

                    progressDialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
                    progressDialog.setMessage("Searching...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    DatabaseReference dr2 = FirebaseDatabase.getInstance().getReference("Subject_name").child(sub_id).child(selected_date);
                    dr2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {
                            if(dataSnapshot.getChildrenCount()>0)
                            {
                                SaveSubjectHandler ssh =   dataSnapshot.getValue(SaveSubjectHandler.class);
                                chapter_name = ssh.getChapter_name();
                                subject_date = ssh.getSubject_date();
                                topic_name = ssh.getTopic_name();
                                subject_name = ssh.getSubject_name();
                                progressDialog.dismiss();
                                Intent i = new Intent(getContext(),ViewSingleLecture.class);
                                i.putExtra("L1",subject_name);
                                i.putExtra("L2",subject_date);
                                i.putExtra("L3",topic_name);
                                i.putExtra("L4",chapter_name);
                                startActivity(i);


                            }

                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                                search_sub_et.setText("");
                                date_selector_tv.setText("");

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Database Error !", Toast.LENGTH_SHORT).show();
                            search_sub_et.setText("");
                            date_selector_tv.setText("");

                        }
                    });
                }
              }
             }
        });


        logout_tv.setOnClickListener(
                new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();

                startActivity(new Intent(getActivity(),StudentLoginActivity.class));
                search_sub_et.setText("");
            }
        });


        return v1;
    }


    public void dateSelector()
    {
        Calendar calendar =  Calendar.getInstance();
        int cur_year = calendar.get(Calendar.YEAR);
        int cur_month =  calendar.get(Calendar.MONTH);
        int cur_day = (calendar.get(Calendar.DAY_OF_MONTH)+1);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,  AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                date_selector_tv.setText(dayOfMonth+"-"+(month+1)+"-"+year);
            }
        },cur_year,cur_month,cur_day);

        DatePicker datePicker  = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }



}
