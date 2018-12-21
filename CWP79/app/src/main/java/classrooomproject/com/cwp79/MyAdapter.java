package classrooomproject.com.cwp79;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vikash on 12/29/2017.
 */


public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyHolder>

{

    String dir_names[] = {"Matt Reeves", "Neill Blomkamp", "Michael Bay", "Bryan Singer", "Brad Anderson", "Edward Zwick", "Marc Webb", "Nathan Greno", "Ron Howard", "Sam Raimi", "Matt Reeves", "Neill Blomkamp", "Michael Bay", "Bryan Singer", "Brad Anderson", "Edward Zwick", "Marc Webb", "Nathan Greno", "Ron Howard", "Sam Raimi", "Matt Reeves", "Neill Blomkamp", "Michael Bay", "Bryan Singer", "Brad Anderson", "Edward Zwick", "Marc Webb", "Nathan Greno", "Ron Howard", "Sam Raimi"};
    int dir_images[] = {R.drawable.one_1, R.drawable.two_2, R.drawable.three_3, R.drawable.four_4, R.drawable.five_5, R.drawable.six_6, R.drawable.seven_7, R.drawable.eight_8, R.drawable.nine_9, R.drawable.ten_10, R.drawable.one_1, R.drawable.two_2, R.drawable.three_3, R.drawable.four_4, R.drawable.five_5, R.drawable.six_6, R.drawable.seven_7, R.drawable.eight_8, R.drawable.nine_9, R.drawable.ten_10, R.drawable.one_1, R.drawable.two_2, R.drawable.three_3, R.drawable.four_4, R.drawable.five_5, R.drawable.six_6, R.drawable.seven_7, R.drawable.eight_8, R.drawable.nine_9, R.drawable.ten_10};
    String movie_names[] = {"Dawn of the Planet of the Apes", "District 9", "Transformers: Age of Extinction", "X-Men: Days of Future Past", "The Machinist", "The Last Samurai", "The Amazing Spider-Man 2", "Tangled", "Rush", "Drag Me to Hell", "Dawn of the Planet of the Apes", "District 9", "Transformers: Age of Extinction", "X-Men: Days of Future Past", "The Machinist", "The Last Samurai", "The Amazing Spider-Man 2", "Tangled", "Rush", "Drag Me to Hell", "Dawn of the Planet of the Apes", "District 9", "Transformers: Age of Extinction", "X-Men: Days of Future Past", "The Machinist", "The Last Samurai", "The Amazing Spider-Man 2", "Tangled", "Rush", "Drag Me to Hell"};
    int mov_images[] = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten, R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten, R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten};
    Context context;


    //first  create a class which extends RecyclerView.viewolder//first  create a class which extends RecyclerView.viewolder
    public static class MyHolder extends RecyclerView.ViewHolder {

        ImageView iv1, iv2;
        TextView tv1, tv2;

        public MyHolder(View viewholder) {
            super(viewholder);
            iv1 = viewholder.findViewById(R.id.iv1);
            iv2 = viewholder.findViewById(R.id.iv2);
            tv1 = viewholder.findViewById(R.id.tv1);
            tv2 = viewholder.findViewById(R.id.tv2);
        }

    }

    public MyAdapter(Context context)//getting context from MainActivity
    {

        this.context = context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        LayoutInflater li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View v = li.inflate(R.layout.my_card_style, parent, false);

        MyHolder myHolder = new MyHolder(v);

        return myHolder;

    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position)
    {
        holder.iv1.setImageResource(mov_images[position]);
        holder.iv2.setImageResource(dir_images[position]);
        holder.tv1.setText(movie_names[position]);
        holder.tv2.setText(dir_names[position]);
    }

    @Override
    public int getItemCount()
    {
        return movie_names.length;
    }


}
