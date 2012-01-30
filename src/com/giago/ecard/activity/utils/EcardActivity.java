package com.giago.ecard.activity.utils;

import com.giago.ecard.utils.analytic.Tracker;

import android.app.Activity;
import android.os.Bundle;

public abstract class EcardActivity extends Activity {

	private Tracker tracker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = new Tracker(this);
		trackPageView(tracker);
	}
	
	@Override
	public void onDestroy() {
		if(tracker != null) {
			tracker.stopSession();
		}
		super.onDestroy();
	}

	protected abstract void trackPageView(Tracker tracker);
}
