//Sending,Retrieving and Deleting data from FireBase
package com.example.android.cw53;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5;
    String id,age,name,cno,paid_fee;
    String total_fee="50000";
    Button bt,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        bt2=findViewById(R.id.b2);
        bt=findViewById(R.id.b1);

                //Insering Data in Database

        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                id = et1.getText().toString().trim();
                name = et2.getText().toString().trim();
                age = et3.getText().toString().trim();
                cno = et4.getText().toString().trim();
                paid_fee = et5.getText().toString().trim();
                if(id.isEmpty())
                {
                    et1.setError("Empty Id");
                    et1.requestFocus();
                }
                else
                {
                    if(name.isEmpty())
                    {
                        et2.setError("Empty Name");
                        et2.requestFocus();
                    }
                    else
                    {
                        if(age.isEmpty())
                        {
                            et3.setError("Empty Age");
                            et3.requestFocus();
                        }
                        else
                        {
                            if(cno.isEmpty())
                            {
                                et4.setError("Empty Contact_no.");
                                et4.requestFocus();
                            }
                            else
                            {
                                if(paid_fee.isEmpty())
                                {
                                    et5.setError("Empty Fee Section");
                                    et5.requestFocus();
                                }
                                else
                                {
                                 MyHandler fee_obj = new MyHandler(paid_fee,total_fee);
                                 MyHandler personal_details_obj = new MyHandler(name,age,cno);
                                 FirebaseDatabase fd =  FirebaseDatabase.getInstance();
                                 DatabaseReference dr = fd.getReference("Student1");
                                 dr.child(id).child("Fee").setValue(fee_obj);
                                 dr.child(id).child("Personal Details").setValue(personal_details_obj);
                                    et1.setText("");
                                    et2.setText("");
                                    et3.setText("");
                                    et4.setText("");
                                    et5.setText("");
                                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }

            }
        });//End of Button1

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this,RetreiveFireBaseData.class);
                startActivity(i);

            }
        });
    }
}
