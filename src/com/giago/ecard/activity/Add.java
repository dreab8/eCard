package com.giago.ecard.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.giago.ecard.R;
import com.giago.ecard.activity.fragment.AddFragment;
import com.giago.ecard.activity.utils.EcardFragmentActivity;
import com.giago.ecard.utils.analytic.Tracker;

public class Add extends EcardFragmentActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            AddFragment add = new AddFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, add).commit();
        }
    }

	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.add();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
