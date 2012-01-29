package com.giago.ecard.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class EcardProvider extends ContentProvider {

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        db = new EcardDatabaseHelper(getContext()).getWritableDatabase();
        if (db == null) {
            return false;
        }
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
    	long id = db.insert(uri.getLastPathSegment(), null, values);
        return Uri.withAppendedPath(uri, "" + id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return db.query(uri.getLastPathSegment(), projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
