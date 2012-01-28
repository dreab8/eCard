package com.giago.ecard.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EcardDatabaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String NAME = "ecard.db";

    public EcardDatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ecard (_id integer primary key autoincrement, name text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
