package com.example.android.cwp20;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

            ListView lv;
    String tv[]={"Game Of Thrones","prison Break","Daredevil","The Da-Vinchi","Sherlok","Vikings","Merlin","The Defenders","WestWorld","Iron Fist","Luke Cage"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView)findViewById(R.id.lv);
        ArrayAdapter ad = new ArrayAdapter(this,R.layout.my_list_view,tv);
        lv.setAdapter(ad);
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.bookmyshow);
        lv.addHeaderView(iv);
        Button b = new Button(this);
        b.setText("CALL US");
        b.setTextColor(Color.WHITE);
        b.setTextSize(10f);
        lv.addFooterView(b);
        b.setBackgroundColor(Color.BLACK);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8669474954"));
                startActivity(i);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position!=0)
                {
                Toast.makeText(MainActivity.this, tv[position-1],Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
