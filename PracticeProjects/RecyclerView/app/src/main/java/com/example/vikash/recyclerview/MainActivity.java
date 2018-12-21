package com.example.vikash.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    String[] userName = {"User1","User2","User3","User4","User5","User6","User7","User8","User9","User10","User11","User12"};
    String[] userDescription = {"Description1","Description2","Description3","Description4","Description5","Description6","Description7","Description8","Description9","Description10","Description11","Description12"};
    int[] userImg = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,R.drawable.img12,R.drawable.img6,R.drawable.img11,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(" RecyclerView Demo");

        mRecyclerView = findViewById(R.id.recyclerview);

        List<UserModel> list = new ArrayList<UserModel>();

        for(int i=0; i<userName.length; i++){

            UserModel model = new UserModel();
            model.setName(userName[i]);
            model.setDescription(userDescription[i]);
            model.setProfile_img(userImg[i]);
            list.add(model);
        }

        MyAdapter adapter = new MyAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

    }
}
