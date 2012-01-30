package com.giago.ecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;
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
			    startActivity(EcardIntent.getEcardActivityIntent(Dashboard.this));
			}
		});
		
		((Button)findViewById(R.id.myecards)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			    startActivity(EcardIntent.getPersonalEcardActivityIntent(Dashboard.this));
			}

		});
		
		//TODO scan
	}
	
	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.dashboard();
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
                break;
            case R.id.menu_search:
                Toast.makeText(this, "Implement search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_share:
                Toast.makeText(this, "Implement share", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
