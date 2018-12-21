package com.example.android.cw46;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Mediator
{



    TextView tv;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv1);
    }

    //1st Method
    /**
    public void reciever (String data)
    {

      Toast.makeText(this, "Data Sent to Actiivity", Toast.LENGTH_SHORT).show();
      tv.setText(data);
    }*/

    //2nd Method(Recommended)

    @Override
    public void communicator(String data)
    {
        Toast.makeText(this, "Data Sent to Actiivity", Toast.LENGTH_SHORT).show();
        tv.setText(data);

    }




}
