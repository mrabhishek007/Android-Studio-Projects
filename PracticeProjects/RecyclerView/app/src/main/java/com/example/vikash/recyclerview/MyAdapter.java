package com.example.vikash.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<UserModel> list;
    Context context;

    MyAdapter(List<UserModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_list_ui,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        UserModel model = list.get(position);

//        myViewHolder.profile_img.setImageResource(model.getProfile_img());

        Glide.with(context).load(model.getProfile_img()).into(myViewHolder.profile_img);
        myViewHolder.description_txt.setText(model.getDescription());
        myViewHolder.name_txt.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
