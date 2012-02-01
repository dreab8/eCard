package com.giago.ecard.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.giago.ecard.R;
import com.giago.ecard.activity.fragment.AddFragment;
import com.giago.ecard.activity.utils.ActionBarEcardActivity;
import com.giago.ecard.utils.analytic.Tracker;

public class Add extends ActionBarEcardActivity {
	
    public static final int ADD = 1;
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if (getAddFragment() == null) {
            initFragment();
        }
    }

	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.add();
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_next:
            	preview();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void inflateMenu(Menu menu, MenuInflater menuInflater) {
    	menuInflater.inflate(R.menu.add, menu);
	}
    
    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			finish();
		}
	}

    private AddFragment getAddFragment() {
		return (AddFragment)getSupportFragmentManager().findFragmentById(android.R.id.content);
	}
    
    private void initFragment() {
		getSupportFragmentManager().beginTransaction()
			.add(android.R.id.content, new AddFragment()).commit();
	}
    
	private void preview() {
		AddFragment af = getAddFragment();
		if(af == null) {
			return;
		}
		af.preview(getApplicationContext());
	}
}
