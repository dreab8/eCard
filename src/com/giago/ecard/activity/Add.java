package com.giago.ecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.giago.ecard.R;
import com.giago.ecard.activity.fragment.AddFragment;
import com.giago.ecard.activity.utils.EcardFragmentActivity;
import com.giago.ecard.utils.analytic.Tracker;

public class Add extends EcardFragmentActivity {
	
    public static final int ADD = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if (getAddFragment() == null) {
            addFragment();
        }
    }

	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.add();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	finish();
                break;
            case R.id.menu_next:
            	preview();
                break;
        }
        return super.onOptionsItemSelected(item);
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
    
    private void addFragment() {
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
