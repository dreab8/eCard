package com.giago.ecard.service;

import com.giago.ecard.activity.intent.EcardIntent;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;

public class EcardDao extends IntentService {

	public static final String ACTION = "com.giago.ecard.ECARD_DAO";
	public static final Uri ECARD_URI = Uri.parse("content://com.giago.ecard/ecard");
	
	public EcardDao() {
		super("EcardDao");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		EcardIntent ei = new EcardIntent(intent);
		ContentValues cvs = ei.getContentValues();
		getContentResolver().insert(ECARD_URI, cvs);
	}

}
