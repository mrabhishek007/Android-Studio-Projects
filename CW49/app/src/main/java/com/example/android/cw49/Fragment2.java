package com.example.android.cw49;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment2 extends Fragment
{
   Context ct;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ct=context;
    }

    TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =  inflater.inflate(R.layout.fragment_fragment2, container, false);
       tv = v.findViewById(R.id.tv1);
       return  v;
    }

    void receiveData(String data)
    {
        tv.setText(data);
        Toast.makeText(ct, "Data Received", Toast.LENGTH_SHORT).show();

    }

}
