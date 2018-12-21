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
public class MyAdminFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        View v1 =  inflater.inflate(R.layout.fragment_my_admin, container, false);
        final Context ct =v1.getContext();

        final EditText et1 = v1.findViewById(R.id.et1);
        final EditText et2 = v1.findViewById(R.id.et2);
        final TextInputLayout til1=v1.findViewById(R.id.til1);
        final TextInputLayout til2=v1.findViewById(R.id.til2);
        Button bt1= v1.findViewById(R.id.b6);

        bt1.setOnClickListener(new View.OnClickListener()
        {
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
                            if ((username.equalsIgnoreCase("Admin")) && (pass.equals("12345")))
                            {
                                Toast.makeText(ct, R.string.loginsuccess, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ct,WelcomeAdmin.class);
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
