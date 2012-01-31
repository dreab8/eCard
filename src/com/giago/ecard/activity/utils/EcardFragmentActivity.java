package com.giago.ecard.activity.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.giago.ecard.utils.actionbar.ActionBarHelper;
import com.giago.ecard.utils.analytic.Tracker;

public abstract class EcardFragmentActivity extends FragmentActivity {

	private Tracker tracker;
	private ActionBarHelper actionBarHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = new Tracker(this);
		trackPageView(tracker);
		actionBarHelper = ActionBarHelper.createInstance(this);
		actionBarHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public void onDestroy() {
		if(tracker != null) {
			tracker.stopSession();
		}
		super.onDestroy();
	}

	protected abstract void trackPageView(Tracker tracker);
	
	protected ActionBarHelper getActionBarHelper() {
        return actionBarHelper;
    }
	
	@Override
    public MenuInflater getMenuInflater() {
        return actionBarHelper.getMenuInflater(super.getMenuInflater());
    }
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarHelper.onPostCreate(savedInstanceState);
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = false;
        retValue |= actionBarHelper.onCreateOptionsMenu(menu);
        retValue |= super.onCreateOptionsMenu(menu);
        return retValue;
    }
	
	protected void onTitleChanged(CharSequence title, int color) {
        actionBarHelper.onTitleChanged(title, color);
        super.onTitleChanged(title, color);
    }
}
