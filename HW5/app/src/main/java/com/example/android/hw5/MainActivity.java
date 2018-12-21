package com.example.android.hw5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    EditText et;
    TextView tv;
    double bal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 =(Button)findViewById(R.id.b1);
        Button bt2 =(Button)findViewById(R.id.b2);
        Button bt3 =(Button)findViewById(R.id.b3);
        et=(EditText)findViewById(R.id.et1);
        tv=(TextView)findViewById(R.id.tv1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String dep=et.getText().toString();
                if(dep.isEmpty())
                {
                    et.requestFocus();
                    et.setError("Empty Demposit Amount Not Allowed");
                }
                else
                {
                    double deposit = Double.parseDouble(dep);
                    if (deposit > 0) {
                        bal = bal + deposit;
                        tv.setText("Rs "+dep+" Successfully Deposited");
                        et.setText("");
                    } else {
                        et.requestFocus();
                        et.setError("Invalid Amount");
                    }
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wd=et.getText().toString();
                if(wd.isEmpty())
                {
                    et.requestFocus();
                    et.setError("Empty Withdrawal Amount Not Allowed");
                }
                else {
                    double wdraw = Double.parseDouble(wd);
                    if(wdraw==0)
                    {
                        et.requestFocus();
                        et.setError("Enter amount greater than Rs 0");
                    }
                    else {
                        if (wdraw <= bal) {
                            bal = bal - wdraw;
                            tv.setText("Rs " + wd + " Successfully Withdrawn");
                            et.setText("");
                        } else {
                            et.requestFocus();
                            et.setError("Insufficient funds");
                        }
                    }
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Bal = Rs "+bal);
                et.setText("");
            }
        });

    }
}
