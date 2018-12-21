package com.example.android.cw32;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.HashMap;

public class
MainActivity extends AppCompatActivity {
    String JSON_URL = "http://services.groupkt.com/country/get/all";
    String JSON_DATA = "";
    ProgressDialog pd;
    int ids[] = {R.id.tv1, R.id.tv2, R.id.tv3};
    String keys[] = { "k1", "k2", "k3" };
    ArrayList al;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        al = new ArrayList();
        MyJSONReader mjr = new MyJSONReader();
        mjr.execute();
    }

    public class MyJSONReader extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://services.groupkt.com/country/get/all");
                URLConnection uc = url.openConnection();
                HttpURLConnection urlc = (HttpURLConnection) uc;
                InputStream is = urlc.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                int no = br.read();
                while (no != -1) {
                    JSON_DATA += (char) no;
                    no = br.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                JSONObject jo = new JSONObject(JSON_DATA);
                JSONObject jobj = jo.getJSONObject("RestResponse");

                JSONArray jary = jobj.getJSONArray("result");

                for (int i = 0; i < jary.length(); i++)
                {
                    JSONObject jobj1 = jary.getJSONObject(i);
                    String name = jobj1.getString("name");
                    String al2code = jobj1.getString("alpha2_code");
                    String al3code = jobj1.getString("alpha3_code");
                    HashMap hm = new HashMap();
                    hm.put(keys[0], name);
                    hm.put(keys[1], al2code);
                    hm.put(keys[2], al3code);
                    al.add(hm);

                }
                SimpleAdapter sa = new SimpleAdapter(MainActivity.this, al, R.layout.mylist_style, keys, ids);
                lv.setAdapter(sa);
                pd.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
