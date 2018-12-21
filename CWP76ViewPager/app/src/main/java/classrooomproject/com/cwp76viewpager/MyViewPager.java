package classrooomproject.com.cwp76viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Vikash on 12/16/2017.
 */

public class MyViewPager extends PagerAdapter
{

    Context context;
    protected  int[]  image_id = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};



    public MyViewPager(Context ct)
    {
        context = ct;
    }

    @Override
    public int getCount()
    {
        return image_id.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        LayoutInflater lif = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View v =  lif.inflate(R.layout.my_view_pager,null,false);
      ImageView iv =  v.findViewById(R.id.iv1);
      iv.setImageResource(image_id[position]);

       ViewPager vp =  (ViewPager)container;
        vp.addView(v,0);
        return  v;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        ViewPager vp2 = (ViewPager)container;
        View v = (View)object;
        vp2.removeView(v);
    }
}
