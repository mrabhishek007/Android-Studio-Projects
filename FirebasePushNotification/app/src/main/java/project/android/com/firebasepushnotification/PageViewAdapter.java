package project.android.com.firebasepushnotification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class PageViewAdapter extends FragmentPagerAdapter
{


    public PageViewAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position)
        {
            case 0 :
                return new ProfileFragement();

            case 1 :
                return new UsersFragment();

            case 2 :

                return new NotificationFragment();

            default:
                return  null;
        }

    }

    @Override
    public int getCount()
    {
        return 3;
    }
}
