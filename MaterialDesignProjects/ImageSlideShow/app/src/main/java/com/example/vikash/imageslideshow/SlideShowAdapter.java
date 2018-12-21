package com.example.vikash.imageslideshow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class SlideShowAdapter extends PagerAdapter {

    private int[] images = {
            R.drawable.angrybirds,
            R.drawable.cuttherope,
            R.drawable.worms3,
            R.drawable.clashofclans,
            R.drawable.talkingtom,
            R.drawable.asphalt8,
            R.drawable.pou,
            R.drawable.fruitninja,
            R.drawable.wheresmywhater
    };

    private String[] names = {
            "Angry Birds","Cut the Rope","Worms 3","Clash of Clans","Talking Tom","Asphalt 8","Pou","fruit Ninja","Where's My Water"
    };

    private Context context ;
    private LayoutInflater layoutInflater ;

    public SlideShowAdapter(Context context) {
        this.context = context ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.slideshow_layout,container,false);

        ImageView game_img = itemView.findViewById(R.id.imageview_id);
        TextView game_name =  itemView.findViewById(R.id.txtview_name_id);
        LinearLayout linearLayout =  itemView.findViewById(R.id.slidshow_ll);

//        game_img.setImageResource(images[position]);
//        Picasso.get().load(images[position]).into(game_img);
        Glide.with(context).load(images[position]).into(game_img);  //  Best Performance

        game_name.setText(names[position]);
        container.addView(itemView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Image "+ (position+1) ,Snackbar.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view ==(LinearLayout)object;    // bcz slideshow_layout.xml root element is linear layout
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }


    @Override
    public int getCount() {
        return images.length;
    }
}
