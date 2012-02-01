package com.giago.ecard.activity;

import android.os.Bundle;

import com.giago.ecard.activity.fragment.EcardsFragment;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.activity.utils.ActionBarEcardActivity;
import com.giago.ecard.utils.analytic.Tracker;

public class Ecards extends ActionBarEcardActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String isPersonal = getIntent().getStringExtra(EcardIntent.IS_PERSONAL);
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            EcardsFragment list = new EcardsFragment(isPersonal);
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
        }
    }

	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.ecards();
	}
  
}