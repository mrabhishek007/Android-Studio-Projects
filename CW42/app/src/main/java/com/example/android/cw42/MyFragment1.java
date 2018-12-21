package com.example.android.cw42;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vikash on 11/19/2017.
 */

public class MyFragment1 extends Fragment
{

   public View onCreateView (LayoutInflater li , ViewGroup vg, Bundle b )
   {
       super.onCreateView(li,vg,b);
       View v1 = li.inflate(R.layout.fg1,vg,false);
       return v1;

   }




}
