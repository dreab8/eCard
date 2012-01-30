package com.giago.ecard.utils.analytic;

import android.content.Context;
import android.content.Intent;

import com.google.android.apps.analytics.AnalyticsReceiver;

public class ReferralReceiver extends AnalyticsReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		new Tracker(context).install();
	}
}
