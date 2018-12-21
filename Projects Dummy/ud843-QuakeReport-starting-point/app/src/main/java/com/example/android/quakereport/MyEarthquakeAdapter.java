package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vikash on 1/3/2018.
 */

public class MyEarthquakeAdapter extends ArrayAdapter<MyEarthquakeHandler>
{

    Context context;

    public MyEarthquakeAdapter(Activity context, ArrayList<MyEarthquakeHandler> handler)
    {
        super(context,0,handler);
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View v = convertView;
        if(v == null)
        {
          LayoutInflater lf =   LayoutInflater.from(context);
          v =  lf.inflate(R.layout.listview_style,parent,false);

          MyEarthquakeHandler currrent_eq = getItem(position);

            TextView tv1 =  (TextView) v.findViewById(R.id.tv1);
            TextView tv2 =  (TextView) v.findViewById(R.id.tv2);
            TextView tv3 =  (TextView) v.findViewById(R.id.tv3);
            TextView tv4 =  (TextView) v.findViewById(R.id.tv4);

           double mag1 =  Double.parseDouble(currrent_eq.getMagnitude());
           Double d = new Double(mag1);
           int mag_int = d.intValue();

           switch (mag_int)
           {
               case 1:
               {
                  GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                  gd.setColor(context.getResources().getColor(R.color.magnitude1));
                   break;
               }
               case 2:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude2));
                   break;
               }
               case 3:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude3));
                   break;
               }
               case 4:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude4));
                   break;
               }
               case 5:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude5));
                   break;
               }
               case 6:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude6));
                   break;
               }
               case 7:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude7));
                   break;
               }
               case 8:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude8));
                   break;
               }
               case 9:
               {
                   GradientDrawable gd =(GradientDrawable)tv1.getBackground();
                   gd.setColor(context.getResources().getColor(R.color.magnitude9));
                   break;
               }


               default:
               {

                       tv1.setBackgroundColor(context.getResources().getColor(R.color.magnitude1));

               }
           }


            tv1.setText(currrent_eq.getMagnitude());
            tv2.setText(currrent_eq.getExact_location());
            tv3.setText(currrent_eq.getCountry());
            tv4.setText(currrent_eq.getTime());

        }

        return v;
    }

}
