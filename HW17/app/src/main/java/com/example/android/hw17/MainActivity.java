
//Working with Checkboxes
package com.example.android.hw17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button b1;
    CheckBox sa;
    CheckBox c[] = new CheckBox[3];
    int ids[]={R.id.checkBox2,R.id.checkBox3,R.id.checkBox4};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<c.length;i++)
        {
            c[i]=findViewById(ids[i]);
        }
        b1=findViewById(R.id.button);
        sa=findViewById(R.id.checkBox1);
        tv1=findViewById(R.id.tv);

        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(sa.isChecked())
                {
                    for(int i=0;i<c.length;i++)
                    {
                        c[i].setChecked(true);
                    }
                }
                else
                {
                    for(int i=0;i<c.length;i++)
                    {
                        c[i].setChecked(false);
                    }
                }
            }
        });

        for(int i=0;i<c.length;i++)
        {
           c[i].setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view)
               {
                   int count=0;
                   for(int i=0;i<c.length;i++)
                   {
                       if(c[i].isChecked())
                       {
                           count++;
                       }
                   }
                   if(count==c.length)
                   {
                       sa.setChecked(true);
                       count=0;
                   }
                   else
                   {
                      sa.setChecked(false);
                      count=0;
                   }

               }
           });

        }
        //when button is clicked
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                tv1.setText("");
               String text="";
               for(int i=0;i<c.length;i++)
               {
                   if(c[i].isChecked())
                   {
                       tv1.setText(text=text+c[i].getText().toString().concat(" "));
                   }

               }

            }
        });












    }
}
