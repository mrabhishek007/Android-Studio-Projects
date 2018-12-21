package com.milk.project.com.milkproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.milk.project.com.milkproject.milkdatabase.MilkDatabase;

import java.util.Random;
import java.util.regex.Pattern;

public class UserRegistrationActivity extends Activity {

    TextInputLayout til_reg_name,til_reg_mob,til_reg_email;
    EditText user_reg_name,user_reg_mob,user_reg_email;
    Button register_user;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        til_reg_name = findViewById(R.id.til_user_registration_name);
        til_reg_mob =  findViewById(R.id.til_user_registration_mobile);
        til_reg_email =findViewById(R.id.til_user_registration_email);
        user_reg_name =findViewById(R.id.user_registration_name);
        user_reg_mob = findViewById(R.id.user_registration_mobile);
        user_reg_email = findViewById(R.id.user_registration_email);
         register_user = findViewById(R.id.user_register_button);

        userRegistrationDialog();

    }

    protected void userRegistrationDialog()
    {

        register_user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String uname =  user_reg_name.getText().toString().trim();
                String mob =  user_reg_mob.getText().toString().trim();
                String email =  user_reg_email.getText().toString().trim();

                if(uname.isEmpty())
                {
                    til_reg_name.setError("Empty!");
                    user_reg_name.requestFocus();
                }
                else
                {
                    til_reg_name.setErrorEnabled(false);
                    if(mob.isEmpty())
                    {
                        til_reg_mob.setError("Empty!");
                        user_reg_mob.requestFocus();
                    }
                    else
                    {
                        til_reg_mob.setErrorEnabled(false);
                        if(email.isEmpty())
                        {
                            til_reg_email.setError("Empty!");
                            user_reg_email.requestFocus();
                        }
                        else  //When fields are not empty
                        {
                            int i;
                            til_reg_email.setErrorEnabled(false);

                            //Validating Name
                            for( i=0;i<uname.length();i++)
                            {
                                if(((uname.charAt(i)>=65)&&(uname.charAt(i)<=90))||((uname.charAt(i)>=97)&&(uname.charAt(i)<=122))|| (uname.charAt(i) ==' ' ))
                                {

                                }
                                else
                                {
                                    break;
                                }
                            }
                            if (i != uname.length())
                            {
                                til_reg_name.setError("Invalid Name!");
                                user_reg_name.requestFocus();
                            }
                            else
                            {
                                til_reg_name.setErrorEnabled(false);

                                //Validating Mobile No.

                                if ( (mob.length() == 10) && ( (mob.charAt(0)=='9')||(mob.charAt(0)=='8') || (mob.charAt(0)=='7') )  )
                                {
                                    til_reg_mob.setErrorEnabled(false);

                                    //Validating Email_id
                                    boolean res =  EMAIL_ADDRESS_PATTERN.matcher(email).matches();
                                    if(res==false)
                                    {
                                        til_reg_email.setError("Invalid Email");
                                        user_reg_email.requestFocus();
                                    }
                                    else
                                    {

                                        til_reg_email.setErrorEnabled(false);

                                        String reg_otp = validateOtp(mob);//Otp Geneation


                                        //Saving User_Registration Details in the Database

                                        MilkDatabase ref = new MilkDatabase(UserRegistrationActivity.this);
                                        SQLiteDatabase sql_db = ref.getWritableDatabase();

                                        ContentValues cv = new ContentValues();
                                        cv.put(MilkDatabase.COL1,uname);
                                        cv.put(MilkDatabase.COL2,mob);
                                        cv.put(MilkDatabase.COL3,email);
                                        cv.put(MilkDatabase.COL4,reg_otp);

                                        long status = sql_db.insert(MilkDatabase.TABLE_NAME,null,cv);

                                        if(status!= -1)
                                        {
                                            user_reg_name.setText("");
                                            user_reg_mob.setText("");
                                            user_reg_email.setText("");
                                            Toast.makeText(UserRegistrationActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                        }

                                        else
                                        {
                                            Toast.makeText(UserRegistrationActivity.this, "Error while inserting data", Toast.LENGTH_SHORT).show();
                                        }


                                        //Rest program starts from here





                                    }

                                }
                                else
                                {
                                    til_reg_mob.setError("Invalid Mob.No.");
                                    user_reg_mob.requestFocus();
                                }

                            }
                        }//end of else
                    }
                }
            }
        });
    }//end of userRegistrationDialog()


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

       //Generating Otp using Random class concept
   protected String validateOtp(String no)
   {
       String mob="";
       for(int i=0;i<6;i++)
       {
         char ch  = no.charAt(i);
         mob+=ch;
       }
       int otp =Integer.parseInt(mob);
       Random random = new Random();
       int gen_otp = random.nextInt(otp);
       String orig_otp= "DW-"+String.valueOf(gen_otp);
       return  orig_otp;
   }
}
