//ViewPager using TabLayout

package classrooomproject.com.cw77viewpagerusingfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ViewPager vp =  findViewById(R.id.vp1);
       TabLayout tl =findViewById(R.id.tl1);

       android.support.v4.app.FragmentManager fm  =  getSupportFragmentManager();
       PagerAdapter pa =  new MyPageAdapter(this,fm);
       vp.setAdapter(pa);
       tl.setupWithViewPager(vp);

    }
}
