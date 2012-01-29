package com.giago.ecard.activity;

import java.nio.charset.Charset;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.os.Bundle;
import android.os.Parcelable;

public abstract class BeamActivity extends Activity implements CreateNdefMessageCallback {
	
	private static final String US_ASCII = "US-ASCII";
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
	protected void onResume() {
		super.onResume();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			setIntent(processNfcIntent(getIntent()));
		}
	}
	
	private Intent processNfcIntent(Intent intent) {
		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String message = new String(msg.getRecords()[0].getPayload());
        return processNfcIntent(intent, message);
	}

	protected abstract Intent processNfcIntent(Intent intent, String message);

	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
		return new NdefMessage(createMimeRecord());
	}

	protected abstract String getBeamMimeType();
	
	protected abstract String getBeamMessage();
	
	private NdefRecord[] createMimeRecord() {
		String mimeType = getBeamMimeType();
		byte[] payload = getBeamMessage().getBytes(); 
        byte[] mimeBytes = mimeType.getBytes(Charset.forName(US_ASCII));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return new NdefRecord[] {mimeRecord};
    }
}
