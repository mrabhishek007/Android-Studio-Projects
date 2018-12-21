package com.example.vikash.contentproviderwithloader;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.LoaderManager;

import java.util.ArrayList;

// Main advantage of loader is that thread is automatically managed by android framework and it provides live data i.e. any changes occurs on background will be reflected automatically

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //    android.R.id.content  ( Gives the root view of an activity directly )

    private Button load, add, update, delete;
    private TextView show;
    private EditText mContentEditText;
    boolean isFirstTimeLoaded = false;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = findViewById(R.id.load_contacts);
        show = findViewById(R.id.show_data);
        add = findViewById(R.id.button);
        update = findViewById(R.id.button3);
        delete = findViewById(R.id.button2);
        mContentEditText = findViewById(R.id.modify_contact_txt);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkContactPermission("load");
                }else {
                    loadLoader();

                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkContactPermission("add");
                }else {
                    addContacts();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkContactPermission("delete");
                }else {
                    deleteContacts();
                }


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkContactPermission("update");
                }else {
                    updateContacts();
                }

            }
        });
    }


    private void deleteContacts() {
        String data = mContentEditText.getText().toString();
        if(!data.isEmpty()){
            String whereClause = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY  + " = " + data ;
            // DELETE FROM <TABLENAME> WHERE COLUMNAME = SELECTIONVALUE

            try {
                getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI,whereClause, null );
                Toast.makeText(this, "Deletion successful....", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(this, ""+ e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Empty !", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateContacts() {

        String [] updateValue=mContentEditText.getText().toString().split(" ");
        // Fromat will be  { id  name } from edittext where id =current name & name = futre updated name

        if(updateValue.length==2){

            try{

                String contactId = updateValue[0];
                String whereClause = ContactsContract.RawContacts._ID + " = ?" ;
                String[] params = {contactId};

                String updatedName = updateValue[1];

                ContentValues contentValues = new ContentValues();
                contentValues.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, updatedName);
                getContentResolver().update(ContactsContract.RawContacts.CONTENT_URI,contentValues,whereClause, params );


            }catch (Exception e){
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, "Please enter in correct format", Toast.LENGTH_SHORT).show();
        }

    }


    private void addContacts() {


        String contactName =mContentEditText.getText().toString();

        if(!contactName.isEmpty()){

            try{

                ArrayList<ContentProviderOperation> cops = new ArrayList<>();
                cops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,"accountname@gmail.com")
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,"com.google")
                        .build());
                cops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contactName)
                        .build());

                getContentResolver().applyBatch(ContactsContract.AUTHORITY, cops);


            }catch (Exception e){
                Toast.makeText(this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
        }

    }


    private void loadLoader() {
        if(!isFirstTimeLoaded){
            // initializing the loader, its life cycle method will be triggered after initialization of loader
            getSupportLoaderManager().initLoader(1,null, MainActivity.this); // 1 is id to differentiate,if we are using multiple loaders
            isFirstTimeLoaded = true;
        }else {
            getSupportLoaderManager().restartLoader(1, null, MainActivity.this);
        }
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        // if id =1 then we are initializing contact loader
        if(i==1){
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, mColumnProjection, null, null, mOrderBy);
        }

        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {


        if(cursor!=null & cursor.getCount()>0){
            StringBuilder contact  = new StringBuilder();

            while (cursor.moveToNext()){
                contact.append(cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) + cursor.getString(3) + "\n" );
            }
            show.setText(contact.toString());


        }else {
            show.setText("No contacts found...");

        }

//        cursor.close();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }





    private void showContacts() {

/*
        ContentResolver mContentResolver = getContentResolver();

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


/*        Cursor cursor = mContentResolver.query(
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
        return contact.toString();*/

    }

    private void checkContactPermission(String requestType) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            // permission is granted
//            Toast.makeText(this, "Permission was granted...", Toast.LENGTH_SHORT).show();

            switch (requestType){
                case "load":
                    loadLoader();
                    break;
                case "add":
                    addContacts();
                    break;
                case "update":
                    updateContacts();
                    break;
                case "delete":
                    deleteContacts();
                    break;
                default:
                    Toast.makeText(this, "Please select any operation...", Toast.LENGTH_SHORT).show();

            }

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

                Snackbar.make(findViewById(android.R.id.content), "Please enable permission from settings ", Snackbar.LENGTH_INDEFINITE).setAction("Enable", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", MainActivity.this.getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, 102); // 102 request code check whether user has accepted permission or not
                    }
                }).show();
                // OR STOP THE APP RELATED FUNCTIONALITY
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102) {
            if(resultCode == RESULT_CANCELED){
                checkContactPermission(null);
            }
        }
    }




}
