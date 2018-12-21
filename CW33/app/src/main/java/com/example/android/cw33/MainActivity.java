package com.example.android.cw33;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button bt;
    String json_url="https://api.myjson.com/bins/18tbrf";
    String json_string="";
    String text="";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= findViewById(R.id.textView);
        bt= findViewById(R.id.button);
        MYJson mj = new MYJson();
       mj.execute();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(text);
            }
        });
    }


    public class MYJson extends AsyncTask<Void,Void,Void>
    {


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
           pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("please wait");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                //Reading JSON From URL

                URL url = new URL(json_url);
               URLConnection uc=  url.openConnection();
                HttpURLConnection hurl = (HttpURLConnection)uc;
               InputStream is = hurl.getInputStream();
               InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                int no =br.read();
                while(no!=-1)
                {
                    json_string= json_string+(char)no;
                    no = br.read();
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {

            super.onPostExecute(aVoid);

            try
            {
                JSONObject jobj = new JSONObject(json_string);
                JSONArray ja = jobj.getJSONArray("data");
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo = ja.getJSONObject(i);
                      String id = jo.getString("id");
                      String name = jo.getString("name");
                      String year = jo.getString("year");
                      String color=jo.getString("color");
                      String pval=jo.getString("pantone_value");
                      text+=id+"\n"+name+"\n"+year+"\n"+color+"\n"+pval;
                }
                pd.dismiss();

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }


        }
    }












}





