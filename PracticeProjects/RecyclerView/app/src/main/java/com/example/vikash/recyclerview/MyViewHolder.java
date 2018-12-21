package com.example.vikash.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView name_txt, description_txt;
    ImageView profile_img;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name_txt = itemView.findViewById(R.id.txt_name);
        description_txt = itemView.findViewById(R.id.txt_description);
        profile_img =  itemView.findViewById(R.id.profile_image);

    }
}
