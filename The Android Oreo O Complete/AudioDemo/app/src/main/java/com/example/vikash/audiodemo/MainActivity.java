package com.example.vikash.audiodemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button play_btn,pause_btn ;
    private SeekBar volumeSeekbar,musicControllerSeekbar;

    MediaPlayer mediaPlayer ;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          play_btn = findViewById(R.id.play_btn) ;
          pause_btn = findViewById(R.id.pause_btn) ;
          mediaPlayer = MediaPlayer.create(this,R.raw.oldcar) ;

          //AudioManager to get control volume of a device
          audioManager = (AudioManager)getSystemService(AUDIO_SERVICE) ;
          int device_max_volume =   audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //max volume
          int device_current_volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //current volume

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        //Music Controller Seekbar(Seekbar 2)
        musicControllerSeekbar = findViewById(R.id.song_ctrl_seekbar) ;
        int song_duration = mediaPlayer.getDuration();
        musicControllerSeekbar.setMax(song_duration);
        musicControllerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                musicControllerSeekbar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,1000);



        //Volume Controller Seekbar (Seekbar 1)
        volumeSeekbar = findViewById(R.id.volume_ctrl_seekbar) ;
        volumeSeekbar.setProgress(device_current_volume);
        volumeSeekbar.setMax(device_max_volume);
        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
