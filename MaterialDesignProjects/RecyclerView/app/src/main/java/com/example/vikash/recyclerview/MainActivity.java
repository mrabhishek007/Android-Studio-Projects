package com.example.vikash.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
;import java.util.ArrayList;
import java.util.List;

/*
     STEP 1 - Create a recycler view in activity_main.xml

     STEP 2 - Create a seperate xml(recycler_item.xml) for recycler view item

     STEP 3 - Create a seperate ViewHolder class which extends RecyclerView.ViewHolder

     STEP 4 - Create a model class(UserModel.java) with default,parameterized constructor with getter & setter according to the UserModel.java

     STEP 5 - Create a adapter class which extends RecyclerView.Adapter<MyViewHolder>

     STEP 6 - Inflate recycler_item in MyAdapter.java in onCreateViewHolder(), and use onBindViewHolder() to set  layout of recycler_item.xml

     STEP 6 - In main_activity define the list() and send to MyAdapter.java

 */

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    String[] userName = {"User1","User2","User3","User4","User5","User6","User7","User8","User9","User10","User11","User12"};
    String[] userDescription = {"Description1","Description2","Description3","Description4","Description5","Description6","Description7","Description8","Description9","Description10","Description11","Description12"};
    int[] userImg = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,R.drawable.img12,R.drawable.img6,R.drawable.img11,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recycler View Demo");


       recyclerView =  findViewById(R.id.recycler_view);

       List<UserModel> list = new ArrayList<>();

       for(int i=0;i<userName.length;i++) {

           UserModel user = new UserModel();
           user.setUserName(userName[i]);
           user.setUserDescription(userDescription[i]);
           user.setUserImage(userImg[i]);
           list.add(user);
       }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2); // 2 row will be created , spanCount = no of rows


       recyclerView.setLayoutManager(gridLayoutManager);
       recyclerView.setHasFixedSize(true);
       recyclerView.setAdapter(new MyAdapter(list,this));

    }
}
