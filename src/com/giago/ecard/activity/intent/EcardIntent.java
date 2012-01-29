package com.giago.ecard.activity.intent;

import java.util.Arrays;
import java.util.List;

import com.giago.ecard.service.EcardDao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

public class EcardIntent {
	
	public static final String QRDATA = "qrdata";
	public static final String ROLE = "role";
	public static final String NOTE = "note";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String COMPANY = "company";

	public static final List<String> EXTRAS = 
			Arrays.asList(COMPANY, NAME, PHONE, EMAIL, NOTE, ROLE, QRDATA);
	
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
			appendParam(sb, param);
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}

	public Intent fromStringToExtras(String valuesPairs) {
        for(String paramValuePair : valuesPairs.split(",")) {
        	String[] paramValue = paramValuePair.split("-");
        	intent.putExtra(paramValue[0], paramValue[1]);
        }
        return intent;
	}
	
	public ContentValues getContentValues() {
		ContentValues cvs = new ContentValues();
		for(String param : EXTRAS) {
			putParamOnContentValues(cvs, param);
		}
		return cvs;
	}
	
	public Intent convertToInsertIntent() {
		Intent newIntent = new Intent(EcardDao.ACTION);
		String extras = intentExtrasToString();
		return new EcardIntent(newIntent).fromStringToExtras(extras);
	}
	
	public byte[] intentExtrasToBytes() {
		return intentExtrasToString().getBytes();
	}
	
	private void putParamOnContentValues(ContentValues cvs, String param) {
		String value = intent.getStringExtra(param);
		if(value == null) {
			return;
		}
		cvs.put(param, value);
	}

	private void appendParam(StringBuilder sb, String param) {
		String value = intent.getStringExtra(param);
		if(value == null) {
			return;
		}
		sb.append(param).append("-").append(value).append(",");
	}
	
	private void addExtra(String param, Cursor cursor) {
	    cursor.moveToFirst();
		String value = cursor.getString(cursor.getColumnIndex(param));
		if(value != null) {
			intent.putExtra(param, value);
		}
	}

}
