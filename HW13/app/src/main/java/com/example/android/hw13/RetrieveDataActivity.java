package com.example.android.hw13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RetrieveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);
        TextView tv4 =(TextView)findViewById(R.id.tv4);
        TextView tv5 =(TextView)findViewById(R.id.tv5);
        TextView tv6 =(TextView)findViewById(R.id.tv6);
        Intent i =getIntent();
       Bundle b=  i.getExtras();
       String name=  b.getString("key2");
       int id=  b.getInt("key1");
       double salary=  b.getDouble("key3");

        tv4.setText("Idno - "+String.valueOf(id));
        tv5.setText("Name - "+name);
        tv6.setText("Salary - "+String.valueOf(salary));

    }
}
