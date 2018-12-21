//Working with internal storage.Reading and writing data in internal subfoldera(cuustom location)

package android.example.com.cwp75;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       et1 =  findViewById(R.id.et1);
       et2 =  findViewById(R.id.et2);

       findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
              String uname =  et1.getText().toString().trim();
              String pass =  et2.getText().toString().trim();
               if(uname.equals("sathya")&&pass.equals("tech"))
               {


                   try
                   {
                       loginDetails();
                       finish();
                       startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
                   }
                   catch (FileNotFoundException e)
                   {
                       e.printStackTrace();
                   } catch (IOException e)
                   {
                       e.printStackTrace();
                   }


               }
               else
               {
                   Toast.makeText(MainActivity.this, "Inavlid user", Toast.LENGTH_SHORT).show();
               }

           }
       });

    }


    protected void loginDetails() throws IOException {
        Calendar c = Calendar.getInstance();
        String date  ="Date - "+c.get(java.util.Calendar.DATE)+"/"+(c.get(java.util.Calendar.MONTH)+1)+"/"+c.get(java.util.Calendar.YEAR);
        String time ="Time - "+c.get(java.util.Calendar.HOUR_OF_DAY)+":"+c.get(java.util.Calendar.MINUTE)+":"+c.get(java.util.Calendar.SECOND);
        String msg = date+"\n"+time+"\n";
        byte data[]=msg.getBytes();


              //Writing Data into the Custom internal Location


        File f1 = getFilesDir();//we can also get path name using getAbsolutePath();
        File f = new File(f1+"/Myfiles/Login");
        f.mkdirs();
       // FileOutputStream fos = new FileOutputStream(f+"/login_status",true); //for append mode
        FileOutputStream fos = new FileOutputStream(f+"/login_status");//for overriding mode(Private mode)
        fos.write(data);
        fos.close();


      /** Writing data in default Internal Location

      //  FileOutputStream fos = openFileOutput("login_time.txt",MODE_PRIVATE);//Replaes the old data with new data
        FileOutputStream fos = openFileOutput("login_time.txt",MODE_APPEND);//Will add  new data into the file
        fos.write(data);
        fos.close();
                          */

        Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show();



    }
}
