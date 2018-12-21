//Working with Navigation Drawer

package classrooomproject.com.cwp78navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = findViewById(R.id.dl1);

        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);

        dl.addDrawerListener(abdt);

        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


                  //Working with navigationView

     NavigationView nv =  findViewById(R.id.nv1);

     nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item)
         {

             int id = item.getItemId();

             if(id==R.id.m1)
             {
                 Toast.makeText(MainActivity.this, "I am 1st", Toast.LENGTH_SHORT).show();
             }
             else
             if(id==R.id.m2)
             {
                 Toast.makeText(MainActivity.this, "I am 2nd", Toast.LENGTH_SHORT).show();

             }
             else
             if(id==R.id.m3)
             {
                 Toast.makeText(MainActivity.this, "I am 3rd", Toast.LENGTH_SHORT).show();

             }
             return false;
         }
     });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(abdt.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }



}
