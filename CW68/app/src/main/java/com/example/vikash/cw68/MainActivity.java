//Working with Services(Music player using Service)
package com.example.vikash.cw68;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent i;
    TextView tv ;
    Switch sw;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = findViewById(R.id.switch1);
        tv = findViewById(R.id.textView2);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                i = new Intent(MainActivity.this,MyService.class);
              if(sw.isChecked())
              {
                  startService(i);
                  Toast.makeText(MainActivity.this, "Music Started", Toast.LENGTH_SHORT).show();
              }
              else
              {
                 stopService(i);
                  Toast.makeText(MainActivity.this, "Music Stopped", Toast.LENGTH_SHORT).show();

              }
            }
        });
    }
}
