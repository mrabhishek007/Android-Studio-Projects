package com.example.android.cw46;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragmentDataTransfer extends Fragment
{

    EditText et1;
    Context ct,ct2;


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ct2=context;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        final Context myct =getContext();
        View v= inflater.inflate(R.layout.fragment_my_fragment_data_transfer, container, false);

       Button bt =  v.findViewById(R.id.bt1);
       et1=v.findViewById(R.id.et1);

       bt.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               String st = et1.getText().toString();
               if(st.isEmpty())
               {
                   et1.setError("Empty Field");
               }
               else
               {

                   //   1st Method

                   /**
                    ct = v.getContext();
                    MainActivity ma = (MainActivity)ct;
                    ma.reciever(st);
                    */

                   //2nd Method(Recommended)

                   MainActivity ma = (MainActivity) ct2;//Context is the super class of MainActivity.So we are downcasting context ref into MainActivity
                   ma.communicator(st);//It will trigger communicator() of MainActivity()
                   et1.setText("");
               }

           }
       });

        return  v;
    }

}
