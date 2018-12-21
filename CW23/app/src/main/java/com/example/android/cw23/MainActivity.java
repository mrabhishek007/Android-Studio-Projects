//Radio Button with Submit button
package com.example.android.cw23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    RadioButton rb1,rb2,rb3;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb1= findViewById(R.id.rb1);
        rb2= findViewById(R.id.rb2);
        rb3= findViewById(R.id.rb3);
        tv=findViewById(R.id.tv2);
        rg=findViewById(R.id.rg1);
        Button b1=findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int id=rg.getCheckedRadioButtonId();
                   if(id==R.id.rb1)
                   {
                       tv.setText(rb1.getText().toString());
                   }
                   else
                       if(id==R.id.rb2)
                       {
                           tv.setText(rb2.getText().toString());
                       }
                       else

                       {
                           tv.setText(rb3.getText().toString());
                       }
            }
        });




    }
}
