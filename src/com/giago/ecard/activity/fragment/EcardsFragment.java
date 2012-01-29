package com.giago.ecard.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.giago.ecard.R;
import com.giago.ecard.activity.Show;

public class EcardsFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    Uri uri = Uri.parse("content://com.giago.ecard/ecard");

    String[] from = { "name"};
    int[] to = { R.id.title };

    private SimpleCursorAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);

        adapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), R.layout.ecard_item, null, from, to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        setListAdapter(adapter);
    }

    private void startShowActivity(Context context, String company, String role, String name, String phone, String email, String note, String qrdata ) {
        
        Log.v("dev", "name " + name);
        Intent intent = new Intent(context, Show.class);
        intent.putExtra("company", company);
        intent.putExtra("role", role);
        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("note", note);
        intent.putExtra("qrdata", qrdata);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Context context = v.getContext();
        Cursor cursor = context.getContentResolver().query(uri, null, "_id = " + id,null, null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String company = cursor.getString(cursor.getColumnIndex("company"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
        String phone = cursor.getString(cursor.getColumnIndex("phone"));
        String role = cursor.getString(cursor.getColumnIndex("role"));
        String note = cursor.getString(cursor.getColumnIndex("note"));
        String qrdata = cursor.getString(cursor.getColumnIndex("qrdata"));
        startShowActivity(context,company, role, name, phone, email, note, qrdata);
    }

    /*
     * LoaderCallBack methods
     */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle arg) {
        String[] projection = { "_id", "name" };
        CursorLoader cursorLoader = new CursorLoader(getActivity(), uri, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
