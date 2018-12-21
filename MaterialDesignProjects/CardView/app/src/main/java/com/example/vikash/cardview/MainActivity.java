package com.example.vikash.cardview;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView cardView1 ;
    CardView cardView2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       cardView1 =  findViewById(R.id.card_view1_id) ;
       cardView2 =  findViewById(R.id.card_view2_id) ;


       cardView1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Snackbar.make(view,"CardView 1 is selected",Snackbar.LENGTH_LONG);
           }
       });

       cardView2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Snackbar.make(view,"CardView 2 is selected",Snackbar.LENGTH_LONG);

           }
       });



    }
}
