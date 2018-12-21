package com.example.vikash.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("MyAppName");
    }


    //Add the menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //Set an action to menu items buttons

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.item1){
            Toast.makeText(this, "Item 1 selelcted", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.item2){
            Toast.makeText(this,"Item2 selected",Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.item3){
            Toast.makeText(this, "Item3 selected", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.cart_id){
            Toast.makeText(this, "Cart Selected", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.search_id){
            Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show();
        }
        else if(id==android.R.id.home){     //Action for back button in toolbar
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}


