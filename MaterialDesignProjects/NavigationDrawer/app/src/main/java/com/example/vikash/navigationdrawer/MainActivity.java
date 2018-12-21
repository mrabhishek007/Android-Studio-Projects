package com.example.vikash.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        toolbar =  findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Navigation Drawer");

        drawerLayout =  findViewById(R.id.drawer_layout);
        navigationView  = findViewById(R.id.navigation_view);

        //Listening action to navigation drawer items [Line 42 ]

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

      int id =  item.getItemId();

      switch (id){
          case (R.id.inbox):
              Toast.makeText(this, "Inbox selected", Toast.LENGTH_SHORT).show();
              break;

          case (R.id.starred):
              Toast.makeText(this, "Starred Selected", Toast.LENGTH_SHORT).show();
              break;

          case (R.id.send_mail):
              Toast.makeText(this, "Send Mail Selected", Toast.LENGTH_SHORT).show();
              break;

          case (R.id.drafts):
              Toast.makeText(this, "Drafts Selected", Toast.LENGTH_SHORT).show();
              break;

          case (R.id.allmail):
              Toast.makeText(this, "All mail selected", Toast.LENGTH_SHORT).show();
              break;

          case (R.id.spam):
              Toast.makeText(this, "Spam selected", Toast.LENGTH_SHORT).show();
              break;
      }
      return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
}
