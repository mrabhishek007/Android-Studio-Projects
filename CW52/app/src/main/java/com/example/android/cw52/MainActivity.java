//Working with Firebase (Only Data sending
// )
package com.example.android.cw52;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    ProgressDialog pd;
    EditText et1,et2,et3;
    String id,sal,name;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        bt=findViewById(R.id.b1);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                 id = et1.getText().toString().trim();
                 name = et2.getText().toString().trim();
                 sal = et3.getText().toString().trim();
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
                         if(sal.isEmpty())
                         {
                             et3.setError("Empty Salary");
                             et3.requestFocus();
                         }
                         else
                         {


                            try
                            {
                                pd = new ProgressDialog(MainActivity.this);
                                pd.setMessage("Saving Data");
                                pd.setCancelable(false);
                                pd.show();
                                FirebaseDatabase fd =  FirebaseDatabase.getInstance();
                                DatabaseReference dr = fd.getReference("Student");
                                MyFirebaseDemo mfd = new MyFirebaseDemo(id, name, sal);
                                dr.child(id).setValue(mfd);
                                et1.setText("");
                                et2.setText("");
                                et3.setText("");
                                pd.dismiss();
                                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            }
                            catch(Exception e)
                            {
                                Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }


                         }
                     }
                 }

            }
        });
    }
}
