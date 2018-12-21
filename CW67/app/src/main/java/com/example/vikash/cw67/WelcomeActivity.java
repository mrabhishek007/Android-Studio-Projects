package com.example.vikash.cw67;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener,Runnable
{

    TextView tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv3 = findViewById(R.id.textView3);
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(this);
        Thread t1 = new Thread(this);
        t1.start();
    }



    //Reading data in a file from Internal Storage(Thread is not necessary)

    public void run()
    {
        String msg ="";
        try
        {
            FileInputStream fis = openFileInput("login_details");

                /**
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
           msg =  new String(buffer);

              */
            int x;

            x=fis.read();//when fis.read() is called it will return integer value from array of bytes[].
            Log.e("--->",""+x);

            while (x != -1)
            {
                char data = (char)x;
                msg = msg + data;
                x=fis.read();//When it is called 2nd time it will return next integer value from array of bytes

            }
            fis.close();
            tv3.setText(msg);

        }

        catch (FileNotFoundException e)
        {
            Toast.makeText(this, "File doesn't exist", Toast.LENGTH_SHORT).show();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }


    @Override
    public void onClick(View v)
    {
        Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
