package com.giago.ecard.activity;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.giago.ecard.R;

public class Show extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
	}
	
	@Override
	protected void onResume() {
		super.onResume();		
		Intent i = createIntent();
		
		String template = readAsset("basic_blue.html");
		template = replaceParamFromIntent(i, "company", template);
		template = replaceParamFromIntent(i, "name", template);
		template = replaceParamFromIntent(i, "phone", template);
		template = replaceParamFromIntent(i, "email", template);
		template = replaceParamFromIntent(i, "note", template);
		template = replaceParamFromIntent(i, "role", template);
		template = replaceParamFromIntent(i, "qrdata", template);
		
		WebView wv = (WebView)findViewById(R.id.ecardwebview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadDataWithBaseURL("file:///android_asset/", 
				template, "text/html", "utf-8", "file:///android_asset/");
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	}

	private Intent createIntent() {
		Intent i = new Intent("example");
		i.putExtra("company", "Giago Software Ltd");
		i.putExtra("role", "Developer");
		i.putExtra("name", "Luigi Agosti");
		i.putExtra("phone", "M: +4492830233");
		i.putExtra("email", "admin@gi-ago.com");
		i.putExtra("note", "T: @luigiagosti");
		i.putExtra("qrdata", "https://plus.google.com/102048052961229488268/posts");
		return i;
	}
	
	private String replaceParamFromIntent(Intent i, String param, String data) {
		String value = i.getStringExtra(param);
		if(value == null) {
			value = "";
		}
		return data.replaceAll(param + "value", value);
	}
	
	private String readAsset(String filename) {
		try {
	        InputStream is = getAssets().open(filename);
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        return new String(buffer);
		} catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
}
