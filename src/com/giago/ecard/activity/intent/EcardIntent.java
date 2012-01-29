package com.giago.ecard.activity.intent;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

public class EcardIntent {
	
	public static final List<String> EXTRAS = 
			Arrays.asList("company", "name", "phone", "email", "note", "role", "qrdata");
	
	private Intent intent;
	
	public EcardIntent(Intent intent) {
		this.intent = intent;
	}
	
	public EcardIntent(Cursor cursor, Context context, Class<? extends Activity> clazz) {
        intent = new Intent(context, clazz);
        for(String param : EXTRAS) {
        	addExtra(param, cursor);
        }
	}

	public Intent getIntent() {
		return intent;
	}
	
	public String intentExtrasToString() {
		StringBuilder sb = new StringBuilder();
		for(String param : EXTRAS) {
			appendParam(intent, sb, param);
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}

	public Intent fromStringToExtras(String valuesPairs) {
		Log.v("dev", "message : " + valuesPairs);
        for(String paramValuePair : valuesPairs.split(",")) {
        	Log.v("dev", "pair : " + paramValuePair);
        	String[] paramValue = paramValuePair.split("-");
        	intent.putExtra(paramValue[0], paramValue[1]);
        }
        return intent;
	}
	
	private void appendParam(Intent i, StringBuilder sb, String param) {
		String value = i.getStringExtra(param);
		if(value == null) {
			return;
		}
		sb.append(param).append("-").append(value);
	}
	
	private void addExtra(String param, Cursor cursor) {
	    cursor.moveToFirst();
		String value = cursor.getString(cursor.getColumnIndex(param));
		if(value != null) {
			intent.putExtra(param, value);
		}
	}
	
}
