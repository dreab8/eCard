package com.giago.ecard.activity.fragment;

import com.giago.ecard.R;

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

public class EcardsFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    Uri uri = Uri.parse("content://com.giago.ecard/ecard");
    
    private SimpleCursorAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("dev", "EcardsFragment.onActivityCreated ");
        getLoaderManager().initLoader(0, null, this);
        
        String[] from = { "name" };
        int[] to = { R.id.title };
        
        adapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), R.layout.ecard_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /*
     * LoaderCallBack methods
     */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle arg) {
        String[] projection = { "_id", "name" };
        CursorLoader cursorLoader = new CursorLoader(getActivity(),
        uri, projection, null, null, null);
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
