package com.classroomproject.smartclassroom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Vikash on 12/18/2017.
 */

public class MyStudentPageAdapter extends FragmentPagerAdapter {
    Context context;
    int no_of_frag = 3;

    public MyStudentPageAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0 :
                return new StudentHomeFragment1();
            case 1:
                return new StudentHomeFragment2();
            case 2:
                return new StudentHomeFragment3();
            default:
                return null;
        }

    }

    @Override
    public int getCount()
    {
        return no_of_frag;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return context.getString(R.string.student_tabtitle1);
            case 1:
                return context.getString(R.string.student_tabtitle2);
            case 2:
                return "Complaints";
            default:
                return null;
        }
    }
}