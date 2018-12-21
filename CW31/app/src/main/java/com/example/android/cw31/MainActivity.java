//Working with WebView
package com.example.android.cw31;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    TextView tv1;
    Button b1,b2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv= findViewById(R.id.wv);
        iv= findViewById(R.id.iv1);
        b1= findViewById(R.id.b1);
        b2= findViewById(R.id.b2);
        tv1=findViewById(R.id.tv1);
        loadPage();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               boolean res=  wv.canGoBack();
               if(res)
               {
                   wv.goBack();
               }
               else
               {
                   Toast.makeText(MainActivity.this,"No History Found ",Toast.LENGTH_SHORT).show();
               }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean res2=   wv.canGoForward();
              if(res2)
              {
                  wv.goForward();
              }
              else
              {
                  Toast.makeText(MainActivity.this,"Plz choose a link to continue ",Toast.LENGTH_SHORT).show();
              }
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               boolean resx = checkInternet();
               if(resx)
               {
                   Toast.makeText(MainActivity.this,"You are Online now! ",Toast.LENGTH_SHORT).show();
                   loadPage();
               }
               else
               {
                   Toast.makeText(MainActivity.this,"No Connection! ",Toast.LENGTH_SHORT).show();
                   refresh();
               }

            }
        });
    }

    public void loadPage()
    {

        boolean res = checkInternet();
        if(res) {
            iv.setVisibility(View.GONE);
            tv1.setVisibility(View.GONE);
            wv.setVisibility(View.VISIBLE);
            wv.loadUrl("http://www.w3schools.com");
            WebViewClient wvc = new WebViewClient();
            wv.setWebViewClient(wvc);
            WebSettings ws = wv.getSettings();
            ws.setJavaScriptEnabled(true);
            ws.setBuiltInZoomControls(true);
            }
            else
        {
            refresh();
        }

    }

    public void refresh()
    {
            wv.setVisibility(View.GONE);
            iv.setVisibility(View.VISIBLE);
            tv1.setVisibility(View.VISIBLE);
    }

    public  boolean checkInternet()
    {
        // Checking Internet connection

        Object o = getSystemService(CONNECTIVITY_SERVICE);
        ConnectivityManager cm = (ConnectivityManager)o;
        NetworkInfo ninf = cm.getActiveNetworkInfo();

        if(ninf!=null)
        {

            return true;
        }
        else
        {
            return  false;

        }
    }// end of checkInternet

}
