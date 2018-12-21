package com.classroomproject.smartclassroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Vikash on 12/17/2017.
 */

public class MyTeacherPageAdapter extends FragmentPagerAdapter
{
    int pos;

    Context context;
    int noof_fragments = 4;


    public MyTeacherPageAdapter(Context context, FragmentManager fragmentManager)
    {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        pos=position;
        switch (position)
        {
            case 0:
               return new TeacherHomeFragment1();
            case 1:
               return new TeacherHomeFragment2();
            case 2:
                return new TeacherHomeFragment3();
            case 3:
                return new TeacherHomeFragment4();
              default:
                  return  null;
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return context.getString(R.string.teacher_tabtitle2);
            case 1:
                return context.getString(R.string.teacher_tabtitle1);
            case 2:
                return context.getString(R.string.teacher_tabtitle3);
            case 3:
                return context.getString(R.string.teacher_tabtitle4);
                default:
                    return null;
        }
    }

    /**

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

     */
    @Override
    public int getCount()
    {
        return noof_fragments;
    }
}
