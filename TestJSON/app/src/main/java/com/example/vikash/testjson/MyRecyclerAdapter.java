package com.example.vikash.testjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikash.testjson.model.Brand;
import com.example.vikash.testjson.model.Brand_;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerViewViewHolder> {

    Brand brand ;
    Context context;

    public MyRecyclerAdapter(Context context, Brand brand) {
        this.brand = brand;
        this.context = context;
    }

    @NonNull
    @Override
    public MyRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v =   LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item_list,parent,false);
        return new MyRecyclerViewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewViewHolder holder, int position) {

        List<Brand_> brands  = (List<Brand_>)brand.getBrand().get(position);

        Brand_ b =  brands.get(position);


        holder.id.setText(b.getId());
        holder.categ.setText(b.getCategory());

    }

    @Override
    public int getItemCount()
    {
       return 0;
    }
}
