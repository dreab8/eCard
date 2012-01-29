package com.giago.ecard.activity;

import com.giago.ecard.activity.fragment.AddFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Add extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            AddFragment add = new AddFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, add).commit();
        }
    }
}
