package com.example.android.cw45;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment
{


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.e("----","onAttch--Fragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("----","onCreate--Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_my, container, false);
        Log.e("----","onCreateView--Fragment");
        return  v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Log.e("----","onActivityCreated--Fragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("----","onStart--Fragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("----","onResume--Fragment");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.e("----","onPause--Fragment");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("----","onStop--Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("----","onDestroyView--Fragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("----","onDestroy--Fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("----","onDetach--Fragment");
    }
}
