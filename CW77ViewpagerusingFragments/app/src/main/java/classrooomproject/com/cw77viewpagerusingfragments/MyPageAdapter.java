package classrooomproject.com.cw77viewpagerusingfragments;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

/**
 * Created by Vikash on 12/16/2017.
 */

public class MyPageAdapter extends FragmentPagerAdapter {


    int total_no_of_tabs = 4;
    public Context context;


    public MyPageAdapter(Context context,FragmentManager fm)
    {

        super(fm);
        this.context = context;

    }


    @Override
    public Fragment getItem(int position) {

       switch (position)
       {
           case 0:
               return new MyFragment1();
           case 1:
               return  new MyFragment2();
           case 2:
               return  new MyFragment3();
           case 3:
               return new MyFragment4();
               default:
                   return null;

       }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {


        switch (position)
        {
            case 0 :
                // return  context.getString(R.string."title_name1");
                 return "fg1";
            case 1:
                // return  context.getString(R.string."title_name2");
                return  "fg2";
            case 2:
                // return  context.getString(R.string."title_name3");
                return "fg3";
            case 3:
                // return  context.getString(R.string."title_name4");
                return "fg4";

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return total_no_of_tabs;
    }
}
