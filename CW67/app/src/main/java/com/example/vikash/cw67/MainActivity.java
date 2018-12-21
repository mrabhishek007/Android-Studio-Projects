//Working with internal storage

package com.example.vikash.cw67;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        String uname = et1.getText().toString().trim();
        String pass = et2.getText().toString().trim();
        if(uname.isEmpty())
        {
            et1.setError("Empty");
            et1.requestFocus();
        }
        else
            if(pass.isEmpty())
        {
            et2.setError("Empty");
            et2.requestFocus();
        }
        else {
                if (uname.equalsIgnoreCase("Sathya") && pass.equals("tech"))
                {

                    Calendar c = Calendar.getInstance();
                    String date  ="Date - "+c.get(Calendar.DATE)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
                    String time ="Time - "+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
                    String msg = date+"\n"+time;

                                      //Writing data in a file in Internal Storage

                    byte b[] =  msg.getBytes();
                    try
                    {
                       FileOutputStream fos =  openFileOutput("login_details",MODE_PRIVATE);
                       fos.write(b);
                       fos.close();
                        Toast.makeText(this, "File Written Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    finish();
                    startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
                }
                else
                {
                    Toast.makeText(this, "Invalid User or Password", Toast.LENGTH_SHORT).show();
                    et1.setText("");
                    et2.setText("");

                }

            }
    }
}
