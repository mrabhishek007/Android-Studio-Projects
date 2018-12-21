package com.classroomproject.smartclassroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewSingleLecture extends AppCompatActivity
{
    TextView sub_name_tv,sub_date_tv,chap_name_tv,topic_name_tv;
    String sub_name,sub_date,chap_name,topic_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_lecture);
       sub_name_tv =   findViewById(R.id.sub1_name_tv);
       sub_date_tv =   findViewById(R.id.date1_name_tv);
       chap_name_tv =  findViewById(R.id.chapter1_name_tv);
       topic_name_tv = findViewById(R.id.topic1_name_tv);


      Intent intent = getIntent();
     Bundle bundle =  intent.getExtras();
    sub_name   =    bundle.getString("L1");
    sub_date   =    bundle.getString("L2");
    topic_name =  bundle.getString("L3");
    chap_name  =   bundle.getString("L4");

    sub_name_tv.setText(sub_name);
    chap_name_tv.setText("Chapter-"+chap_name);
    sub_date_tv.setText("Date-"+sub_date);
    topic_name_tv.setText("Topic-"+topic_name);




    }
}
