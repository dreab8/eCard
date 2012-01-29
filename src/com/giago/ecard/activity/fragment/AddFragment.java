package com.giago.ecard.activity.fragment;

import com.giago.ecard.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddFragment extends Fragment implements OnClickListener  {
    
    
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);
        Button add  = (Button) view.findViewById(R.id.add_button);
        add.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String name = ((EditText)v.findViewById(R.id.name)).getText().toString();
        String company = ((EditText)v.findViewById(R.id.company)).getText().toString();
        String role = ((EditText)v.findViewById(R.id.role)).getText().toString();
        String phone = ((EditText)v.findViewById(R.id.phone)).getText().toString();
        String email = ((EditText)v.findViewById(R.id.email)).getText().toString();
        String note = ((EditText)v.findViewById(R.id.note)).getText().toString();
    
    }

}
