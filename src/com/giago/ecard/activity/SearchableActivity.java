package com.giago.ecard.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.giago.ecard.R;
import com.giago.ecard.activity.utils.ActionBarEcardActivity;
import com.giago.ecard.utils.analytic.Tracker;

public class SearchableActivity extends ActionBarEcardActivity {

    public static final String JARGON = "jargon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Intent intent = getIntent();
        handleIntent(intent);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void handleIntent(Intent intent) {
        Log.v("dev", "SearchableActivity --- handleIntent");
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.v("dev", query);
        }
    }

    @Override
    protected void trackPageView(Tracker tracker) {
        tracker.searcheableActivity();
        
    }
}
