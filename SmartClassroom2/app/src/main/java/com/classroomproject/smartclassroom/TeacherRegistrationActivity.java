package com.classroomproject.smartclassroom;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class TeacherRegistrationActivity extends Activity {

    TextView teacher_login;
    EditText et_regname,et_id,et_email,et_password,et_sub;
    TextInputLayout ti_regname,ti_id,ti_email,ti_password,ti_sub;
    Button submit;
    String teacher_name,teacher_id,teacher_email,teacher_password,teacher_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);


        ti_regname = findViewById(R.id.til_teacher_registration_name);
        ti_id = findViewById(R.id.til_teacher_registration_id);
        ti_email = findViewById(R.id.til_teacher_registration_email);
        ti_password = findViewById(R.id.til_teacher_registration_Password);
        ti_sub = findViewById(R.id.til_teacher_registration_sub);


        et_regname = findViewById(R.id.teacher_registration_name);
        et_id = findViewById(R.id.teacher_registration_id);
        et_email = findViewById(R.id.teacher_registration_email);
        et_password = findViewById(R.id.teacher_registartion_password);
        et_sub = findViewById(R.id.teacher_registration_sub);

        submit = findViewById(R.id.teacher_register_button);
        teacher_login = findViewById(R.id.teacher_login_tv);

                    //When Login is clicked
        teacher_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
                startActivity(new Intent(TeacherRegistrationActivity.this,TeacherLoginActivity.class));
            }
        });


        validate();

    }

    public void validate()
    {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                teacher_name = et_regname.getText().toString().trim();
                teacher_id = et_id.getText().toString().trim();
                teacher_email = et_email.getText().toString().trim();
                teacher_password = et_password.getText().toString().trim();
                teacher_sub = et_sub.getText().toString().trim();


                if(teacher_name.isEmpty())
                {

                    ti_email.setErrorEnabled(false);
                    ti_id.setErrorEnabled(false);
                    ti_password.setErrorEnabled(false);
                    ti_regname.setError("Empty!");
                    et_regname.requestFocus();
                }
                else {
                    ti_regname.setErrorEnabled(false);
                    ti_email.setErrorEnabled(false);
                    ti_id.setErrorEnabled(false);
                    ti_password.setErrorEnabled(false);

                    if (teacher_id.isEmpty() || teacher_sub.isEmpty()) {
                        ti_id.setError("Empty!");
                        et_id.requestFocus();
                    } else
                        {
                            ti_regname.setErrorEnabled(false);
                            ti_email.setErrorEnabled(false);
                            ti_id.setErrorEnabled(false);
                            ti_password.setErrorEnabled(false);
                        if (teacher_email.isEmpty()) {
                            ti_email.setError("Empty!");
                            et_email.requestFocus();
                        } else {
                            ti_email.setErrorEnabled(false);
                            if (teacher_password.isEmpty()) {
                                ti_password.setError("Empty!");
                                et_password.requestFocus();
                            } else
                            {
                                ti_regname.setErrorEnabled(false);
                                ti_email.setErrorEnabled(false);
                                ti_id.setErrorEnabled(false);
                                ti_password.setErrorEnabled(false);

                                //When fields are not empty

                                //Validating Name
                                int i;
                                for( i=0;i<teacher_name.length();i++)
                                {
                                    if(((teacher_name.charAt(i)>=65)&&(teacher_name.charAt(i)<=90))||((teacher_name.charAt(i)>=97)&&(teacher_name.charAt(i)<=122))|| (teacher_name.charAt(i) ==' ' ))
                                    {

                                    }
                                    else
                                    {
                                        break;
                                    }
                                }
                                if (i != teacher_name.length())
                                {
                                    ti_regname.setError("Invalid Name!");
                                    et_regname.requestFocus();
                                }
                                else
                                {
                                    ti_regname.setErrorEnabled(false);
                                    ti_email.setErrorEnabled(false);
                                    ti_id.setErrorEnabled(false);
                                    ti_password.setErrorEnabled(false);

                                    //Validating Teacher ID
                                    if(teacher_id.length()>8)
                                    {
                                        ti_id.setError("Max length must not exceed 8 char");
                                        et_id.requestFocus();
                                    }
                                    else
                                    {
                                        ti_regname.setErrorEnabled(false);
                                        ti_email.setErrorEnabled(false);
                                        ti_id.setErrorEnabled(false);
                                        ti_password.setErrorEnabled(false);

                                        //Validation of Email

                                       boolean email_validation =  EMAIL_ADDRESS_PATTERN.matcher(teacher_email).matches();
                                        if(email_validation == false)
                                        {
                                            ti_email.setError("Invalid e_mail !");
                                            et_email.requestFocus();
                                        }
                                        else
                                        {
                                            ti_regname.setErrorEnabled(false);
                                            ti_email.setErrorEnabled(false);
                                            ti_id.setErrorEnabled(false);
                                            ti_password.setErrorEnabled(false);


                                            //Saving  All Details into the Firebase

                                            saveTeacherRegistration();


                                        }
                                    }//Id Validation

                                }//name validation else
                            }
                        }
                        }
                }
            }
        });
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

    //Saving Teacher RegistrationDetails on Firebase

    public void saveTeacherRegistration()
    {
        MyTeacherRegistartionHandler teacher_personal_details_obj  = new MyTeacherRegistartionHandler(teacher_name,teacher_id,teacher_email,teacher_password,teacher_sub);

       FirebaseDatabase fd  =  FirebaseDatabase.getInstance();
       DatabaseReference dr = fd.getReference("Teacher_Registration");




        dr.child(teacher_id).child("Personal_Details").setValue(teacher_personal_details_obj);
        Toast.makeText(this, "Registration Successfull ", Toast.LENGTH_SHORT).show();

        et_sub.setText("");
        et_regname.setText("");
        et_id.setText("");
        et_email.setText("");
        et_password.setText("");

    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        finish();
        startActivity(new Intent(this,UserSelectionActivity.class));
        return super.onKeyDown(keyCode, event);
    }


}
