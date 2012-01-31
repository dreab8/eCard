package com.giago.ecard.activity.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.service.EcardDao;
import com.giago.ecard.utils.admob.Ads;
import com.google.ads.AdView;

public class EcardsFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    private static final String DEFAULT_PERSONAL = "0";
	private static final String[] FROM = { "name"};
    private static final int[] TO = { R.id.title };

    private SimpleCursorAdapter adapter;
    private String isPersonal;
    private AdView adView;
    
    public EcardsFragment() {
    	this(DEFAULT_PERSONAL);
    }
    
    public EcardsFragment(String isPersonal) {
        setRetainInstance(true);
        this.isPersonal = isPersonal;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.ecards_fragment, container, false);
	    adView = (AdView) view.findViewById(R.id.ad);
	    adView.loadAd(Ads.getAdsRequest());
	    adView.setVisibility(View.VISIBLE);
    	return view;
    }

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
        Context context = getView().getContext().getApplicationContext();
        
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);
        EcardIntent ei = new EcardIntent(cursor, context);

        startActivity(ei.getIntent());
    }

    /*
     * LoaderCallBack methods
     */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle arg) {
        CursorLoader cursorLoader = new CursorLoader(
        		getActivity(), EcardDao.ECARD_URI, null, "personal = ?", new String[]{isPersonal}, null);
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
