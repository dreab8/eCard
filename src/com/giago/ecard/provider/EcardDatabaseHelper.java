package com.giago.ecard.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EcardDatabaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 11;
    private static final String NAME = "ecard.db";

    public EcardDatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("dev", "db creation");
        db.execSQL("create table ecard (_id integer primary key autoincrement, name text, company text, phone text, email text, note text, role text, qrdata text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("dev", "Upgrading database. Existing contents will be lost. ["+ oldVersion + "]->[" + newVersion + "]");
        db.execSQL("DROP TABLE IF EXISTS " + "ecard");
        onCreate(db);
        db.execSQL("insert into ecard (name, company, phone, email) values ('andrea', 'novoda' , '+44 (0)7411 372597' , 'dreborier@gmail.com')");
        db.execSQL("insert into ecard (name, company, phone, email, note, qrdata) values ('luigi', 'novoda', 'M: +44 (0)7983 382560', 'luigi.agosti@gmail.com', 'T: @luigiagosti', 'https://plus.google.com/102048052961229488268/posts')");
    }

}
