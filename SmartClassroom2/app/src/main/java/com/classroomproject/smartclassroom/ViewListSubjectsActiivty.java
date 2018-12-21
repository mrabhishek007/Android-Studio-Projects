package com.classroomproject.smartclassroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewListSubjectsActiivty extends AppCompatActivity {

    ListView lv;
    int[] ids = {R.id.student_lv_date_tv,R.id.student_lv_chapter_tv,R.id.student_lv_topics_tv};
    long x;
    String[] keys = {"KEY1","KEY2","KEY3"};
    String[] key1 = {"X1","X2","X3"};
    String[] topic={"Topics : RelativeLayout,LinearLayout,CoordinateLayout","Topics :Saving and retreiving data in Sqlite,Saving and retrieving image in Sqlite","Topics :AlertDialog,TimePickerDialog,ProcessDialog,ListView","Topics :Snackbar,TextInputLayout,TabControls,ColorResources,MenuResources","Topics :Internal Storage,SharedPrefences,External Storage","Topics :Creating a Thread,Runnable Interface,Thread Synchronization,Thread Sleep"};
    String[] chapter={"Chapter 1: Layouts","Chapter 2: SqliteDatbase","Chapter 3: Dialogs","Chapter 4: Material Design","Chapter 5: Storages","Chapter 6: Multithreading"};
    String[] date={"Date : 12-11-2017","Date : 14-11-2017","Date : 18-11-2017","Date : 22-11-2017","Date : 01-12-2017","Date : 15-12-2017"};
    ArrayList al;
    ArrayList al3;
    ProgressDialog progressDialog;

    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_subjects_actiivty);
        lv =  findViewById(R.id.view_all_sub1);
       // al3 = new ArrayList();
        al =new ArrayList();

        for(int i=0;i<date.length;i++)
        {
            HashMap hm = new HashMap();
            hm.put(keys[0],date[i]);
            hm.put(keys[1],chapter[i]);
            hm.put(keys[2],topic[i]);

            al.add(hm);
        }



        SimpleAdapter simpleAdapter = new SimpleAdapter(this,al,R.layout.student_home_listview_style2,keys,ids);
        lv.setAdapter(simpleAdapter);


        // Reetreiving subject data from intent page

        Intent intent =  getIntent();
        Bundle bundle =  intent.getExtras();
        sub =  bundle.getString("SUB");




    }




        /**
     MyFirebaseAsyncTask asyncTask = new MyFirebaseAsyncTask();
     asyncTask.execute();

    }//onCreate

     public  class MyFirebaseAsyncTask extends AsyncTask <Void,Void,Void>
     {

         @Override
         protected void onPreExecute()
         {
             super.onPreExecute();
             progressDialog = new ProgressDialog(ViewListSubjectsActiivty.this,R.style.MyAlertDialogStyle);
             progressDialog.setMessage("Please Wait");
             progressDialog.setCancelable(false);
             progressDialog.show();

         }

         @Override
         protected Void doInBackground(Void... voids)
         {

             DatabaseReference dr =  FirebaseDatabase.getInstance().getReference("Subject_name").child(sub);
             dr.addChildEventListener(new ChildEventListener()
             {
                 @Override
                 public void onChildAdded(DataSnapshot dataSnapshot, String s)
                 {
                       x =   dataSnapshot.getChildrenCount();

                       if(x>0)
                       {
                            for(DataSnapshot childDataSnapshot :dataSnapshot.getChildren())
                            {

                               String st =  childDataSnapshot.getKey();
                               al.add(st);
                               Log.e("--->stname",""+st);

                            }


                           Log.e("-->","alistsize:::"+al.size());
                           progressDialog.dismiss();
                           SimpleAdapter simpleAdapter = new SimpleAdapter(ViewListSubjectsActiivty.this,al,R.layout.student_home_listview_style2,key1,ids);
                           lv.setAdapter(simpleAdapter);

                       }


                 }

                 @Override
                 public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                 }

                 @Override
                 public void onChildRemoved(DataSnapshot dataSnapshot) {

                 }

                 @Override
                 public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });


             return null;
         }


         @Override
         protected void onPostExecute(Void aVoid)
         {

             Log.e("--->toast",""+x);

             super.onPostExecute(aVoid);


         }
     }

         */

}
