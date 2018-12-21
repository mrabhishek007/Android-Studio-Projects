package com.classroomproject.smartclassroom;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       pb = findViewById(R.id.mainprogressbar);


        Thread t = new Thread()
        {
            public void run()
            {
                int i;
                for (i = 1; i < 100; i++)
                {
                    try {
                        pb.setProgress(i);
                        Thread.sleep(10);

                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                finish();
                Intent intent = new Intent(MainActivity.this,UserSelectionActivity.class);
                startActivity(intent);

            }
        };
        t.start();


    }
}
