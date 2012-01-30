package com.giago.ecard.activity;

import java.nio.charset.Charset;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;

import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.utils.analytic.Tracker;

public class ShowAndBeam extends Show implements CreateNdefMessageCallback {

	private static final String BEAM_MIME_TYPE = "application/com.giago.ecard.beam";
	private static final String US_ASCII = "US-ASCII";
	private static final byte[] MIME_BYTES = BEAM_MIME_TYPE.getBytes(Charset.forName(US_ASCII));
	private NfcAdapter nfcAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter != null) {
			nfcAdapter.setNdefPushMessageCallback(this, this);
		}
	}
	
	@Override
	protected boolean isActionBarEnabled() {
		return false;
	}
	
	@Override
	protected void trackPageView(Tracker tracker) {
		tracker.showAndBeam();
	}

	@Override
	protected void onResume() {
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			Intent i = processNfcIntent(getIntent());
			setIntent(i);
			startService(createEcardIntent(i).convertToInsertIntent());
		}
		super.onResume();
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
		return new NdefMessage(createMimeRecord());
	}

	private Intent processNfcIntent(Intent i) {
		Parcelable[] rawMsgs = i.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		NdefMessage msg = (NdefMessage) rawMsgs[0];
		String message = new String(msg.getRecords()[0].getPayload());
		return createEcardIntent(i).fromStringToExtras(message);
	}

	private NdefRecord[] createMimeRecord() {
		byte[] payload = createEcardIntent(getIntent()).intentExtrasToBytes();
		NdefRecord mimeRecord = new NdefRecord(
				NdefRecord.TNF_MIME_MEDIA, MIME_BYTES, new byte[0], payload);
		return new NdefRecord[] { mimeRecord };
	}
	
	private EcardIntent createEcardIntent(Intent intent) {
		return new EcardIntent(getIntent());
	}

}
