package com.example.vikash.contactapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

//    android.R.id.content  ( Gives the root view of an activity directly )

    private Button load;
    private TextView show;
    String[] mColumnProjection = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
    };

//     String mSelectionClause =  ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = 'ARYAN NEW' ";  // where SQL similar

    String mSelectionClause =  ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";  // when ? then selection clause is necessary
    String[] mSelectionArgs =  new String[]{"ARYAN NEW"}; // Where name = Aryan New
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;


    private static  class ShowContactsTask extends AsyncTask<Void ,Void, String>{


        // user weakRef.get() to get strong reference
        WeakReference<MainActivity> mainActivityWeakReference;



        ShowContactsTask(MainActivity mainActivity){
            mainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        ProgressBar progressBar;

        @Override
        protected void onPreExecute() {

            MainActivity strongRef = mainActivityWeakReference.get();

            if(strongRef==null || strongRef.isFinishing()){
                return;
            }
            // dynamic progress bar
            FrameLayout layout = strongRef.findViewById(R.id.fl);
            progressBar = new ProgressBar(strongRef,null,android.R.attr.progressBarStyleLargeInverse);
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(100, 100);
            layoutParams.leftMargin= ( (layout.getMeasuredWidth()/2) - 50) ;
            layout.addView(progressBar,layoutParams);
        }

        @Override
        protected String doInBackground(Void... voids) {
            // It happens on seperate thread so we cannot touch view here
            MainActivity strongRef = mainActivityWeakReference.get();
            if(strongRef==null || strongRef.isFinishing()){
                return null;
            }
            return strongRef.showContacts();
        }

        @Override
        protected void onPostExecute(String result) {
            MainActivity strongRef = mainActivityWeakReference.get();
            if(strongRef==null || strongRef.isFinishing()){
                return;
            }
            strongRef.show.setText(result);
            progressBar.setVisibility(View.GONE);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = findViewById(R.id.load_contacts);
        show = findViewById(R.id.show_data);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkContactPermission();
                }else {
                    // showing contacts in a separate thread
                    ShowContactsTask showContactsTask = new ShowContactsTask(MainActivity.this);
                    showContactsTask.execute();
                }

            }
        });


    }

    private String showContacts() {

        ContentResolver mContentResolver = getContentResolver();
/*
        Cursor mCursor =  mContentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                mSelectionClause,
                mSelectionArgs,
                mOrderBy
        );


        if(mCursor!=null & mCursor.getCount()>0){

            StringBuilder contact  = new StringBuilder();

            while (mCursor.moveToNext()){
                contact.append(mCursor.getString(0) + ", " + mCursor.getString(1) + ", " + mCursor.getString(2) + mCursor.getString(3) + "\n" );
            }

            mCursor.close();
            return contact.toString();

        }else {

            mCursor.close();
            return "No contacts found...";

        }
*/


        Cursor cursor = mContentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                mOrderBy
        );

        StringBuilder contact = new StringBuilder();

        while (cursor.moveToNext()) {

            String name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            String id = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID));

            Cursor phones = getContentResolver()
                    .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                            null,
                            null);
            StringBuilder phoneNo = new StringBuilder();
            if (phones != null) {
                while (phones.moveToNext()) {
                    String phoneNumber = phones.getString(
                            phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneNo.append(phoneNumber + "  ");

                }
                phones.close();
            }

            contact.append("id: " + id + ", name : " + name + ", phones :" + phoneNo + "\n");

        }


        cursor.close();
        return contact.toString();

    }

    private void checkContactPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            // permission is granted
            Toast.makeText(this, "Permission was granted...", Toast.LENGTH_SHORT).show();

            // showing contacts in a separate thread
            ShowContactsTask showContactsTask = new ShowContactsTask(MainActivity.this);
            showContactsTask.execute();


        } else {

            // It means is user asking permission for first time or not.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                //if we asking permission for 2nd time then show a snackbar with permission request
                Snackbar.make(findViewById(android.R.id.content), "Asking Permission for loading contact 2nd time", Snackbar.LENGTH_INDEFINITE).setAction("Enable", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 101);
                    }
                }).show();

            } else {
                // if you asking permission for first time
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 101);

            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission is enabled
                Toast.makeText(this, "Permission granted now ...", Toast.LENGTH_SHORT).show();
                showContacts();

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                //User has deny from permission dialog
                Snackbar.make(findViewById(android.R.id.content), "You must provide Permission for loading contact", Snackbar.LENGTH_INDEFINITE).setAction("Enable", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 101);
                    }
                }).show();
            } else {

                // User has deny permission and checked never show permission dialog so you can redirect to Application settings page

                Snackbar.make(findViewById(android.R.id.content), "Please enable permission from settings otherwise you cannot use the application functionality ", Snackbar.LENGTH_INDEFINITE).setAction("Enable", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", MainActivity.this.getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, 102); // 102 request code check whether user has accepted permission or not
                    }
                }).show();

                // OR STOP THE  RELATED FUNCTIONALITY

            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102) {
            if(resultCode == RESULT_CANCELED){
//                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                checkContactPermission();
            }
        }
    }
}
