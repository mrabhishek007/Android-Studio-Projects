package com.classroomproject.smartclassroom;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherHomeFragment2 extends Fragment {

     ArrayList array1;
    String teacher_id,teacher_sub;
    MyTeacherHomeActivity ref;
    ListView listView;
    Context context;
    int ids[]={R.id.teacher_date_tv,R.id.teacher_chapter_tv,R.id.teacher_topics_tv};
    String keys[]={"key1","key2","key3"};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_teacher_home_fragment2, container, false);


        //Retrieving intent from  MyTeacherHomeActivity
        ref = (MyTeacherHomeActivity) context;
        final ArrayList al = ref.teacherLoginDetails();
        teacher_id = (String) al.get(1);
        teacher_sub = (String) al.get(2);



        //Retreiving Lecture Data from Firebase

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Teacher_Registration");
        DatabaseReference dr2 = dr.child(teacher_id).child("Subject_Name -" + teacher_sub);
        dr2.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Check whether a child is available in the snapshot
                if(dataSnapshot.getChildrenCount()>0)
                {
                array1 = new ArrayList();
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for (DataSnapshot dsn : it) {

                    SaveDailyRoutineActivity sdra = dsn.getValue(SaveDailyRoutineActivity.class);
                    Log.e("-->", sdra.getDate() + "-" + sdra.getChapter_name() + "-" + sdra.getTopic_name());
                    HashMap hashMap = new HashMap();
                    hashMap.put(keys[0], sdra.getDate());
                    hashMap.put(keys[1], sdra.getChapter_name());
                    hashMap.put(keys[2], sdra.getTopic_name());
                    array1.add(hashMap);
                    listView = view.findViewById(R.id.teacher_fragment2);
                    if (array1.size() > 0) {
                        SimpleAdapter simpleAdapter = new SimpleAdapter(context, array1, R.layout.teacher_home_listview_style2, keys, ids);
                        listView.setAdapter(simpleAdapter);

                    } else {

                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "No Records Found !", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }

                }
            }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ref, "Database Error !", Toast.LENGTH_SHORT).show();

            }
        });




        // Showing Lecture Data in Listview



        /**
         String date[] ={"Date - 12-12-2016","Date - 13-2-2008","Date - 23-06-2009"} ;
         String chapters[] = {"Chapter - Data types","Chapter - Objects","Chapter - Class"};
         String topics[]={"Topics - premetive,non-premitive","Topics - About objects","Topics - class concepts"};
        ArrayList arrayList = new ArrayList();
        int i;
        for(i=0;i<date.length;i++)
        {
            HashMap hashMap = new HashMap();
            hashMap.put(keys[0],date[i]);
            hashMap.put(keys[1],chapters[i]);
            hashMap.put(keys[2],topics[i]);
            arrayList.add(hashMap);
        }
         */

       return  view;
    }

}
