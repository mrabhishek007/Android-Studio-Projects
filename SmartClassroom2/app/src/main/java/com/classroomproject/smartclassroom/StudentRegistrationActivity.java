package com.classroomproject.smartclassroom;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class StudentRegistrationActivity extends Activity {

    String student_name,student_id,student_email,student_password;
    TextInputLayout student_name_til,student_id_til,student_email_til,student_password_til;
    EditText student_name_et,student_id_et,student_email_et,student_password_et;
    Button register;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        student_name_et = findViewById(R.id.student_registration_name);
        student_id_et = findViewById(R.id.student_registration_id);
        student_email_et = findViewById(R.id.student_registration_email);
        student_password_et = findViewById(R.id.student_registartion_password);

        student_name_til = findViewById(R.id.til_student_registration_name);
        student_id_til = findViewById(R.id.til_student_registration_id);
        student_email_til = findViewById(R.id.til_student_registration_email);
        student_password_til = findViewById(R.id.til_student_registration_Password);

        register = findViewById(R.id.student_register_button);

        login = findViewById(R.id.student_login_tv);


                  // register Student details to Firebase

           register.setOnClickListener(new View.OnClickListener()
           {
               @Override
               public void onClick(View v)
               {
                   student_name = student_name_et.getText().toString().trim();
                   student_id = student_id_et.getText().toString().trim();
                   student_email = student_email_et.getText().toString().trim();
                   student_password = student_password_et.getText().toString().trim();

                   if(student_name.isEmpty())
                   {

                       student_email_til.setErrorEnabled(false);
                       student_name_til.setErrorEnabled(false);
                       student_id_til.setErrorEnabled(false);
                       student_password_til.setErrorEnabled(false);
                       student_name_til.setError("Empty!");
                       student_name_et.requestFocus();
                   }
                   else
                   {
                       student_email_til.setErrorEnabled(false);
                       student_name_til.setErrorEnabled(false);
                       student_id_til.setErrorEnabled(false);
                       student_password_til.setErrorEnabled(false);
                       if(student_id.isEmpty())
                       {
                           student_id_til.setError("Empty!");
                           student_id_et.requestFocus();
                       }
                       else
                       {
                           student_email_til.setErrorEnabled(false);
                           student_name_til.setErrorEnabled(false);
                           student_id_til.setErrorEnabled(false);
                           student_password_til.setErrorEnabled(false);
                           if(student_email.isEmpty())
                           {
                               student_email_til.setError("Empty!");
                               student_email_et.requestFocus();
                           }
                           else
                           {
                               student_email_til.setErrorEnabled(false);
                               student_name_til.setErrorEnabled(false);
                               student_id_til.setErrorEnabled(false);
                               student_password_til.setErrorEnabled(false);
                               if(student_email.isEmpty())
                               {
                                student_email_til.setError("Empty!");
                                student_email_et.requestFocus();
                               }
                               else
                               {
                                   student_email_til.setErrorEnabled(false);
                                   student_name_til.setErrorEnabled(false);
                                   student_id_til.setErrorEnabled(false);
                                   student_password_til.setErrorEnabled(false);
                                   if(student_password.isEmpty())
                                   {
                                       student_password_til.setError("Empty!");
                                       student_password_et.requestFocus();
                                   }
                                   else
                                   {
                                       //When  Fields are not empty
                                       student_email_til.setErrorEnabled(false);
                                       student_name_til.setErrorEnabled(false);
                                       student_id_til.setErrorEnabled(false);
                                       student_password_til.setErrorEnabled(false);

                                       //Validating Name
                                       int i;
                                       for( i=0;i<student_name.length();i++)
                                       {
                                           if(((student_name.charAt(i)>=65)&&(student_name.charAt(i)<=90))||((student_name.charAt(i)>=97)&&(student_name.charAt(i)<=122))|| (student_name.charAt(i) ==' ' ))
                                           {

                                           }
                                           else
                                           {
                                               break;
                                           }
                                       }
                                       if (i !=student_name.length())
                                       {
                                           student_name_til.setError("Invalid Name!");
                                           student_name_et.requestFocus();
                                       }
                                       else
                                       {
                                           student_email_til.setErrorEnabled(false);
                                           student_name_til.setErrorEnabled(false);
                                           student_id_til.setErrorEnabled(false);
                                           student_password_til.setErrorEnabled(false);

                                          boolean verify_email =  EMAIL_ADDRESS_PATTERN.matcher(student_email).matches();
                                          if(verify_email == false)
                                          {

                                              student_email_til.setError("Invalid email!");
                                              student_email_et.requestFocus();
                                          }
                                          else
                                          {
                                              student_email_til.setErrorEnabled(false);
                                              student_name_til.setErrorEnabled(false);
                                              student_id_til.setErrorEnabled(false);
                                              student_password_til.setErrorEnabled(false);

                                              //Saving All Details into the firebase

                                              saveStudentRegistrationDetails();
                                          }

                                       }

                                   }
                               }
                           }
                       }
                   }

               }
           });

            //When Login key is presssed

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
                startActivity(new Intent(StudentRegistrationActivity.this,StudentLoginActivity.class));
            }
        });

    }

    //Saving registration details on the firebase

    public void saveStudentRegistrationDetails()
    {

     MyStudentRegistrationHandler refrence = new MyStudentRegistrationHandler(student_id,student_name,student_email,student_password);

      FirebaseDatabase firebaseDatabase =   FirebaseDatabase.getInstance();
      DatabaseReference dr1 = firebaseDatabase.getReference("Student_Details");
       dr1.child(student_id).child("Personal_Details").setValue(refrence);
        Toast.makeText(this, "Registration Successfull", Toast.LENGTH_SHORT).show();
        student_name_et.setText("");
        student_id_et.setText("");
        student_password_et.setText("");
        student_email_et.setText("");

    }


    //Validating user registration email id

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile
            (
                    "[a-zA-Z0-9+._%-+]{1,256}" +
                            "@" +
                            "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                            "(" +
                            "." +
                            "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                            ")+"
            );


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        finish();
        startActivity(new Intent(this,UserSelectionActivity.class));
        return super.onKeyDown(keyCode, event);
    }
}
