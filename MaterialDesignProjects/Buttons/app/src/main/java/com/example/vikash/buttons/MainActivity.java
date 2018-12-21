package com.example.vikash.buttons;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AppCompatButton raisedButton ;
    AppCompatButton flatButton ;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       raisedButton =  findViewById(R.id.raised_btn) ;
       flatButton =  findViewById(R.id.flat_btn) ;
       fab =  findViewById(R.id.fab) ;

       raisedButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(MainActivity.this, "Raised Button clicked......", Toast.LENGTH_SHORT).show();
           }
       });

       flatButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(MainActivity.this, "Float Button Clicked.....", Toast.LENGTH_SHORT).show();
           }
       });


       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(MainActivity.this, "Floating action button....", Toast.LENGTH_SHORT).show();
           }
       });
    }
}
