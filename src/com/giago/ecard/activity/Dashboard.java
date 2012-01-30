package com.giago.ecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.giago.ecard.R;
import com.giago.ecard.activity.utils.EcardActivity;
import com.giago.ecard.utils.analytic.Tracker;

public class Dashboard extends EcardActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		((Button)findViewById(R.id.add)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Dashboard.this, Add.class));
			}
		});
		
		((Button)findViewById(R.id.ecards)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Dashboard.this, Ecards.class));
			}
		});
		
		((Button)findViewById(R.id.myecards)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Dashboard.this, Ecards.class));
				//TODO
			}
		});
		
		//TODO scan
		
	}
	
	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.dashboard();
	}

}
