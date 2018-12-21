package com.example.vikash.fadinganimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView iv1,iv2;

    boolean img1_showing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       iv1 =  findViewById(R.id.imageView1) ;
       iv2 =  findViewById(R.id.imageView2) ;

//       iv1.animate().translationXBy(-1000).setDuration(3000);   // Animation will finish under 3 sec in -ve x axis

//        iv1.animate().rotation(2700).alpha(0).setDuration(2000);   //image will move rounded 2700 degrees in  4 sec

//        iv1.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);  //images will shrink half size in 2 sec

          iv1.setTranslationX(-1000);
          iv1.animate().translationXBy(1000).rotation(3600).scaleX(0.3f).scaleY(0.3f).setDuration(3000) ; //all above effects at same time

















/*       iv1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

//               changeImageWithDelay();

           }
       });*/


    }

    private void changeImageWithDelay() {

        if(img1_showing){
            img1_showing =false ;
            iv1.animate().alpha(0).setDuration(2000) ;
            iv2.animate().alpha(1).setDuration(2000) ;
        }else{
            img1_showing =true ;
            iv2.animate().alpha(0).setDuration(2000) ;
            iv1.animate().alpha(1).setDuration(2000) ;
        }
    }

}
