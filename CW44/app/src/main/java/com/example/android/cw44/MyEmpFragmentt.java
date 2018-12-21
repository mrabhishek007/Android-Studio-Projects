package com.example.android.cw44;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyEmpFragmentt extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        View v1 =  inflater.inflate(R.layout.fragment_my_emp, container, false);
       final EditText et1=  v1.findViewById(R.id.et3);
       final EditText et2=  v1.findViewById(R.id.et4);
       final TextInputLayout til1 = v1.findViewById(R.id.til3);
       final TextInputLayout til2=v1.findViewById(R.id.til4);
        Button b = v1.findViewById(R.id.b7);
        final Context ct = v1.getContext();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                String username =et1.getText().toString().trim();
                String pass=et2.getText().toString().trim();
                if(username.isEmpty())
                {
                    til1.setError("Empty Username");
                    et1.requestFocus();
                }
                else
                {
                    til1.setErrorEnabled(false);
                    if(pass.isEmpty())
                    {
                        til2.setError("Empty Password ");
                        et2.requestFocus();
                    }
                    else

                    {
                        til2.setErrorEnabled(false);
                        if ((username.equalsIgnoreCase("Employee")) && (pass.equals("12345")))
                        {
                            Toast.makeText(ct, R.string.loginsuccess, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ct,WelcomeEmp.class);
                            startActivity(i);
                            et1.setText("");
                            et2.setText("");
                            et1.requestFocus();
                        } else {
                            Toast.makeText(ct, R.string.iuop, Toast.LENGTH_SHORT).show();
                            et1.setText("");
                            et2.setText("");
                            et1.requestFocus();
                        }
                    }

                }

            }
        });


        return  v1;
    }

}
