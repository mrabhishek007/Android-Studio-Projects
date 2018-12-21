package com.example.android.cw58;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("login_status",MODE_PRIVATE);
                SharedPreferences.Editor spe = sp.edit();
                spe.putBoolean("key",false);
               // spe.remove("key"); //to delete key value from shared prefences
                // spe.clear();  //to clear all data from SharedPrefences
                spe.commit();
                //spe.apply();
                Intent i = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(WelcomeActivity.this, "Successfully Loggedout", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

}
