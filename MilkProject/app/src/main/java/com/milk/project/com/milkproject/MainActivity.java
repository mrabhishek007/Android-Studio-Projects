package com.milk.project.com.milkproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.opengl.Visibility;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends Activity {

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        userRegistration();

    }


        //User Registration  Page
    protected  void userRegistration()
    {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run()
            {

                try
                {
                    progressBar.setVisibility(View.VISIBLE);
                    int i;
                    for(i=1;i<=100;i++)
                    {
                        Thread.sleep(20);
                        progressBar.setProgress(i); //Setting progress to Progress bar

                    }
                    startActivity(new Intent(MainActivity.this,UserRegistrationActivity.class));
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }




}
