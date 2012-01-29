package com.giago.ecard.utils;

import java.io.IOException;
import java.io.InputStream;

import com.giago.ecard.activity.intent.EcardIntent;

import android.content.Context;
import android.content.Intent;

public class Template {
	
	private static final String DEFAULT_TEMPLATE = "default";
	private static final String TEMPLATE_TOKEN = "value";
	private static final String HTML = ".html";
	private String templateName;
	private String template;
	private Intent intent;
	
	public Template(Intent intent) {
		this.intent = intent;
		String value = intent.getStringExtra(EcardIntent.TEMPLATE);
		if(value == null) {
			value = DEFAULT_TEMPLATE;
		}
		this.templateName = value;
	}

	public String format(Context c) {
		template = readAssetAsString(c);
		format();
		return template;
	}
	
	private String readAssetAsString(Context c) {
		try {
	        InputStream is = c.getAssets().open(templateName + HTML);
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        return new String(buffer);
		} catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	private void format() {
		for(String param : EcardIntent.EXTRAS) {
			if(!EcardIntent.TEMPLATE.equals(param)) {
				template = replaceParamFromIntent(intent, param);
			}
		}
	}

	private String replaceParamFromIntent(Intent i, String param) {
		String value = i.getStringExtra(param);
		if(value == null) {
			value = "";
			
		}
		return template.replaceAll(param + TEMPLATE_TOKEN, value);
	}
}
