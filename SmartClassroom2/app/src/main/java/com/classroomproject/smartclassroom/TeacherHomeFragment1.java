package com.classroomproject.smartclassroom;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherHomeFragment1 extends Fragment {
 String t_name,t_id,t_sub;
Context context;
MyTeacherHomeActivity ref;
TextView teacher_name_tv,date_view_tv,logout_tv;
Button date_picker,save_details;
EditText chapter_name_et,topic_name_et;
TextInputLayout chapter_name_til,topic_name_til;
String chapter_name,topic_name,date_selected;
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
        View view = inflater.inflate(R.layout.fragment_teacher_home_fragment1,container,false);
        teacher_name_tv = view.findViewById(R.id.welcomemsg_tv);
        date_view_tv =    view.findViewById(R.id.teacher_dateselector_tv);
        logout_tv =       view.findViewById(R.id.teacher_logout_tv);
        chapter_name_et = view.findViewById(R.id.teacher_chapter_et);
        chapter_name_til= view.findViewById(R.id.teacher_chapter_til);
        topic_name_et =   view.findViewById(R.id.teacher_topics_et);
        topic_name_til =  view.findViewById(R.id.teacher_topics_til);
        date_picker =     view.findViewById(R.id.teacher_dateselector);
        save_details =    view.findViewById(R.id.teacher_submitdailyreport);

        teacher_name_tv.setText("Hello");



        //Getting Intent value from login activity

           /** ALSO VALID

       Intent intent =  getActivity().getIntent();
       Bundle bundle =  intent.getExtras();
       t_name =bundle.getString("KEY1");
       t_id =  bundle.getString("KEY2");
        t_id =  bundle.getString("KEY2");

              */

           //Getting intent values from MyTeacherHomeActivity list

        ref =  (MyTeacherHomeActivity)context;
       ArrayList arrayList = ref.teacherLoginDetails();
        Object o = arrayList.get(0);
        Object o1 =arrayList.get(1);
        Object o2 = arrayList.get(2);
        t_name =  (String)o;
        t_sub = (String)o2;
        t_id = (String)o1;
       teacher_name_tv.setText("Welcome "+t_name);
        Log.e("name",""+t_sub);
        Log.e("id",""+t_id);

        date_picker.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
                 datePickerActivity();
           }
       });

       date_view_tv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               datePickerActivity();

           }
       });



       save_details.setOnClickListener(new View.OnClickListener()
       {



           @Override
           public void onClick(View v)
           {


           chapter_name =  chapter_name_et.getText().toString().trim();
           topic_name =     topic_name_et.getText().toString().trim();
           date_selected = date_view_tv.getText().toString().trim();
           Log.e("dateselect",""+date_selected);

           if(chapter_name.isEmpty())
           {
               chapter_name_til.setError("Empty !");
               chapter_name_et.requestFocus();
           }
           else
           {
               chapter_name_til.setErrorEnabled(false);
               topic_name_til.setErrorEnabled(false);

               if(topic_name.isEmpty())
               {
                   chapter_name_til.setError("Empty!");
                   chapter_name_et.requestFocus();

               }
               else
               {
                   chapter_name_til.setErrorEnabled(false);
                   topic_name_til.setErrorEnabled(false);
                   if(date_selected.isEmpty())
                   {
                       //Showing Snackbar in Fragment
                     Snackbar snackbar =  Snackbar.make(getActivity().findViewById(android.R.id.content),"Date is Empty !",Snackbar.LENGTH_SHORT);
                     snackbar.show();
                   }
                   else
                   {

                       //Saving Daily routine Details in the firebase
;
                       SaveDailyRoutineActivity sdra = new SaveDailyRoutineActivity(date_selected,chapter_name,topic_name);
                       FirebaseDatabase firebaseDatabase  =  FirebaseDatabase.getInstance();
                       DatabaseReference dr =  firebaseDatabase.getReference("Teacher_Registration");
                       dr.child(t_id).child("Subject_Name -"+t_sub).child(date_selected).setValue(sdra);
                       Toast.makeText(ref, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                       chapter_name_et.setText("");
                       topic_name_et.setText("");
                       date_view_tv.setText("");
                       date_view_tv.setHint("dd//mm//yyyy");

                          //Saving data in firebase where subject name is parent

                       SaveSubjectHandler saveSubjectHandlerobj = new SaveSubjectHandler(t_sub,date_selected,chapter_name,topic_name);
                       FirebaseDatabase.getInstance().getReference("Subject_name").child(t_sub).child(date_selected).setValue(saveSubjectHandlerobj);

                       //Saving data in firebase where Date is parent

                       SubjectListHandler subjectListHandlerobj = new SubjectListHandler(t_sub);
                       FirebaseDatabase.getInstance().getReference("Subject_list").child(t_sub).setValue(subjectListHandlerobj);


                       ref.refreshActivity();




                }

               }



           }

           }//onClick()
       });//end of Savedetails



            //Logout from Teacher_Home_fragment

        logout_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
             Intent i = new Intent(getActivity(),TeacherRegistrationActivity.class);
             startActivity(i);
            }
        });


        return  view;
    }


    //Implementing Date Picker Dialog

    public void datePickerActivity()
    {
        Calendar calendar =  Calendar.getInstance();
        int cur_year = calendar.get(Calendar.YEAR);
        int cur_month =  calendar.get(Calendar.MONTH);
        int cur_day = (calendar.get(Calendar.DAY_OF_MONTH)+1);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                date_view_tv.setText(dayOfMonth+"-"+(month+1)+"-"+year);
            }
        },cur_year,cur_month,cur_day);

        DatePicker datePicker  = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }



}
