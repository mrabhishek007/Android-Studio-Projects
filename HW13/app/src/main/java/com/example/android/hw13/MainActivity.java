package com.example.android.hw13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import static com.example.android.hw13.R.styleable.View;

public class MainActivity extends AppCompatActivity {

    EditText et[]=new EditText[3];
    int id[]={R.id.et1,R.id.et2,R.id.et3};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.b1);
        for(int i=0;i<id.length;i++)
        {
            et[i] =(EditText)findViewById(id[i]);
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String values[] = new String[et.length];
                int x;
                for (x = 0; x < id.length; x++) {
                    values[x] = et[x].getText().toString().trim();
                    if (values[x].isEmpty()) {
                        et[x].setError("Empty field not allowed");
                        et[x].requestFocus();
                        break;
                    }
                }
                if (x == et.length) {
                    int id = Integer.parseInt(values[0]);
                    double sal = Double.parseDouble(values[2]);
                    Intent it = new Intent(MainActivity.this, ViewActivity.class);
                    it.putExtra("key1", id);
                    it.putExtra("key2", values[1]);
                    it.putExtra("key3", sal);
                    startActivity(it);
                    for (int i = 0; i < et.length; i++) {
                        et[i].setText("");
                        et[0].requestFocus();
                    }
                    //           OR
                    // i.putExtra("key",values);


                }
            }});//Onclick
        }

    }
