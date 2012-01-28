package com.giago.ecard.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EcardDatabaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 4;
    private static final String NAME = "ecard.db";

    public EcardDatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("dev", "db creation");
        db.execSQL("create table ecard (_id integer primary key autoincrement, name text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("dev", "Upgrading database. Existing contents will be lost. ["+ oldVersion + "]->[" + newVersion + "]");
        db.execSQL("DROP TABLE IF EXISTS " + "ecard");
        onCreate(db);
        db.execSQL("insert into ecard (name) values ('andrea')");
        db.execSQL("insert into ecard (name) values ('luigi')");
    }

}
