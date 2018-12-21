package com.example.vikash.zingohotel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vikash.zingohotel.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    SharedPreferences preferences;
    TextView signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        signOut = findViewById(R.id.sign_out);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().clear().apply();
                auth.signOut();
                sendToAuth();
            }
        });
    }

    private void loadUI(UserModel user) {
        String fullName = user.getFirstName()+ "  " + user.getMiddleName() + " " + user.getLastName();
        TextView travelId =    findViewById(R.id.travelid_txt);
        TextView name =        findViewById(R.id.fullname_txt);
        TextView dob =         findViewById(R.id.dob_txt);
        TextView mobile =      findViewById(R.id.mobileno_txt);
        TextView email =       findViewById(R.id.email_txt);
        TextView gender =      findViewById(R.id.gender_txt);
        TextView location =    findViewById(R.id.location_txt);

        travelId.setText("Travel ID : " + user.getTravellerId());
        name.setText("Full Name : " + fullName);
        dob.setText("D.O.B : " + user.getDOB());
        mobile.setText("Mobile No : " + user.getPhoneNumber());
        email.setText("Email : " + user.getEmail());
        gender.setText("Mobile No : " + user.getGender());
        location.setText("Place : " + user.getPlaceName());

        // Animations
        travelId.setTranslationX(-500);
        name.setTranslationX(-800);
        dob.setTranslationX(-1000);
        mobile.setTranslationX(-1200);
        email.setTranslationX(-1400);
        gender.setTranslationX(-1600);
        location.setTranslationX(-1800);

        travelId.animate().translationXBy(500).setDuration(500);
        name.animate().translationXBy(800).setDuration(600);
        dob.animate().translationXBy(1000).setDuration(700);
        mobile.animate().translationXBy(1200).setDuration(800);
        email.animate().translationXBy(1400).setDuration(900);
        gender.animate().translationXBy(1600).setDuration(1000);
        location.animate().translationXBy(1800).setDuration(1100);


    }

    private void sendToAuth() {
//        Intent authIntent = new Intent(MainActivity.this, SignUpActivity.class);
        Intent authIntent = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(authIntent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();

//        FirebaseUser currentUser = auth.getCurrentUser();
//        if(currentUser == null){
//            sendToAuth();
//        }

        preferences = getSharedPreferences("UserLog",MODE_PRIVATE);
        String userJSonData = preferences.getString("json",null);

        if(userJSonData == null){
            sendToAuth();
        }else {
            Gson gson = new Gson();
            UserModel user = gson.fromJson(userJSonData,UserModel.class);
            loadUI(user);
        }
    }

}
