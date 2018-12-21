package com.classroomproject.smartclassroom;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentLoginActivity extends Activity {

    String student_id,student_password,retrieved_id,retrieved_name,retrieved_email,retrieved_password;
    EditText student_id_et,student_password_et;
    TextInputLayout student_id_til,student_password_til;
    ProgressDialog progressDialog;
    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

       student_id_et = findViewById(R.id.et_student_login_id);
       student_password_et =  findViewById(R.id.et_student_login_password);
       student_id_til =  findViewById(R.id.til_student_login_id);
       student_password_til = findViewById(R.id.til_student_login_Password);

        Button login = findViewById(R.id.student_login_button);

            //When login button is clicked

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               student_id =  student_id_et.getText().toString().trim();
               student_password =  student_password_et.getText().toString().trim();

                if(student_id.isEmpty())
                {
                    student_id_til.setErrorEnabled(false);
                    student_password_til.setErrorEnabled(false);

                    student_id_til.setError("Empty!");
                    student_id_et.requestFocus();
                }
                else
                {
                    student_id_til.setErrorEnabled(false);
                    student_password_til.setErrorEnabled(false);

                    if(student_password.isEmpty())
                    {
                        student_password_til.setError("Empty!");
                        student_password_et.requestFocus();
                    }
                    else
                        {
                            //When fields are not empty

                              validateStudent();

                        }//end of else
                }
            }
        });
    }

      //Checks whether student_id is valid or not

    public void validateStudent()
    {
        progressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

      FirebaseDatabase firebaseDatabase =   FirebaseDatabase.getInstance();
      DatabaseReference dr1 = firebaseDatabase.getReference("Student_Details");
     DatabaseReference dr2 =  dr1.child(student_id).child("Personal_Details");
     dr2.addListenerForSingleValueEvent(new ValueEventListener()
     {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot)
         {

             if(dataSnapshot.getChildrenCount()>0)
             {

                 student_id_et.setText("");
                 student_password_et.setText("");
                 student_id_til.setErrorEnabled(false);
                 student_password_til.setErrorEnabled(false);

               MyStudentRegistrationHandler obj1 =   dataSnapshot.getValue(MyStudentRegistrationHandler.class);

                        retrieved_id =   obj1.getStudent_id();
                        retrieved_name = obj1.getStudent_name();
                        retrieved_email =  obj1.getStudent_email();
                        retrieved_password = obj1.getStudent_password();

                        //Check Whether Password is valid or not

                        if(retrieved_password.equals(student_password))
                        {

                            Toast.makeText(StudentLoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(StudentLoginActivity.this,MyStudentHomeActivity.class);
                            i.putExtra("K1",retrieved_id);
                            i.putExtra("K2",retrieved_name);
                            i.putExtra("K3",retrieved_email);
                            finish();
                            startActivity(i);

                        }
                        else
                        {
                            view1 = getWindow().getDecorView().getRootView();
                            progressDialog.dismiss();
                            Snackbar.make(view1,"Invalid Login Credential",Snackbar.LENGTH_SHORT).show();
                            student_id_et.setText("");
                            student_password_et.setText("");
                            student_id_et.requestFocus();

                        }

             }
              //When Student Id not found in the firebase
             else
             {
                 progressDialog.dismiss();
                student_id_til.setError("Id Not Found");
                student_id_et.setText("");
                student_password_et.setText("");
                student_id_et.requestFocus();
             }

         }

         @Override
         public void onCancelled(DatabaseError databaseError)
         {

             view1 = getWindow().getDecorView().getRootView();
             Snackbar.make(view1,"Database Error",Snackbar.LENGTH_SHORT).show();
         }

     });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
              finish();
              startActivity(new Intent(this,StudentRegistrationActivity.class));
        }

        return super.onKeyDown(keyCode, event);

    }
}
