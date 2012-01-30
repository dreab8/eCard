package com.giago.ecard.utils.analytic;

import android.content.Context;

import com.giago.ecard.Ecard;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class Tracker {

	private static final String ANALYTICS_UID = "UA-28771822-1";

	private static final String BUTTON_CATEGORY = "button";
	private static final String CLICK_ACTION = "click";

	private static interface PageView {
		String dashboard = "/dashboard";
		String show = "/show";
		String showAndBeam = "/showAndBeam";
		String install = "/install";
	}

	private static interface Event {
		String insert = "insert";
		String beam = "beam";
	}

	private GoogleAnalyticsTracker tracker;

	public Tracker(Context context) {
		if (Ecard.isDevMode()) {
			return;
		}
		if (tracker != null) {
			return;
		}
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.startNewSession(ANALYTICS_UID, 20, context);
	}

	public void stopSession() {
		if (tracker == null) {
			return;
		}
		tracker.stopSession();
	}

	// =====================================
	// Track Pages
	// =====================================

	public void show() {
		trackPageView(PageView.show);
	}

	public void showAndBeam() {
		trackPageView(PageView.showAndBeam);
	}

	public void dashboard() {
		trackPageView(PageView.dashboard);
	}
	
	public void install() {
		trackPageView(PageView.install);
	}

	// =====================================
	// Track events
	// =====================================

	public void insertEvent() {
		trackButtonEvent(Event.insert, 0);
	}

	public void beamEvent() {
		trackButtonEvent(Event.beam, 0);
	}
	
	// =====================================
	// Utility methods
	// =====================================

	private void trackPageView(String page) {
		if (tracker == null) {
			return;
		}
		tracker.trackPageView(page);
	}

	private void trackButtonEvent(String label, int position) {
		if (tracker == null) {
			return;
		}
		tracker.trackEvent(BUTTON_CATEGORY, CLICK_ACTION, label, position);
	}

}
