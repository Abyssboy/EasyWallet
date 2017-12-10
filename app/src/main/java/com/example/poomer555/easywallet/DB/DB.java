package com.example.poomer555.easywallet.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by poome on 12/10/2017.
 */

public class DB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "EasyWallet.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Money";
    public static final String COL_ID ="_id" ;
    public static final String COL_Name = "Name";
    public static final String COL_Type = "Type";
    public static final String COL_Much= "much";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_Name+ " TEXT, "
            + COL_Type + " TEXT, "
            + COL_Much + " INTEGER) " ;


    public DB (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
