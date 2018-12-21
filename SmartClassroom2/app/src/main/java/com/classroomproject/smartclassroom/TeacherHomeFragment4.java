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


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherHomeFragment4 extends Fragment {

    String name[] = {"Name - Harinder Daksh","Name - Vivek Raj","Name - Amardeep Kumar","Name - Raushan Ranjan","Name - Vikram Kumar","Name - Vikash Kumar Gaurav"};
    String email[] = {"Email - harrydaksh.998@outlook.com","Email - vivek11@gmail.com","Email - amardeep.mahuawala11@live.com","Email - raushanrr123@gmail.com","Email - vikram111@live.com","Email - vkgsbs@gmail.com"};
    String complain_msg[] ={"Complaint - Sir,I am getting error while retreiving data from SqliteDatabase.","Complaint - Sir,I am getting runtime exception when working with multiple Threads.","Complaint - Sir,I don't understood AlertDialog.","Complaint - Sir,Snackbar is not working with Fragments,Sir i need some idea about that.","Complaint - Sir,I am not able to retrieve data from SharedPrefences.","Sir, how to use TabLayout with Fragments."};
    String date[] = {"Date - 15-11-2017","Date - 19-11-2017","Date - 18-11-2017","Data - 23-11-2017","Date - 17-12-2017","Date - 17-12-2017"};
    String key[]={"KL1","KL2","KL3","KL4"};
    int ids[] = {R.id.complain_stud_name_list,R.id.complain_stud_datelist,R.id.complain_student_email_list,R.id.complain_student_complain_list};

    View view;
    Context context;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {
        super.onCreateView(inflater,container,savedInstanceState);

        view = inflater.inflate(R.layout.fragment_teacher_home_fragment4,container,false);

            //executes only when any data avai;able in firebase
        if(name.length>0)
        {
            ListView lv = view.findViewById(R.id.teacher_fragment4);

            ArrayList al = new ArrayList();
            for (int i = 0; i < name.length; i++) {
                HashMap hm = new HashMap();
                hm.put(key[0], name[i]);
                hm.put(key[1], date[i]);
                hm.put(key[2], email[i]);
                hm.put(key[3], complain_msg[i]);
                al.add(hm);
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(context, al, R.layout.teacher_home_listview_style4, key, ids);
            lv.setAdapter(simpleAdapter);

        }
        return view;
    }

}
