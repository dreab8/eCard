package com.giago.ecard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.giago.ecard.R;
import com.giago.ecard.utils.Template;

public class Show extends Activity  {

	private static final String TEXT_HTML = "text/html";
	private static final String UTF_8 = "utf-8";
	private static final String FILE_ANDROID_ASSET = "file:///android_asset/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Intent i = getIntent();
		Template template = new Template(i);
		String formatted = template.format(getApplicationContext());
		initializeWebView(formatted);
	}
	
	private void initializeWebView(String template) {
		WebView wv = (WebView)findViewById(R.id.ecardwebview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadDataWithBaseURL(FILE_ANDROID_ASSET, 
				template, TEXT_HTML, UTF_8, FILE_ANDROID_ASSET);
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	}

}
