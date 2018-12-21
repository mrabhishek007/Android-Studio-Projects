package com.example.vikash.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView userProfileImg;
    TextView userName;
    TextView userDescription;
    LinearLayout linearLayout;

    public MyViewHolder(View itemView) {

        super(itemView);

        userProfileImg = itemView.findViewById(R.id.profileimage_id);
        userName = itemView.findViewById(R.id.name_id);
        userDescription = itemView.findViewById(R.id.description_id);
        linearLayout = itemView.findViewById(R.id.recycler_item_id);
    }
}
