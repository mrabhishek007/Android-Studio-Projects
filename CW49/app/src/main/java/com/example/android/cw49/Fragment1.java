package com.example.android.cw49;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1 extends Fragment {

EditText et;
Button bt;
Context context;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1 =  inflater.inflate(R.layout.fragment_fragment1, container, false);
        et = v1.findViewById(R.id.et1);
        bt = v1.findViewById(R.id.bt1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               String data =  et.getText().toString().trim();
                if(data.isEmpty())
                {
                    et.setError("Empty Field Not Allowed");
                }
                else
                {
                   MainActivity ref =  (MainActivity)context;
                   ref.data(data);
                   et.setText("");
                }
            }
        });



        return  v1;
    }

}
