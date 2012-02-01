package com.giago.ecard.activity.utils;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
                Toast.makeText(this, "Implement search", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    protected void inflateMenu(Menu menu, MenuInflater menuInflater) {
		menuInflater.inflate(R.menu.main, menu);
	}

}