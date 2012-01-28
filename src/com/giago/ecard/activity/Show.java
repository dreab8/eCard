package com.giago.ecard.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.giago.ecard.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class Show extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		String company = encodeParameter("Giago Software Ltd");
		String name = encodeParameter("Luigi Agosti");
		String phone = encodeParameter("M: +4492830233");
		String email = encodeParameter("admin@gi-ago.com");
		String note = encodeParameter("T: @luigiagosti");
		String qrcode = encodeParameter("https://plus.google.com/102048052961229488268/posts");
		
		WebView wv = (WebView)findViewById(R.id.ecardwebview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("file:///android_asset/ecard.html?company="+ company 
				+ "&name=" + name + "&phone=" + phone + "&email=" 
				+ email + "&note=" + note + "&qrcode=" + qrcode);
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	private static final String ENCODING = "UTF-8";
	
	protected String encodeParameter(String parameter) {
		try {
			return URLEncoder.encode(parameter, ENCODING);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
