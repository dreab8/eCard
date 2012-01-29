package com.giago.ecard.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.giago.ecard.R;
import com.giago.ecard.activity.Ecards;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.service.EcardDao;

public class AddFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);
        Button add = (Button) view.findViewById(R.id.add_button);
        Button preview = (Button) view.findViewById(R.id.preview_button);
        add.setOnClickListener(new OnAddClickListener());
        preview.setOnClickListener(new OnPreviewClickListener());
        return view;
    }

    private class OnAddClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Context applicationContext = save();

            startActivity(new Intent(applicationContext, Ecards.class));
        }

        
        private Context save() {

            View view = getView();
            Context applicationContext = view.getContext().getApplicationContext();
            String name = ((EditText) view.findViewById(R.id.name)).getText().toString();
            String company = ((EditText) view.findViewById(R.id.company)).getText().toString();
            String role = ((EditText) view.findViewById(R.id.role)).getText().toString();
            String phone = ((EditText) view.findViewById(R.id.phone)).getText().toString();
            String email = ((EditText) view.findViewById(R.id.email)).getText().toString();
            String note = ((EditText) view.findViewById(R.id.note)).getText().toString();

            Intent save = new Intent(EcardDao.ACTION);
            save.putExtra(EcardIntent.NAME, name);
            save.putExtra(EcardIntent.COMPANY, company);
            save.putExtra(EcardIntent.ROLE, role);
            save.putExtra(EcardIntent.PHONE, phone);
            save.putExtra(EcardIntent.EMAIL, email);
            save.putExtra(EcardIntent.NOTE, note);

            applicationContext.startService(save);
            return applicationContext;
        }
    }

    private class OnPreviewClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            
            View view = getView();
            Context applicationContext = view.getContext().getApplicationContext();
            String name = ((EditText) view.findViewById(R.id.name)).getText().toString();
            String company = ((EditText) view.findViewById(R.id.company)).getText().toString();
            String role = ((EditText) view.findViewById(R.id.role)).getText().toString();
            String phone = ((EditText) view.findViewById(R.id.phone)).getText().toString();
            String email = ((EditText) view.findViewById(R.id.email)).getText().toString();
            String note = ((EditText) view.findViewById(R.id.note)).getText().toString();

            EcardIntent ei = new EcardIntent(applicationContext);
            ei.putExtra(EcardIntent.NAME, name);
            ei.putExtra(EcardIntent.COMPANY, company);
            ei.putExtra(EcardIntent.ROLE, role);
            ei.putExtra(EcardIntent.PHONE, phone);
            ei.putExtra(EcardIntent.EMAIL, email);
            ei.putExtra(EcardIntent.NOTE, note);
            
            startActivity(ei.getIntent());
        }
    }

    

}
