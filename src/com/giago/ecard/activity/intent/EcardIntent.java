package com.giago.ecard.activity.intent;

import java.util.Arrays;
import java.util.List;

import com.giago.ecard.activity.Show;
import com.giago.ecard.activity.ShowAndBeam;
import com.giago.ecard.service.EcardDao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

public class EcardIntent {
	
	public static final String QRDATA = "qrdata";
	public static final String ROLE = "role";
	public static final String NOTE = "note";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String COMPANY = "company";
	public static final String TEMPLATE = "template";

	public static final List<String> EXTRAS = 
			Arrays.asList(COMPANY, NAME, PHONE, EMAIL, NOTE, ROLE, QRDATA, TEMPLATE);
	
	private Intent intent;
	
	public EcardIntent(Intent intent) {
		this.intent = intent;
	}
	
	public EcardIntent(Cursor cursor, Context context) {
        intent = new Intent(context, getTheSupportedShowActivityClass());
        for(String param : EXTRAS) {
        	addExtra(param, cursor);
        }
	}
	
	public EcardIntent( Context context) {
        intent = new Intent(context, getTheSupportedShowActivityClass());
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
	
	public void putExtra(String param, String value){
	    intent.putExtra(param, value);
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
		String value = cursor.getString(cursor.getColumnIndex(param));
		if(value != null) {
			Log.v("dev", "value of param : " + param + " is " + value);
			intent.putExtra(param, value);
		}
	}
	
	private Class<? extends Activity> getTheSupportedShowActivityClass() {
        Class<? extends Activity> clazz = null;
        try {
            new android.nfc.NdefMessage("".getBytes());
            clazz = ShowAndBeam.class;
        } catch (java.lang.NoClassDefFoundError e) {
            clazz = Show.class;
        } catch (Exception e) {
            clazz = ShowAndBeam.class;
        }
        return clazz;
    }

}
