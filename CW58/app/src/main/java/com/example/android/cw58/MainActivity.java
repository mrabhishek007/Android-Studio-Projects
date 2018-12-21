//Working With SharedPrefences with Login Page
package com.example.android.cw58;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText et1,et2;
    Button bt;
    SharedPreferences sp;//Interface which is used to read Shared Prefences
    SharedPreferences.Editor spe;//Interface which is used to write data in SharedPrefences

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getApplicationContext().getSharedPreferences("login_status",MODE_PRIVATE);
        checkSharedPrefences();
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        bt = findViewById(R.id.bt1);
        bt.setOnClickListener(this);
    }

    public void checkSharedPrefences()
    {
        boolean login_status = sp.getBoolean("key",false);
        if(login_status)
        {
            Toast.makeText(this, "Welcome Again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,WelcomeActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v)
    {
        /** Use this when Multiple button
         int  i = bt.getId();
         if(R.id.bt1==i)
         {
         }
         */
        String uname = et1.getText().toString().trim();
        String pass=   et2.getText().toString().trim();

        if(uname.isEmpty())
        {
            et1.setError("Empty UserName");
            et2.requestFocus();
        }
        else
        if(pass.isEmpty())
        {
            et2.setError("Empty Password");
            et2.requestFocus();
        }
        else

        if((uname.equalsIgnoreCase("Vikash"))&(pass.equals("12345")))
        {
            // Writing Data to SharedPrefences //

            spe  =  sp.edit();
            spe.putBoolean("key",true);
            //spe.apply();              //also valid
            spe.commit();

            Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,WelcomeActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "Username or password Invalid", Toast.LENGTH_SHORT).show();
        }

    }

}
