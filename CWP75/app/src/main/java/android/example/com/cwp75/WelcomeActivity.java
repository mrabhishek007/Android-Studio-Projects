package android.example.com.cwp75;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {

    TextView tv3;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv3 = findViewById(R.id.textView3);
        Button b2 = findViewById(R.id.button2);
        try {
          message =  readLastLoginStatus();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tv3.setText(message);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            }
        });


    }


    //reading data from internal custom location

    public String readLastLoginStatus() throws IOException
    {
        String msg ="";
        File f =  getFilesDir();
        String path = f.getAbsolutePath()+"/Myfiles/Login/login_status";
        FileInputStream fis = new FileInputStream(path);
        //FileInputStream fis = new FileInputStream(f+"/Myfiles/Login/login_status"); //also valid
        int x =fis.read();
        while(x!=-1)
        {
           char c =  (char)x;
           msg+=c;
           x=fis.read();

        }
        fis.close();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return msg;


    }
}
