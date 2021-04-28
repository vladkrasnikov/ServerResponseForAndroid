package com.example.checksite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataBase";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_URL = "name";
    public static final String KEY_RES = "res";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_CONTACTS + " (" + KEY_ID + " integer primary key autoincrement, "+ KEY_URL + " text not null," + KEY_RES + " text not null );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table if not exists " + TABLE_CONTACTS + " (" + KEY_ID + " integer primary key autoincrement, "+ KEY_URL + " text not null," + KEY_RES + " text not null );");
        onCreate(db);
    }
}
