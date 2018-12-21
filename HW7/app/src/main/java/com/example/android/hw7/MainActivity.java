package com.example.android.hw7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.name;


public class MainActivity extends AppCompatActivity {
    EditText et1, et2, et3;
    Button bt;
    TextView tv;
    int vage;
    String smob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.b1);
        tv = (TextView) findViewById(R.id.tv2);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et1.getText().toString();
                String age = et2.getText().toString();
                String mob = et3.getText().toString();

                if (name.isEmpty()) {
                    et1.requestFocus();
                    et1.setError("Empty Name Not Allowed");

                } else if (age.isEmpty()) {
                    et2.requestFocus();
                    et2.setError("Empty Age Not Allowed ");

                } else if (mob.isEmpty()) {
                    et3.requestFocus();
                    et3.setError("Empty Contact no. Not Allowed");

                } else if ( Integer.parseInt(age)>= 23 && Integer.parseInt(age) <=50) {
                    vage = Integer.parseInt(age);
                    if(mob.length()==10)
                    {
                    if (mob.charAt(0) == '7' || mob.charAt(0) == '8' || mob.charAt(0) == '9') {
                        smob = mob;
                        tv.setText("Name : " + name + " , " + "Age  : " + vage + " , " + "Mob No : " + smob);
                        et1.setText("");
                        et2.setText("");
                        et3.setText("");
                    } else {
                        et3.requestFocus();
                        et3.setError("Mob No. must start with 7/8/9");
                    }}
                    else{
                        et3.requestFocus();
                        et3.setError("Mob No. muust contain 10 digits");
                    }
                } else {
                      et2.requestFocus();
                    et2.setError("Plz enter age between 23 - 50 ");
                       }

            }
        });
    }
}

