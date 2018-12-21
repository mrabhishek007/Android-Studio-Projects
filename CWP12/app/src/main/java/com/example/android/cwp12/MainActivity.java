package com.example.android.cwp12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText et1,et2,et3;
    TextView tv1;
    double num1;
    double num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
      Button bt1=(Button)findViewById(R.id.b1);
        tv1 =(TextView)findViewById(R.id.tv1);
        et1= (EditText)findViewById(R.id.et1);
        et2= (EditText)findViewById(R.id.et2);
        et3= (EditText)findViewById(R.id.et3);
        bt1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        String n1 = et1.getText().toString();
        String n2 = et3.getText().toString();
        String o1 = et2.getText().toString();

        if(n1.isEmpty() || n2.isEmpty() || o1.isEmpty())
        {
            Toast.makeText(this,"Don't enter empty fields ",Toast.LENGTH_SHORT).show();
        }
        else {
            num1 = Double.parseDouble(n1);
            num2 = Double.parseDouble(n2);


            if (o1.equals("+"))//Comparing 2 strings
            {
                double sum = num1 + num2;
                tv1.setText(n1 + " + " + n2 + " = " + sum);
            } else if (o1.equals("-")) {
                double sub = num1 - num2;
                tv1.setText(n1 + " - " + n2 + " = " + sub);
            } else if (o1.equals("*")) {
                double mul = num1 * num2;
                tv1.setText(n1 + " * " + n2 + " = " + mul);
            } else if (o1.equals("/")) {
                double div = num1 / num2;
                tv1.setText(n1 + " / " + n2 + " = " + div);
            } else {
                et2.setError("\"Plz enter between + , - , / , * only\"");
            }
        }
    }
}
