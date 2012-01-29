package com.giago.ecard.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;

import com.giago.ecard.R;
import com.giago.ecard.activity.Show;
import com.giago.ecard.activity.ShowAndBeam;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.service.EcardDao;

public class EcardsFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    private static final String[] FROM = { "name"};
    private static final int[] TO = { R.id.title };

    private SimpleCursorAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), R.layout.ecard_item, null, FROM, TO,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Context c = getView().getContext().getApplicationContext();
        
        Cursor cursor = c.getContentResolver().query(EcardDao.ECARD_URI, null, "_id = " + id,null, null);
        
        EcardIntent ei = new EcardIntent(cursor, c, getTheSupportedShowActivityClass());
        startActivity(ei.getIntent());
    }

    /*
     * LoaderCallBack methods
     */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle arg) {
        String[] projection = { "_id", "name" };
        CursorLoader cursorLoader = new CursorLoader(
        		getActivity(), EcardDao.ECARD_URI, projection, null, null, null);
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
    
    private Class<? extends Activity> getTheSupportedShowActivityClass() {
		Class<? extends Activity> clazz = null;
        try {
        	new android.nfc.NdefMessage("".getBytes());
        	clazz = ShowAndBeam.class;
        } catch (java.lang.NoClassDefFoundError e) {
        	clazz = Show.class;
        } catch (Exception e) {
        	clazz = ShowAndBeam.class;
        }
		return clazz;
	}

}
