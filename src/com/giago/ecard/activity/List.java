package com.giago.ecard.activity;

import com.giago.ecard.R;
import com.giago.ecard.R.layout;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class List extends Activity {

    Uri uri = Uri.parse("content://com.giago.ecard/ecard");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = getContentResolver().query(uri , null, null, null, null);
        Log.v("dev", "" + cursor.getCount());
    }
}