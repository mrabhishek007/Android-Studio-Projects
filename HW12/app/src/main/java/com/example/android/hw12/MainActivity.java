package com.example.android.hw12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton b1,b2;
    ImageView iv1;
    TextView tv1;
    String names[] = {"Dawn of the Planet of the Apes","District 9","Transformers: Age of Extinction","X-Men: Days of Future Past","The Machinist","The Last Samurai","The Amazing Spider-Man 2","Tangled", "Rush","Drag Me to Hell","Matt Reeves","Neill Blomkamp","Michael Bay","Bryan Singer","Brad Anderson","Edward Zwick","Marc Webb","Nathan Greno","Ron Howard","Sam Raimi"};
    int images[] = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten,R.drawable.eleven,R.drawable.twelve,R.drawable.thirteen,R.drawable.fourteen,R.drawable.fifteen,R.drawable.sixteen,R.drawable.seventeen,R.drawable.eihteen,R.drawable.ninteen,R.drawable.twenty};
    int count=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 =(ImageButton)findViewById(R.id.b1);
        b2 =(ImageButton)findViewById(R.id.b2);
        iv1=(ImageView)findViewById(R.id.iv1);
        tv1 =(TextView)findViewById(R.id.tv1);

    }

    void backTask(View v)
    {
        --count;
        if(count<0)
        {
            Toast.makeText(this,"This is first Image",Toast.LENGTH_SHORT).show();
            ++count;
        }
        else {
            int x=count+1;
            iv1.setImageResource(images[count]);
            tv1.setText(String.valueOf(x)+" - "+names[count]);
        }
    }

    void nextTask(View v)
    {
        ++count;
        if(count>19)
        {
            Toast.makeText(this,"This is Last Image",Toast.LENGTH_SHORT).show();
            --count;
        }
        else
        {
        iv1.setImageResource(images[count]);
            tv1.setText(String.valueOf(count+1)+" - "+names[count]);
        }
    }

}
