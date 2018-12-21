//Transferring Data Between Fragments
package com.example.android.cw49;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void data(String data)
    {

    FragmentManager fm =  getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    Fragment fg = fm.findFragmentById(R.id.fg2);
    Fragment2  fg2ref =  (Fragment2)fg;
    fg2ref.receiveData(data);
    }
}
