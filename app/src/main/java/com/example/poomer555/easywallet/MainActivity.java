package com.example.poomer555.easywallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.poomer555.easywallet.DB.DB;
import com.example.poomer555.easywallet.Model.Item;
import com.example.poomer555.easywallet.adap.Adapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DB mHelper;
    private SQLiteDatabase mDB;
    private int fTotal=0;

    private ArrayList<Item> mItem =new ArrayList<>();
    private Adapter mAapter;
    private ListView mListView;

    private Button button1;
    private Button button2;
    private TextView To;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.ListView);
        button1 = findViewById(R.id.Income_butt);
        button2= findViewById(R.id.OutComebutt);
        To = findViewById(R.id.TotalAll);


        mHelper = new DB(this);
        mDB=mHelper.getWritableDatabase();


        LoadData();

        mAapter =new Adapter(
                this,
                R.layout.listview,
                mItem


        );

        mListView.setAdapter(mAapter);



       To.setText(String.valueOf(fTotal));


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("ยืนยันการลบรายการ");
                builder.setPositiveButton("ลบ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Item item = mItem.get(position);
                        int ID = item.Id;
                        mDB.delete(
                                mHelper.TABLE_NAME,
                                mHelper.COL_ID+"=?",
                                new String[]{String.valueOf(ID)}

                        );
                        LoadData();
                        mAapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("ไม่ลบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,IncomeAct.class);
                startActivityForResult(intent,1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Outcome.class);
                startActivityForResult(intent,2);
            }
        });

    }

    private void LoadData() {
        Cursor cursor =mDB.rawQuery("Select * from "+mHelper.TABLE_NAME,null);
        mItem.clear();

        while (cursor.moveToNext()){
            int ID = cursor.getInt(cursor.getColumnIndex(DB.COL_ID));
            String Name = cursor.getString(cursor.getColumnIndex(DB.COL_Name));
                    int much= cursor.getInt(cursor.getColumnIndex(DB.COL_Much));
                            String Type = cursor.getString(cursor.getColumnIndex(DB.COL_Type));

                            Item item = new Item(ID,Name,Type,much);
                            mItem.add(item);
        }
    }

    private int totalAll() {
        Cursor Totall = mDB.rawQuery("Select SUM(" + DB.COL_Much + ") FROM " + mHelper.TABLE_NAME, null);
        int amount = 0;
        if (Totall.moveToFirst()) {
            amount = Totall.getInt(0);
        }
        return amount;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if (resultCode==RESULT_OK){
                LoadData();

                mAapter.notifyDataSetChanged();

            }
        }
         if (requestCode==2){
             if (resultCode==RESULT_OK){
                 LoadData();
                 totalAll();
                 mAapter.notifyDataSetChanged();
             }
         }


    }
}
