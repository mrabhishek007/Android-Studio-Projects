package com.example.vikash.hoteldesignapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikash.hoteldesignapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmenitiesFragment extends Fragment {


    public AmenitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amenities, container, false);
    }

}
