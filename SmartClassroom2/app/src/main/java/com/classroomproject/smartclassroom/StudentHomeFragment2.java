package com.classroomproject.smartclassroom;

import android.app.ProgressDialog;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentHomeFragment2 extends Fragment
{
    MyStudentHomeActivity mshobj;
    ProgressDialog progressDialog;
    View v1;
    Context context;
    EditText search_sub_et;
    TextInputLayout search_sub_til;
    TextView welcome_message_tv,logout_tv;
    Button search;
    String subject_id;
    String getstudent_id,getstudent_email,getstudent_name;



    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

       v1 = inflater.inflate(R.layout.fragment_student_home_fragment2, container, false);
       welcome_message_tv =  v1.findViewById(R.id.student1_welcomemsg_tv);
       logout_tv =  v1.findViewById(R.id.studentxx_logout_tv);
       search_sub_et = v1.findViewById(R.id.student1_sub_search_et);
       search_sub_til =  v1.findViewById(R.id.student1_sub_search_til);
       search = v1.findViewById(R.id.student1_finddailyreport);
       mshobj =  (MyStudentHomeActivity)context;


     ArrayList arrayList  = mshobj.studentLoginData();
     getstudent_id =(String) arrayList.get(0);
     getstudent_name =(String) arrayList.get(1);
     getstudent_email =(String) arrayList.get(2);

     welcome_message_tv.setText("Welcome "+getstudent_name);




        search.setOnClickListener(new View.OnClickListener()
      {
          @Override
          public void onClick(View v)
          {
              subject_id =  search_sub_et.getText().toString().trim();

              if(subject_id.isEmpty())
              {
                  search_sub_til.setError("Empty");
                  search_sub_et.requestFocus();
              }
              else
              {
                  search_sub_til.setErrorEnabled(false);
                    validateStudentId();
              }
          }
      });

        logout_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
                startActivity(new Intent(getActivity(),StudentLoginActivity.class));
            }
        });



       return  v1;
    }

    public void validateStudentId()
    {
        progressDialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Searching...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        DatabaseReference dr1 =   FirebaseDatabase.getInstance().getReference("Subject_name").child(subject_id);
        dr1.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.getChildrenCount()==0)
                {

                    Toast.makeText(context, "Subject not found", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    search_sub_et.setText("");
                }
                else
                {

                    Toast.makeText(context, "Found", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent i = new Intent(getActivity(),ViewListSubjectsActiivty.class);
                    i.putExtra("SUB",subject_id);
                    startActivity(i);
                     search_sub_et.setText("");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

}
