package com.example.android.cwp11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainLayoutActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText et1,et2;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Button bt1=(Button)findViewById(R.id.b1);
        Button bt2=(Button)findViewById(R.id.b2);
        Button bt3=(Button)findViewById(R.id.b3);
        Button bt4=(Button)findViewById(R.id.b4);
         et1=(EditText)findViewById(R.id.et1);
         et2=(EditText)findViewById(R.id.et2);
         tv1=(TextView)findViewById(R.id.tv1);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int id= v.getId();
        String n1= et1.getText().toString();
        String n2= et2.getText().toString();
        double num1=Double.parseDouble(n1);//Converting String to Double
        double num2=Double.parseDouble(n2);
        if(id==R.id.b1)
        {
          double sum=num1+num2;
            String add=String.valueOf(sum);//Converting double to String
            tv1.setText(n1+" + "+n2+" = "+add);
        }
        else if(id==R.id.b2)
        {
            double sub =num1-num2;
            String subs=String.valueOf(sub);//Converting double to String
            tv1.setText(n1+" - "+n2+" = "+subs);
        }
        else if(id==R.id.b3)
        {
            double mul= num1*num2;
            String muls=String.valueOf(mul);
            tv1.setText(n1+" * "+n2+" = "+muls);
        }
        else if(id==R.id.b4)
        {
            if(num2==0)
            {
                Toast.makeText(this,"Don't Enter Zero for Denominator ",Toast.LENGTH_SHORT).show();
            }
            else
            {
                double div=num1/num2;
                String divs=String.valueOf(div);
                tv1.setText(n1+" / "+n2+" = "+divs);
            }
        }

    }
}
