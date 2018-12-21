package com.example.android.predatabase;


        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
    }

    public void saveDetails(View v)
    {
        String s1 = et1.getText().toString().trim();
        String name = et2.getText().toString().trim();
        String s3 = et3.getText().toString().trim();

        int idno = Integer.parseInt(s1);
        double salary = Double.parseDouble(s3);

        MyDatabase md = new MyDatabase(this);
        SQLiteDatabase db = md.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MyDatabase.COL3,salary);
        cv.put(MyDatabase.COL1,idno);
        cv.put(MyDatabase.COL2,name);

        long res = db.insert(MyDatabase.TABLE_NAME,null,cv);

        if (res != -1)
        {
            Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et1.requestFocus();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


    public void viewAll(View v)
    {
        Intent i = new Intent(this,DetailsActivity.class);
        startActivity(i);
    }
}
