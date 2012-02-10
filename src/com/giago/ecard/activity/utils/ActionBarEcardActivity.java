package com.giago.ecard.activity.utils;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.giago.ecard.R;

public abstract class ActionBarEcardActivity extends EcardActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        inflateMenu(menu, menuInflater);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	finish();
                break;
            case R.id.menu_search:
                Log.v("dev", "menu_search");
                onSearchRequested();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    protected void inflateMenu(Menu menu, MenuInflater menuInflater) {
		menuInflater.inflate(R.menu.main, menu);
	}

}