package com.example.vikash.zingohotel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.vikash.zingohotel.models.UserModel;
import com.example.vikash.zingohotel.service.RetrofitClient;
import com.google.gson.Gson;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    SharedPreferences.Editor sharedPreEditor;

    String phoneNo;
    EditText firstName_et, lastName_et, middleName_et, location_et, dob_et, email_et, phone_et;
    RadioButton male_btn, female_btn;
    Button signup_btn;
    RadioGroup radioGroup;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SharedPreferences sharedPreferences = getSharedPreferences("UserLog",MODE_PRIVATE);
        sharedPreEditor = sharedPreferences.edit();

        phoneNo = getIntent().getStringExtra("MobNo");

        firstName_et = findViewById(R.id.first_name);
        middleName_et = findViewById(R.id.middle_name);
        lastName_et = findViewById(R.id.lastname);
        location_et = findViewById(R.id.location);
        phone_et = findViewById(R.id.mobile);
        dob_et = findViewById(R.id.dob);
        email_et = findViewById(R.id.email);
        male_btn = findViewById(R.id.male);
        female_btn = findViewById(R.id.female);
        signup_btn = findViewById(R.id.btn_signUp);
        radioGroup = findViewById(R.id.radiogroup);

        phone_et.setText(phoneNo);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateCredetails();
            }
        });
    }

    private void validateCredetails() {

        String firstName = firstName_et.getText().toString();
        String lastName = lastName_et.getText().toString();
        String middleName = middleName_et.getText().toString();
        String location = location_et.getText().toString();
        String email = email_et.getText().toString();
        String dob = dob_et.getText().toString();
//        String phoneNo = phone_et.getText().toString();
        int id = radioGroup.getCheckedRadioButtonId();
        String gender;

        if(male_btn.isChecked()){
               gender   = "Male";
        }else {
               gender = "Female";
        }

        if(firstName.isEmpty() || lastName.isEmpty() || middleName.isEmpty() || location.isEmpty() || email.isEmpty() || dob.isEmpty() || phoneNo.isEmpty() || id==-1 ){
            showMsg("One or More field is empty !");
        }else{

            if(!validateEmail(email)){
                showMsg("Invalid Email");
                email_et.requestFocus();
            }
            else {
                // if everything is valid then post method triggers
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setTitle("Registering User...");
                mProgressDialog.setMessage("Please wait while we create your account !");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                signUp(firstName, middleName, lastName, gender, dob, email, phoneNo, location);
            }
        }
    }


    private void signUp(String firstName, String middleName, String lastName, String gender, String dob, String email, String phoneNo, String location){

        Call<UserModel> call = RetrofitClient
                                .getRetrofitInstance()
                                .getApiService()
                                .createUser(
                                        firstName,
                                        middleName,
                                        lastName,
                                        gender,
                                        dob,
                                        email,
                                        phoneNo,
                                        location
                                );

        // To execute above post request following mathod will be called
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                showMsg("Sucessfull ! ");
                UserModel user = response.body();
                Gson gson = new Gson();
                String userJsonData = gson.toJson(user);
                sharedPreEditor.putString("json",userJsonData).commit();
                mProgressDialog.dismiss();

                Intent loginIntent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(loginIntent);
                finish();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                mProgressDialog.dismiss();
                Log.e("error-------", t.getMessage());
                showMsg("Something went wrong !");
            }
        });
    }

    //Showing error msg when verification fails
    private void showMsg(String msg) {
        final Snackbar snackbar = Snackbar
                .make(findViewById(R.id.signup_layout), msg , Snackbar.LENGTH_LONG );
        // Changing message text color
        snackbar.setActionTextColor(Color.YELLOW);
        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.GREEN);
        snackbar.show();
        snackbar.setAction("Close", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
    }

    private boolean validateEmail(String email)
    {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+[a-zA-Z0-9._]*@[a-zA-z]+([.][a-zA-z]+)+");//[a-zA-Z0-9]+ represents 1st character,[a-zA-Z0-9._]* represents remaining character
        Matcher matcher = pattern.matcher(email);

        if(matcher.find()&&matcher.group().equals(email))
        {
            return  true;
        }
        return  false;
    }


}
