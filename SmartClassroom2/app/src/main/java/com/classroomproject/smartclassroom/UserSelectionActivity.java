package com.classroomproject.smartclassroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class UserSelectionActivity extends Activity {

    AlertDialog.Builder builder;
    Button teacher_rb,student_rb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
       teacher_rb =  findViewById(R.id.teacherrb);
        student_rb =  findViewById(R.id.studentrb);

              //When student is clicked

        teacher_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
                startActivity(new Intent(UserSelectionActivity.this,TeacherRegistrationActivity.class));
            }
        });

        student_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
                startActivity(new Intent(UserSelectionActivity.this,StudentRegistrationActivity.class));

            }
        });


    }


    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {

            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this,R.style.MyDialogTheme);
            builder.setMessage("Exit");
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Are You Sure ?");

            //Changing the default font color of Negative button in Alertdialog
            builder.setNegativeButton(Html.fromHtml("<font color='#FF7F27'>No</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    dialog.dismiss();
                }
            });

            builder.setPositiveButton((Html.fromHtml("<font color='#FF7F27'>Yes</font>")), new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                    finish();
                    System.exit(0);
                }
            });

            builder.create().show();

        }

        return super.onKeyDown(keyCode, event);

    }
}
