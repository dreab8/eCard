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

public class ShowAndBeam extends Show implements CreateNdefMessageCallback {

	private static final String BEAM_MIME_TYPE = "application/com.giago.ecard.beam";
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
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			setIntent(processNfcIntent(getIntent()));
		}
		super.onResume();
	}
	
	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
		return new NdefMessage(createMimeRecord());
	}

	private Intent processNfcIntent(Intent intent) {
		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String message = new String(msg.getRecords()[0].getPayload());
        return processNfcIntent(intent, message);
	}

	private String getBeamMessage() {
		return new EcardIntent(getIntent()).intentExtrasToString();
	}
	
	private Intent processNfcIntent(Intent intent, String message) {
		return new EcardIntent(intent).fromStringToExtras(message);
	}

	private NdefRecord[] createMimeRecord() {
		byte[] payload = getBeamMessage().getBytes(); 
        byte[] mimeBytes = BEAM_MIME_TYPE.getBytes(Charset.forName(US_ASCII));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return new NdefRecord[] {mimeRecord};
    }

}
