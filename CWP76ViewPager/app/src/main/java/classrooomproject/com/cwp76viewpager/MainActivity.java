//Demonstrate ViewPager using imageview

package classrooomproject.com.cwp76viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       vp =   findViewById(R.id.vp1);

       MyViewPager mv = new MyViewPager(this);

       vp.setAdapter(mv);
    }
}
