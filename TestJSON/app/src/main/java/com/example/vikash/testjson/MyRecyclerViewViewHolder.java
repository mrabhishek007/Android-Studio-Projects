package com.example.vikash.testjson;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecyclerViewViewHolder extends RecyclerView.ViewHolder {

   public TextView id,categ;
   public ImageView brandimg;
   public ViewPager viewPager;

    public MyRecyclerViewViewHolder(View itemView) {

        super(itemView);

        id = itemView.findViewById(R.id.tv1);
        categ = itemView.findViewById(R.id.tv2);
        brandimg = itemView.findViewById(R.id.iv);
        viewPager = itemView.findViewById(R.id.id_viewpager);

    }
}
