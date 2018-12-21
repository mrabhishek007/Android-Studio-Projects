package com.example.android.hw6;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    TextView tv1;
    String funame="Android";
    String pass="11111111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.b1);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        tv1 = (TextView) findViewById(R.id.tv2);
    }

       public void validate(View v)
    {
         String uname = et1.getText().toString();
        String pwd=et2.getText().toString();
        if (uname.isEmpty())
        {
            et1.requestFocus();
            et1.setError("Empty Username not allowed");

        }
        else
            if(pwd.isEmpty())
        {
            et2.requestFocus();
            et2.setError("Empty Password not allowed");

        }
        else {
                if (uname.equalsIgnoreCase(funame) && pwd.equals(pass) )
                {
                    tv1.setText("Login Successfull");
                    tv1.setTextColor(Color.GREEN);
                }
                else
                    {
                       tv1.setText("Username or Password is Incorrect");
                        tv1.setTextColor(Color.RED);
                    }
            }
        et1.setText("");
        et2.setText("");

    }



}
