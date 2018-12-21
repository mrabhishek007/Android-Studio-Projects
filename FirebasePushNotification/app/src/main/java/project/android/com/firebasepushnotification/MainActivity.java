package project.android.com.firebasepushnotification;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    TextView mProfileLabel,mNotificationLabel,mUserLabel;

    private ViewPager mainPager;

    private PageViewAdapter mPageViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mProfileLabel = findViewById(R.id.profile_label);

        mNotificationLabel =findViewById(R.id.notification_label);

        mUserLabel = findViewById(R.id.users_label);

        mainPager = findViewById(R.id.main_viewpager);


        mPageViewAdapter  = new PageViewAdapter(getSupportFragmentManager());

        mainPager.setAdapter(mPageViewAdapter);


        mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                changeTabsPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        //When buttons of linear layout is pressed it will also change fragments

        mProfileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
             mainPager.setCurrentItem(0);
            }
        });


        mUserLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainPager.setCurrentItem(1);

            }
        });

        mNotificationLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainPager.setCurrentItem(2);

            }
        });

    }





    private void changeTabsPosition(int position)
    {
        if(position==0)
        {
            mProfileLabel.setTextColor(getResources().getColor(R.color.texttabwhite));
            mProfileLabel.setTextSize(22);


            mUserLabel.setTextColor(getResources().getColor(R.color.texttabbright));
            mUserLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.texttabbright));
            mNotificationLabel.setTextSize(16);
        }

        if(position==1)
        {
            mUserLabel.setTextColor(getResources().getColor(R.color.texttabwhite));
            mUserLabel.setTextSize(22);


            mProfileLabel.setTextColor(getResources().getColor(R.color.texttabbright));
            mProfileLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getResources().getColor(R.color.texttabbright));
            mNotificationLabel.setTextSize(16);
        }

        if(position==2)
        {
            mNotificationLabel.setTextColor(getResources().getColor(R.color.texttabwhite));
            mNotificationLabel.setTextSize(22);


            mUserLabel.setTextColor(getResources().getColor(R.color.texttabbright));
            mUserLabel.setTextSize(16);

            mProfileLabel.setTextColor(getResources().getColor(R.color.texttabbright));
            mProfileLabel.setTextSize(16);
        }
    }
}
