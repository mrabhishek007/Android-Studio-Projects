package com.example.vikash.testjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.vikash.testjson.event.Mediator;
import com.example.vikash.testjson.model.Brand;
import com.example.vikash.testjson.service.BrandServiceProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

   public Brand brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BrandServiceProvider provider = new BrandServiceProvider(this);
        provider.getBrandInfo();

       recyclerView =  findViewById(R.id.rv) ;

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }



    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediator(Mediator event) {
        brand =  event.getBrandobj();
  /*      MyRecyclerAdapter adapter = new MyRecyclerAdapter(this,brand);
        LinearLayoutManager ll = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(ll);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);*/
    }




}
