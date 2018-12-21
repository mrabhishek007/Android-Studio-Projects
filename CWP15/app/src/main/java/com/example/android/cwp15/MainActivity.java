package com.example.android.cwp15;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    Button bt1 =(Button)findViewById(R.id.button);
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if(id==R.id.button)
        {
            AlertDialog.Builder bd= new AlertDialog.Builder(this);
            bd.setMessage("Text Dialog Box");
            AlertDialog ad=  bd.create();
            ad.show();

        }

    }

    void iconDialog(View v)
    {
        AlertDialog.Builder bd = new AlertDialog.Builder(this);
        bd.setMessage("Battery Low");
        bd.setTitle("warning");
        bd.setIcon(R.drawable.em);
        bd.create().show();
    }

    void choiceDialog(final View v)
    {

        AlertDialog.Builder bd = new AlertDialog.Builder(this);
        bd.setMessage("Open Camera");
        bd.setTitle("Are you sure");
        bd.setIcon(R.drawable.camera);
        bd.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        AlertDialog.Builder bd2 = new AlertDialog.Builder(MainActivity.this);

                         bd2.setMessage("Warning");
                         bd2.setTitle("Are You Sure");
                        bd2.setCancelable(false);
                        bd2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.choiceDialog(v);
                            }
                        });
                        bd2.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder bd3 = new AlertDialog.Builder(MainActivity.this);
                                dialog.dismiss();

                            }
                        });
                        bd2.create().show();

                    }
                });
        bd.setPositiveButton("Open", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
            }
        });
        bd.setCancelable(false);
        AlertDialog ad =bd.create();
        ad.show();

    }


}