package com.giago.ecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.activity.utils.EcardActivity;
import com.giago.ecard.utils.Template;
import com.giago.ecard.utils.analytic.Tracker;

public class Show extends EcardActivity {

	private static final String TEXT_HTML = "text/html";
	private static final String UTF_8 = "utf-8";
	private boolean previewMode = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
	}
	
	@Override
	protected boolean isActionBarEnabled() {
		return false;
	}
	
	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.show();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Intent i = getIntent();
		checkMode(i);
		Template template = new Template(i);
		String formatted = template.format(getApplicationContext());
		initializeWebView(formatted);
	}

	private void checkMode(Intent i) {
		if(new EcardIntent(i).isPreview()) {
			previewMode = true;
			return;
		}
		previewMode = false;
	}
	
	private void initializeWebView(String template) {
		WebView wv = (WebView)findViewById(R.id.ecardwebview);
		wv.getSettings().setJavaScriptEnabled(true);
		String path = Template.TEMPLATE_PATH;
		wv.loadDataWithBaseURL(path, template, TEXT_HTML, UTF_8, path);
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		showPreviewBar();
	}

	private void showPreviewBar() {
		if(!previewMode) {
			return;
		}
		findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
		findViewById(R.id.save).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View paramView) {
				//TODO Need to set the correct template
				//TODO set the type, personal/not personal
				startService(new EcardIntent(getIntent()).convertToInsertIntent());
				setResult(RESULT_OK, null);
				finish();
			}
		});
	}

}
