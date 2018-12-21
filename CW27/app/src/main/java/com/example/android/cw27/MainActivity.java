//Working With Snackbar
package com.example.android.cw27;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1= findViewById(R.id.button);
        Button b2= findViewById(R.id.button2);
        Button b3= findViewById(R.id.button3);
        Button b4= findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar sb = Snackbar.make(v,"I am a Snackbar",Snackbar.LENGTH_SHORT);
                sb.show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar sb = Snackbar.make(v,"I am snackbar with button",Snackbar.LENGTH_INDEFINITE);
                sb.setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sb.dismiss();
                    }
                });
                sb.setActionTextColor(Color.GREEN);
                sb.show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              Snackbar sb =Snackbar.make(v,"I am a snackbar with  BG color ",Snackbar.LENGTH_SHORT);
              View v1 = sb.getView();
             v1.setBackgroundColor(Color.RED);

              sb.show();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Resources rs = getResources();
               Drawable dw =rs.getDrawable(R.drawable.ix);
               Snackbar sb = Snackbar.make(v,"Snackbar with img",Snackbar.LENGTH_SHORT);
               View v1 =sb.getView();
               v1.setBackground(dw);
               sb.show();
            }
        });



    }
}
