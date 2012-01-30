package com.giago.ecard.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.giago.ecard.R;
import com.giago.ecard.activity.fragment.EcardsFragment;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.activity.utils.EcardFragmentActivity;
import com.giago.ecard.utils.analytic.Tracker;

public class Ecards extends EcardFragmentActivity {

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
            	finish();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "Implement search", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
  
}