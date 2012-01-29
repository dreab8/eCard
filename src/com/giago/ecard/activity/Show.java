package com.giago.ecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.utils.Template;

public class Show extends BeamActivity  {

	private static final String TEXT_HTML = "text/html";
	private static final String UTF_8 = "utf-8";
	private static final String FILE_ANDROID_ASSET = "file:///android_asset/";
	private static final String BEAM_MIME_TYPE = "application/com.giago.ecard.beam";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		Template template = new Template("basic_blue");
		Intent i = getIntent();
		
		String formatted = template.format(getApplicationContext(), i);
		initializeWebView(formatted);
	}

	@Override
	protected String getBeamMimeType() {
		return BEAM_MIME_TYPE;
	}

	@Override
	protected String getBeamMessage() {
		return new EcardIntent(getIntent()).intentExtrasToString();
	}
	
	@Override
	protected Intent processNfcIntent(Intent intent, String message) {
		return new EcardIntent(intent).fromStringToExtras(message);
	}
	
	private void initializeWebView(String template) {
		WebView wv = (WebView)findViewById(R.id.ecardwebview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadDataWithBaseURL(FILE_ANDROID_ASSET, 
				template, TEXT_HTML, UTF_8, FILE_ANDROID_ASSET);
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	}

	
	
	

}
