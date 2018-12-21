package com.example.android.cw44;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WelcomeContact extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View v1 = inflater.inflate(R.layout.fragment_welcome_contact,container,false);
        Context ct = v1.getContext();
       TextView callus_button =  v1.findViewById(R.id.callus);
        TextView emailus_button =  v1.findViewById(R.id.email);
        callus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+918699474954"));
                startActivity(i);
            }
        });
        emailus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String addresses[]={"vikashgaurav.vkg@gmail.com"};
                String subject="Help";

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                startActivity(intent);

            }


        });


         return v1;
    }
}
