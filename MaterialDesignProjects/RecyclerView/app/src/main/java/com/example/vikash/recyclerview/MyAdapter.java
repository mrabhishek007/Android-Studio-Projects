package com.example.vikash.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<UserModel> userModelList ;
    Context context;

    public MyAdapter(List<UserModel> userModelList,Context context) {
        this.userModelList = userModelList;
        this.context  = context ;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        UserModel user =  userModelList.get(position);

       holder.userName.setText(user.getUserName());
       holder.userDescription.setText(user.getUserDescription());
       Glide.with(context).load(user.getUserImage()).into(holder.userProfileImg);

         // Implementing action on reycler view
       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(context, "Clicked at location "+ (position+1), Toast.LENGTH_SHORT).show();
           }
       });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }
}
