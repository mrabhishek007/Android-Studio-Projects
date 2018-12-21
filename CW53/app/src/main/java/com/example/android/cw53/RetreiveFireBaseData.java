package com.example.android.cw53;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetreiveFireBaseData extends AppCompatActivity
{

    String age,name,contact_no,search_id;
    EditText et6,et8,et9,et10;
    Button bt3,bt4,bt5;
    DatabaseReference dr1;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_fire_base_data);
        et6 =  findViewById(R.id.et6);
        et8 =  findViewById(R.id.et8);
        et9 =  findViewById(R.id.et9);
        et10 = findViewById(R.id.et10);
        bt3  = findViewById(R.id.b3);
        bt4  = findViewById(R.id.b4);
        bt5  = findViewById(R.id.b5);



                      //Searching Data in Firebase

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                search_id = et6.getText().toString().trim();
                if(search_id.isEmpty())
                {
                    et6.setError("Empty Id Not Allowed");
                }
                else
                {
                    pd= new ProgressDialog(RetreiveFireBaseData.this);
                    pd.setMessage("Please Wait");
                    pd.setCancelable(false);
                    pd.show();
                   FirebaseDatabase fd =  FirebaseDatabase.getInstance();
                   DatabaseReference dr = fd.getReference("Student1");//Parent_Name is Case_sensitive.Parent_name which is stored in database must be used
                             dr1 = dr.child(search_id).child("Personal Details");

                    dr1.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           if (dataSnapshot.getChildrenCount() > 0)
                           {
                               et6.setText("");
                               MyHandler mh = dataSnapshot.getValue(MyHandler.class);
                               String name = mh.getName();
                               String age = mh.getAge();
                               String c_no = mh.getCno();
                               et8.setVisibility(View.VISIBLE);
                               et9.setVisibility(View.VISIBLE);
                               et10.setVisibility(View.VISIBLE);
                               bt4.setVisibility(View.VISIBLE);
                               bt5.setVisibility(View.VISIBLE);
                               et8.setText(name);
                               et9.setText(age);
                               et10.setText(c_no);
                               pd.dismiss();
                               Toast.makeText(RetreiveFireBaseData.this, "Data Found", Toast.LENGTH_SHORT).show();
                           }
                           else
                           {
                               et6.setError("Invalid ID !");
                               et6.setText("");
                               et6.requestFocus();
                               et8.setText("");
                               et9.setText("");
                               et10.setText("");
                               et8.setVisibility(View.GONE);
                               et9.setVisibility(View.GONE);
                               et10.setVisibility(View.GONE);
                               bt4.setVisibility(View.GONE);
                               bt5.setVisibility(View.GONE);
                               pd.dismiss();

                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError)
                       {
                           Toast.makeText(RetreiveFireBaseData.this, "Database Error", Toast.LENGTH_SHORT).show();
                           Toast.makeText(RetreiveFireBaseData.this, ""+databaseError.getDetails(), Toast.LENGTH_SHORT).show();
                           pd.dismiss();

                       }
                   });

                }
            }
        });//End of Button b3


                            //Updating Data in Firebase

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                name = et8.getText().toString().trim();
                age = et9.getText().toString().trim();
                contact_no = et10.getText().toString().trim();

                    if(name.isEmpty())
                    {
                        et8.setError("Empty Name");
                        et8.requestFocus();
                    }
                    else
                    {
                        if(age.isEmpty())
                        {
                            et9.setError("Empty Age");
                            et9.requestFocus();
                        }
                        else
                        {
                            if(contact_no.isEmpty())
                            {
                                et10.setError("Empty Contact_no.");
                                et10.requestFocus();
                            }
                            else
                            {
                                MyHandler p_details_obj = new MyHandler(name,age,contact_no);
                                dr1.setValue(p_details_obj);
                                et8.setText("");
                                et9.setText("");
                                et10.setText("");
                                et8.setVisibility(View.GONE);
                                et9.setVisibility(View.GONE);
                                et10.setVisibility(View.GONE);
                                bt4.setVisibility(View.GONE);
                                bt5.setVisibility(View.GONE);
                                Toast.makeText(RetreiveFireBaseData.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            }
        });//End of button 2



                //Deleting Data in Firebase
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              DatabaseReference dr2 =  FirebaseDatabase.getInstance().getReference("Student1");
              DatabaseReference sr3 = dr2.child(search_id);
              sr3.removeValue();//1st way to delete child in Firebase
              //sr3.setValue(null);//2nd way to delete child in Firebase
              Toast.makeText(RetreiveFireBaseData.this, "Id_no "+search_id+" deleted successfully", Toast.LENGTH_SHORT).show();
                et8.setText("");
                et9.setText("");
                et10.setText("");
                et8.setVisibility(View.GONE);
                et9.setVisibility(View.GONE);
                et10.setVisibility(View.GONE);
                bt4.setVisibility(View.GONE);
                bt5.setVisibility(View.GONE);
            }
        });
    }
}

