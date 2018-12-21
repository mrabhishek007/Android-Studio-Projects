package com.example.vikash.hoteldesignapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.vikash.hoteldesignapp.R;

public class ImageSlideAdapter  extends PagerAdapter {

    private int[] images = {
            R.drawable.angrybirds,
            R.drawable.asphalt8,
            R.drawable.clashofclans
    };
    private LayoutInflater layoutInflater ;
    private Context context ;

    public ImageSlideAdapter(Context context) {
        this.context = context ;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.image_slide_layout,container,false);

        ImageView game_img = itemView.findViewById(R.id.hotel_img_imgview);
        LinearLayout linearLayout =  itemView.findViewById(R.id.slidshow_ll);

        Glide.with(context).load(images[position]).into(game_img);  //  Best Performance
        container.addView(itemView);

/*        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });*/

        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }



    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
       return view == (LinearLayout)o;
    }
}
