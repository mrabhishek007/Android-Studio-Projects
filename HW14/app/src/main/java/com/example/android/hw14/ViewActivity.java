package com.example.android.hw14;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
        mname= b.getString("key1");
        String rating ="Rating : "+b.getDouble("key2");
        int pos = b.getInt("key3");
        int ipos=images[pos];


        ArrayList al = new ArrayList();
        HashMap hm= new HashMap();
        hm.put("key1",mname);
        hm.put("key2",rating);
        hm.put("key3",ipos);
        al.add(hm);

        SimpleAdapter sa = new SimpleAdapter(this,al,R.layout.listview_style,keys,ids);
        lv.setAdapter(sa);

    }
}
