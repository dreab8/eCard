package com.giago.ecard.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;

public class AddFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);
        Button preview = (Button) view.findViewById(R.id.preview_button);
        preview.setOnClickListener(new OnPreviewClickListener());
        return view;
    }

    private class OnPreviewClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
        	View view = getView();
        	Context applicationContext = view.getContext().getApplicationContext();
            EcardIntent i = new EcardIntent(applicationContext);
            populateIntentFromView(view, i);
            startActivity(i.getIntent());
        }
    }
    
    private void populateIntentFromView(View view, EcardIntent ei) {
    	String name = ((EditText) view.findViewById(R.id.name)).getText().toString();
    	String company = ((EditText) view.findViewById(R.id.company)).getText().toString();
    	String role = ((EditText) view.findViewById(R.id.role)).getText().toString();
    	String phone = ((EditText) view.findViewById(R.id.phone)).getText().toString();
    	String email = ((EditText) view.findViewById(R.id.email)).getText().toString();
    	String note = ((EditText) view.findViewById(R.id.note)).getText().toString();
    	ei.putExtra(EcardIntent.NAME, name);
    	ei.putExtra(EcardIntent.COMPANY, company);
    	ei.putExtra(EcardIntent.ROLE, role);
    	ei.putExtra(EcardIntent.PHONE, phone);
    	ei.putExtra(EcardIntent.EMAIL, email);
    	ei.putExtra(EcardIntent.NOTE, note);
    }

}