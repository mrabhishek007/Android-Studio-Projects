package com.classroomproject.smartclassroom;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class TeacherHomeFragment3 extends Fragment {

    String name[] = {"Name - Vikash Kumar Gaurav","Name -Abhishek Kumar","Name - Vikram Kumar","Name -Raushan Ranjan","Name -Yogesh Kumar","Name -Amardeep Kumar","Harinder Daksh","Name -Nilmani Kumar Mishra","Name -Vivek Raj","Name -Suraj Verma","Name -Adesh Kumar"};
    String email[] = {"Email - vkgsbs@gmail.com","Email - abhishekkrsbs@gmail.com","Email - Vikram111@live.com","Email - raushanrr123@gmail.com","Email -yogi145@gmail.com","Email - amardeep.mahuawala11@live.com","Email - harrydaksh.998@outlook.com","Email - nilmanikrmishra.morkel@gmail.com","Email - vivek11@live.com","Email - verma.suraj.111@gmail.com","Email - adesh.bareli.kr45@gmail.com"};

    String key[]={"KL1","KL2","KL3"};
    int ids[] = {R.id.stud_name_list,R.id.stud_email_list};


    View v1 ;
    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        super.onCreateView(inflater,container,savedInstanceState);

        v1 = inflater.inflate(R.layout.fragment_teacher_home_fragment3,container,false);


            //Executes only when any data available in firebase
        if(name.length>0)
        {

            ListView lv = v1.findViewById(R.id.teacher_fragment3);
            ArrayList al1 = new ArrayList();
            int i;
            for (i = 0; i < name.length; i++) {
                HashMap hashMap = new HashMap();
                hashMap.put(key[0], name[i]);
                hashMap.put(key[1], email[i]);
                al1.add(hashMap);
            }


            SimpleAdapter simpleAdapter = new SimpleAdapter(context, al1, R.layout.teacher_home_listview_style3, key, ids);
            lv.setAdapter(simpleAdapter);

        }



       return v1;
    }

}
