package com.example.vikash.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar timer_seekBar;
    private TextView timer_txtView;
    private Button timer_btn;
    public Boolean isTimerActive = false;
    public CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer_seekBar = findViewById(R.id.seekbar_timer);
        timer_txtView = findViewById(R.id.timer_txtview);
        timer_btn = findViewById(R.id.timer_btn);

        timer_seekBar.setMax(300);
        timer_seekBar.setProgress(30);

        timer_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        timer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTimerActive){
                    isTimerActive=true;
                    timer_seekBar.setEnabled(false);
                    timer_btn.setText("STOP!");

                    countDownTimer= new CountDownTimer(timer_seekBar.getProgress()*1000+100,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            updateTimer((int) millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {
                            MediaPlayer player = MediaPlayer.create(MainActivity.this,R.raw.air_horn);
                            player.start();
                            timer_seekBar.setProgress(30);
                            isTimerActive=false;
                            timer_seekBar.setEnabled(true);
                            timer_btn.setText("START");

                        }
                    };
                    countDownTimer.start();
                }
                else{

                    timer_seekBar.setProgress(30);
                    isTimerActive=false;
                    timer_seekBar.setEnabled(true);
                    timer_btn.setText("START");
                    countDownTimer.cancel();
                }

            }
        });
    }

    private void updateTimer(int progress) {
        int minutes = progress/60 ;
        int seconds = progress - (minutes*60);
        String secInString = String.valueOf(seconds);
        if(seconds<=9){
            secInString="0"+secInString;
        }
        timer_txtView.setText(minutes+ ":" +secInString);
    }
}
