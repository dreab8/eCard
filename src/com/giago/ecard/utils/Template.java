package com.giago.ecard.utils;

import java.io.IOException;
import java.io.InputStream;

import com.giago.ecard.activity.intent.EcardIntent;

import android.content.Context;
import android.content.Intent;

public class Template {
	
	private static final String TEMPLATE_TOKEN = "value";
	private static final String HTML = ".html";
	private String templateName;
	private String template;
	
	public Template(String templateName) {
		this.templateName = templateName;
	}

	public String format(Context c, Intent i) {
		template = readAssetAsString(c);
		format(i);
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
	
	private void format(Intent i) {
		for(String param : EcardIntent.EXTRAS) {
			template = replaceParamFromIntent(i, param);
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
