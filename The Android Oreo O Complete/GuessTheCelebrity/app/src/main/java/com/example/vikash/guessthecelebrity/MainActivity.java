package com.example.vikash.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView celebrity_imageView;
    Button btn0,btn1,btn2,btn3;
    String result;
    ArrayList<String> img_url_list = new ArrayList<>();
    ArrayList<String> celb_name_list = new ArrayList<>();
    int choosenCeleb=0;
    int currect_ans_loc = 0;
    String[] options_list = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignIds();
        fetchDataFromWeb();
        startQuiz();

    }

    private void startQuiz() {
        //   Selecting a random celebrity and showing into imageView  //

        btn0.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_bt3));
        btn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_bt1));
        btn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_bt2));
        btn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_bt0));

        Random random = new Random();
        choosenCeleb = random.nextInt( (celb_name_list.size()) );

        ImageDownloader imageDownloaderTask = new ImageDownloader();

        try{

        Bitmap bitmap_result = imageDownloaderTask.execute(img_url_list.get(choosenCeleb)).get();

        celebrity_imageView.setImageBitmap(bitmap_result);

        // Generating 4 options

        currect_ans_loc =  random.nextInt(4);

        for(int i=0;i<4;i++){

            if(i==currect_ans_loc){
                options_list[i] = celb_name_list.get(choosenCeleb);
            }
            else
            {
                int option =  random.nextInt(celb_name_list.size());
                while(option==choosenCeleb){
                    option =  random.nextInt(celb_name_list.size());
                }
                options_list[i] = celb_name_list.get(option);
            }
        }

        btn0.setText(options_list[0]);
        btn1.setText(options_list[1]);
        btn2.setText(options_list[2]);
        btn3.setText(options_list[3]);

        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void fetchDataFromWeb() {

        DownloadTask task = new DownloadTask();
        try {
            result =  task.execute("http://www.posh24.se/kandisar").get();
            String[] split_result = result.split("sidebarContainer");

            /*  Retrieving Url and Name from Website */

            Matcher img_Url = Pattern.compile("(<img src=\")(.*?)(\"\\salt)").matcher(split_result[0]);   //Here 1st bracket () is group(1).same as other brackets are group(2) and group(3)
            while(img_Url.find())
            {
                img_url_list.add(img_Url.group(2));
//                Log.e("imggroup",img_Url.group(2));
            }

            Matcher celb_name = Pattern.compile("(alt=\")(.*?)(\"/>)").matcher(split_result[0]);
            while(celb_name.find())
            {
                celb_name_list.add(celb_name.group(2));
//                Log.e("namegroup",celb_name.group(2));

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

                /* Button click action */

    public void checkAnswer(View view) {

        String tag = view.getTag().toString();

       if( tag.equals(String.valueOf(currect_ans_loc))){
           ((Button)view).setBackgroundColor(getResources().getColor(R.color.green_clr));
       }
       else
       {
           ((Button)view).setBackgroundColor(getResources().getColor(R.color.dark_red));

           switch (currect_ans_loc){

               case 0:
                   btn0.setBackgroundColor(getResources().getColor(R.color.green_clr));
                   break;
               case 1:
                   btn1.setBackgroundColor(getResources().getColor(R.color.green_clr));
                   break;
               case 2:
                   btn2.setBackgroundColor(getResources().getColor(R.color.green_clr));
                   break;
               case 3:
                   btn3.setBackgroundColor(getResources().getColor(R.color.green_clr));
                   break;
           }
       }

       btn0.setEnabled(false);
       btn1.setEnabled(false);
       btn2.setEnabled(false);
       btn3.setEnabled(false);

       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               startQuiz();
           }
       },3000);
    }



             /* ASYNCHRONOUS TASK */

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {

            String result="";

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                InputStream inputStream = connection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while ( data != -1 ){

                    result += (char)data;

                    data =  reader.read();
                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    }


    public class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

               Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);

               return bitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    private void assignIds() {
        celebrity_imageView = findViewById(R.id.celibrity_imgview);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
    }

}
