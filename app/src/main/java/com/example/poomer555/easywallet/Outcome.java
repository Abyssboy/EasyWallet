package com.example.poomer555.easywallet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.poomer555.easywallet.DB.DB;

public class Outcome extends AppCompatActivity {
    private EditText mDetail;
    private EditText mMuch;
    private Button mEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);

        mDetail = findViewById(R.id.detailEdit2);
        mMuch =findViewById(R.id.MuchEdit2);
        mEnter = findViewById(R.id.button2);

        mEnter.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          SaveDataToDB();
                                          setResult(RESULT_OK);
                                          finish();
                                      }
                                  }

        );

    }

    private void SaveDataToDB() {
        String Name =mDetail.getText().toString();
        int Num =Integer.parseInt(mMuch.getText().toString());
        Num=-Num;
        String Type = String.valueOf('1');



        ContentValues CV = new ContentValues();
        CV.put(DB.COL_Name,Name);
        CV.put(DB.COL_Much,Num);
        CV.put(DB.COL_Type,Type);

        DB mHelper = new DB(this);
        SQLiteDatabase Sql =mHelper.getWritableDatabase();

        Sql.insert(DB.TABLE_NAME,null,CV);



    }
}
