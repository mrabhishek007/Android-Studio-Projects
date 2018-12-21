package com.example.android.cw24;

import android.bluetooth.BluetoothAdapter;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
ToggleButton tb1,tb3;
    Switch s2,s3;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb1=(ToggleButton)findViewById(R.id.tb1);
        s2=(Switch) findViewById(R.id.sw1);
        tb3=(ToggleButton)findViewById(R.id.tb3);
        s3=(Switch) findViewById(R.id.switch1);

        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               if(tb1.isChecked())
               {
                   Toast.makeText(MainActivity.this,"On",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this,"Off",Toast.LENGTH_SHORT).show();
               }

            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s2.isChecked())
                {
                    Toast.makeText(MainActivity.this,"On",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Off",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(tb3.isChecked())
                {
                    mp =MediaPlayer.create(MainActivity.this,R.raw.song);
                    mp.start();
                    Toast.makeText(MainActivity.this,"Playing",Toast.LENGTH_SHORT).show();

                }
                else
                {
                      mp.pause();
                    Toast.makeText(MainActivity.this,"Stopped",Toast.LENGTH_SHORT).show();
                }
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               BluetoothAdapter ba= BluetoothAdapter.getDefaultAdapter();
                if(s3.isChecked())
                {
                    ba.enable();
                    Toast.makeText(MainActivity.this,"Bluetooth On",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ba.disable();
                    Toast.makeText(MainActivity.this,"Bluetooth Off",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onStop()
    {
      super.onStop();
        mp.stop();
    }
}
