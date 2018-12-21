package com.classroomproject.smartclassroom;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherLoginActivity extends Activity {

    ProgressDialog pd ;
    TextInputLayout til_id,til_password;
    EditText et_id,et_password;
    Button login;
    String teacher_id,teacher_password,teacher_name,teacher_subject,teacher_email;
    DatabaseReference dr2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        et_id = findViewById(R.id.et_teacher_login_id);
        et_password = findViewById(R.id.et_teacher_login_password);
        til_id = findViewById(R.id.til_teacher_login_id);
        til_password = findViewById(R.id.til_teacher_login_Password);
        login = findViewById(R.id.teacher_login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                validateUser();
            }
        });

    }

    public void validateUser()
    {
        teacher_id = et_id.getText().toString().trim();
        teacher_password = et_password.getText().toString().trim();


        if(teacher_id.isEmpty())
        {
            til_password.setErrorEnabled(false);
            til_id.setError("Empty!");
            et_id.requestFocus();
        }
        else
        {
            til_id.setErrorEnabled(false);
            if(teacher_password.isEmpty())
            {
                til_password.setError("Empty!");
                et_password.requestFocus();
            }
            else
            {
                       //Validating Teacher User ID from Firebase

                pd = new ProgressDialog(TeacherLoginActivity.this,R.style.MyAlertDialogStyle);//Applying Progressdialog style
                pd.setMessage("Please Wait");
                pd.setCancelable(false);
                pd.show();

               FirebaseDatabase fd =  FirebaseDatabase.getInstance();
               DatabaseReference dr =  fd.getReference("Teacher_Registration");
               dr2 =  dr.child(teacher_id).child("Personal_Details");
               dr2.addListenerForSingleValueEvent(new ValueEventListener()
               {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot)
                   {
                       if(dataSnapshot.getChildrenCount()>0)
                       {
                           til_id.setErrorEnabled(false);
                           et_id.setText("");
                           et_password.setText("");
                          MyTeacherRegistartionHandler ref =  dataSnapshot.getValue(MyTeacherRegistartionHandler.class);
                          String password  = ref.getTeacher_password();
                          teacher_name = ref.getTeacher_name();
                          teacher_subject=ref.getTeacher_subject();
                          teacher_email=ref.getTeacher_email();

                             //If password is valid
                          if(teacher_password.equals(password))
                          {

                              //Sending teacher Username to Home Activity
                              pd.dismiss();
                              Intent intent = new Intent(TeacherLoginActivity.this,MyTeacherHomeActivity.class);
                              intent.putExtra("KEY1",teacher_name);
                              intent.putExtra("KEY2",teacher_id);
                              intent.putExtra("KEY3",teacher_subject);
                              intent.putExtra("KEY4",teacher_email);
                              startActivity(intent);
                              finish();

                          }

                          else
                          {
                              til_id.setErrorEnabled(false);
                              til_password.setErrorEnabled(false);
                              pd.dismiss();
                              Toast.makeText(TeacherLoginActivity.this, "Invalid Login Credential ", Toast.LENGTH_SHORT).show();
                              et_id.setText("");
                              et_password.setText("");
                              et_id.requestFocus();
                          }


                       }
                       else
                       {
                           pd.dismiss();
                           et_id.setText("");
                           et_password.setText("");
                           et_id.requestFocus();
                           til_id.setError("Invalid ID");
                           til_password.setErrorEnabled(false);
                       }

                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError)
                   {
                       Toast.makeText(TeacherLoginActivity.this, "Database Error ", Toast.LENGTH_SHORT).show();

                   }
               });

            }//end of validation

        }

    }//validateUser()


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            finish();
            startActivity(new Intent(this,TeacherRegistrationActivity.class));
        }


        return super.onKeyDown(keyCode, event);
    }
}
