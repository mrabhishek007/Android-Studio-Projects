package com.example.vikash.progressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ProgressBar circular_progressBar;
    ProgressBar linear_progressBar;
    Handler handler,handler2;
    Timer timer,timer2;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CIRCULAR PROGRESS BAR
        circular_progressBar = findViewById(R.id.circular_progressbar_id) ;
        setCircularProgressBar();

//        LINEAR PROGRESS BAR
        linear_progressBar = findViewById(R.id.linear_progressBar_id) ;
        linear_progressBar.setMax(100);
        linear_progressBar.setProgress(0);
        linear_progressBar.setSecondaryProgress(0);   // Use this if you want secondary progress in a progressbar otherwise don't use it

        setLinearProgressBar();


    }

    private void setLinearProgressBar() {

       linear_progressBar.setVisibility(View.VISIBLE);
       handler2 = new Handler();
       timer2 = new Timer();

       timer2.schedule(new TimerTask() {
           @Override
           public void run() {

               handler2.post(new Runnable() {
                   @Override
                   public void run() {

                       if(++i<=100){
                           linear_progressBar.setProgress(i);
                           linear_progressBar.setSecondaryProgress(i+10);
                       } else{
                           timer2.cancel();
                           linear_progressBar.setVisibility(View.GONE);
                       }
                   }
               });


           }
       },1000,100);  //after 100milli sec run() will be executed again

    }


    private void setCircularProgressBar() {

        circular_progressBar.setVisibility(View.VISIBLE);
        handler = new Handler();
        timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        timer.cancel(); //disables the timer
                        circular_progressBar.setVisibility(View.GONE);
                    }
                });

            }
        },5000,1000);   //timer will be activited after 5 sec and handler.post() will run each time after 1 sec until timer.cancel().
    }//setProgressBar()
}
