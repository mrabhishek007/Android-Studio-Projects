package com.example.android.predatabase;

        import android.content.DialogInterface;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity
{
    ListView lv;
    ArrayList al;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        lv = (ListView)findViewById(R.id.lv);

//        MyDatabase md = new MyDatabase(this);
//        SQLiteDatabase db = md.getWritableDatabase();
//
//        String colu[] = {MyDatabase.COL1};
//        Cursor c = db.query(MyDatabase.TABLE_NAME,colu,null,null,null,null,null);
//
//        boolean res = c.moveToFirst();
//        String values[] = new String[c.getCount()];
//        if (res)
//        {
//            int i = 0;
//            do
//            {
//              values[i] = c.getString(0);
//              i++;
//            }while (c.moveToNext());
//
//            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,values);
//            lv.setAdapter(aa);
//        }
//        else
//        {
//            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
//            finish();
//        }
        loadListView();
    }

    public void loadListView()
    {
        al = new ArrayList();
        MyDatabase md = new MyDatabase(this);
        final SQLiteDatabase db = md.getWritableDatabase();

        String col[] = {MyDatabase.COL1};
        Cursor c = db.query(MyDatabase.TABLE_NAME,col,null,null,null,null,null);

        if (c.moveToFirst())
        {
            do
            {
                int idno = c.getInt(0);
                al.add(idno);
            }
            while (c.moveToNext());

            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,al);
            lv.setAdapter(aa);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    final int selected_id = (int)al.get(position);

                    String col[] = {MyDatabase.COL2,MyDatabase.COL3};
                    String sel = MyDatabase.COL1+" = ?";
                    String sel_args[] = {""+selected_id};

                    Cursor c = db.query(MyDatabase.TABLE_NAME,col,sel,sel_args,null,null,null,null);

                    if (c.moveToFirst())
                    {

                        String name = c.getString(0);
                        double sal = c.getDouble(1);

                        final String mess = "Name : "+name+"\n\n"+"Salary : "+sal;

                        final AlertDialog.Builder ad = new AlertDialog.Builder(DetailsActivity.this);
                        ad.setIcon(R.drawable.client);
                        ad.setTitle("IDNO :" + selected_id);
                        ad.setMessage(mess);
                        ad.setCancelable(false);
                        ad.setPositiveButton("Delete", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                AlertDialog.Builder adb = new AlertDialog.Builder(DetailsActivity.this);
                                adb.setIcon(R.drawable.client);
                                adb.setTitle("IDNO :" + selected_id);
                                adb.setMessage("Conformation.......");
                                adb.setCancelable(false);
                                adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        String where = MyDatabase.COL1+" = ?";
                                        String where_args[] = {""+selected_id};

                                        int res = db.delete(MyDatabase.TABLE_NAME,where,where_args);

                                        if (res != 0)
                                        {
                                            Toast.makeText(DetailsActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                            loadListView();
                                        }
                                        else
                                        {
                                            Toast.makeText(DetailsActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                adb.create().show();
                            }
                        });
                        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog add = ad.create();
                        add.show();
                    }
                    else
                    {
                        Toast.makeText(DetailsActivity.this, "IDNO not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
