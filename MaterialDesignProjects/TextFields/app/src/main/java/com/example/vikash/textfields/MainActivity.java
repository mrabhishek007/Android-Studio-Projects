package com.example.vikash.textfields;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText user;
    private TextInputLayout user_til,pwd_til;
    private AppCompatEditText password;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.username_textfield);
        password = findViewById(R.id.password_textfield);
        linearLayout = findViewById(R.id.ll);
        user_til = findViewById(R.id.username_til);
        pwd_til = findViewById(R.id.password_til);


/*    // Check whether edittext has focus or not. if edittext has focus it returns true,if focus is on other edittext it returns false

        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                System.out.println(b);
            }
        });
*/

        //If clicked outside any part of the edittext focus will be removed
        linearLayout.setOnClickListener(null);

        pwd_til.setCounterEnabled(true);  //Enable character counter on edittext
        pwd_til.setCounterMaxLength(8);

        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {


                if(user.getText().toString().isEmpty()){     //When focus is on edittext(Username) enable the error message
                    user_til.setErrorEnabled(true);
                    user_til.setError("Username is empty!");
                }
                else
                {
                    user_til.setErrorEnabled(false);
                }
                if(!hasfocus){   //When focus is removed disable the error message
                    user_til.setErrorEnabled(false);
                }

            }
        });

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(user.getText().toString().isEmpty()){    //When username is empty set error message,not activated util user enter atleast 1 word, use setOnFocusChangeListener() to detect when user click the edittext
                    user_til.setErrorEnabled(true);
                    user_til.setError("Username is empty!");
                }
                else
                {
                    user_til.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}
