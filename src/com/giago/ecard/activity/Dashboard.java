package com.giago.ecard.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.activity.utils.ActionBarEcardActivity;
import com.giago.ecard.service.EcardDao;
import com.giago.ecard.utils.analytic.Tracker;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Dashboard extends ActionBarEcardActivity {

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        add = (Button) findViewById(R.id.add_button);
        

        ((Button) findViewById(R.id.ecards)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EcardIntent.getEcardActivityIntent(Dashboard.this));
            }
        });

        ((Button) findViewById(R.id.scan)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator.initiateScan(Dashboard.this, getString(R.string.zx_title),
                        getString(R.string.zx_message), getString(R.string.zx_pos), getString(R.string.zx_neg),
                        IntentIntegrator.PRODUCT_CODE_TYPES);
            }
        });

        setAdmobViewIfEnabled();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = getContentResolver()
                .query(EcardDao.ECARD_URI, null, "personal = ?", new String[] { "1" }, null);
        if (cursor.getCount() > 0) {
            add.setText(R.string.dashboard_myecards);
            add.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(EcardIntent.getPersonalEcardActivityIntent(Dashboard.this));
                }
            });
           
        } else {
            add.setText(R.string.dashboard_add);
            add.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Add.class));
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == IntentIntegrator.BARCODE_REQUEST) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanResult.getContents() != null) {
                Log.v("dev", "content of scan : " + scanResult.getContents());
                // TODO reuse the scan
            }
        }
    }

    @Override
    protected void trackPageView(Tracker tracker) {
        tracker.dashboard();
    }

}
