package com.example.vikash.zingohotel;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AuthActivity extends AppCompatActivity {

    private Toolbar toolbar;
    FirebaseAuth mAuth;
    LinearLayout otpLayout;
    EditText phoneNo_et, otp_et;
    ProgressBar phoneNoPgBar, otpPgBar;
    Button verifyOtp_btn;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private int btnType = 0; // when btnType=0 it acts as verify mobile otherwise verify otp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        toolbar = findViewById(R.id.auth_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.mobile_verify);

        otpLayout = findViewById(R.id.ll_otp);
        phoneNo_et= findViewById(R.id.phoneno);
        otp_et = findViewById(R.id.otp);
        phoneNoPgBar = findViewById(R.id.mobile_pb);
        otpPgBar = findViewById(R.id.otpPgBar);
        verifyOtp_btn = findViewById(R.id.verify_otp);

        mAuth = FirebaseAuth.getInstance();

        verifyOtp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnType==0){
                    verify();
                }else{
                    btnType = 0;
                    verifyOtp_btn.setEnabled(false);
                    otpPgBar.setVisibility(View.VISIBLE);
                    verifyOtp();
                }
            }
        });
    }

    // when user have to enter the otp manually
    private void verifyOtp() {
        String OTP = otp_et.getText().toString();
        if(OTP.isEmpty()){
            otp_et.setError("Empty !");
        }
        else {
            otp_et.setError(null);
            otp_et.clearFocus();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, OTP);
            signInWithPhoneAuthCredential(credential);
        }

    }

    private void verify() {

        String phoneNo =  phoneNo_et.getText().toString();
        if(phoneNo.isEmpty()){
          phoneNo_et.setError("Empty !");
        }
        else {
            phoneNoPgBar.setVisibility(View.VISIBLE);
            phoneNo_et.setEnabled(false);
            verifyOtp_btn.setEnabled(false);
            phoneNo_et.setError(null);//removes error
            phoneNo_et.clearFocus();  //clear focus from edittext
            generateOtp(phoneNo);
        }
    }


    private void generateOtp(String phoneNo) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo, // phone no to verify
                60,   // timeout duration
                TimeUnit.SECONDS, //Unit of timeout
                this, // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        // This callback will be invoked in two situations:
                        // 1 - Instant verification. In some cases the phone number can be instantly
                        //     verified without needing to send or enter a verification code.
                        // 2 - Auto-retrieval. On some devices Google Play services can automatically
                        //     detect the incoming verification SMS and perform verification without
                        //     user action.
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                           showErrorMsg();
                    }

                     // when auto rechecking doesnot work this method will be called
                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        // The SMS verification code has been sent to the provided phone number, we
                        // now need to ask the user to enter the code and then construct a credential
                        // by combining the code with a verification ID.

                        // Save verification ID and resending token so we can use them later
                        mVerificationId = verificationId;
                        mResendToken = token;

                        btnType=1;

                        otpLayout.setVisibility(View.VISIBLE);
                        otpPgBar.setVisibility(View.VISIBLE);
                        verifyOtp_btn.setText(R.string.verifyotp);
                        verifyOtp_btn.setEnabled(true);

                        // ...
                    }
                }
        );

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //FirebaseUser user = task.getResult().getUser();
                            // Sign in success, update UI with the signed-in user's information
                            Intent successIntent = new Intent(AuthActivity.this, SignUpActivity.class);
                            successIntent.putExtra("MobNo",phoneNo_et.getText().toString());
                            startActivity(successIntent);
                            finish();

                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                              showErrorMsg();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    //Showing error msg when verification fails
    private void showErrorMsg() {
        final Snackbar snackbar = Snackbar
                .make(findViewById(R.id.auth_layout), "Something Went Wrong !" , Snackbar.LENGTH_LONG );
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


}
