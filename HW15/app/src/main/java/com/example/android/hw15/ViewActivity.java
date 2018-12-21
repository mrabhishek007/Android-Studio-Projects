package com.example.android.hw15;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewActivity extends AppCompatActivity {
     String mname;
    int images[]={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};
    ImageView iv;
    TextView tv1,tv2;
    double rating;
    ListView lv;
    String keys[]={"key1","key2","key3"};
    int ids[]={R.id.tv1,R.id.tv2,R.id.iv2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lv = (ListView)findViewById(R.id.lv1);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        ArrayList al =b.getStringArrayList("key1");
        SimpleAdapter sa = new SimpleAdapter(this,al,R.layout.listview_style,keys,ids);
        lv.setAdapter(sa);

    }
}
