package com.giago.ecard.activity.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.giago.ecard.R;
import com.giago.ecard.utils.actionbar.ActionBarHelper;
import com.giago.ecard.utils.admob.Ads;
import com.giago.ecard.utils.analytic.Tracker;
import com.google.ads.AdView;

public abstract class EcardActivity extends Activity {

	private Tracker tracker;
	private ActionBarHelper actionBarHelper;
	private AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = new Tracker(this);
		trackPageView(tracker);
		if(!isActionBarEnabled()) {
			return;
		}
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
	
	protected boolean isActionBarEnabled() {
		return true;
	}
	
	protected ActionBarHelper getActionBarHelper() {
        return actionBarHelper;
    }
	
	@Override
    public MenuInflater getMenuInflater() {
		if(!isActionBarEnabled()) {
			return super.getMenuInflater();
		}
        return actionBarHelper.getMenuInflater(super.getMenuInflater());
    }
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(!isActionBarEnabled()) {
			return;
		}
        actionBarHelper.onPostCreate(savedInstanceState);
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		if(!isActionBarEnabled()) {
			return super.onCreateOptionsMenu(menu);
		}
        boolean retValue = false;
        retValue |= actionBarHelper.onCreateOptionsMenu(menu);
        retValue |= super.onCreateOptionsMenu(menu);
        return retValue;
    }
	
	protected void onTitleChanged(CharSequence title, int color) {
		if(!isActionBarEnabled()) {
			super.onTitleChanged(title, color);
			return;
		}
        actionBarHelper.onTitleChanged(title, color);
        super.onTitleChanged(title, color);
    }
	
	protected void setAdmobViewIfEnabled() {
	    adView = (AdView) findViewById(R.id.ad);
	    adView.loadAd(Ads.getAdsRequest());
	    adView.setVisibility(View.VISIBLE);
	}

}