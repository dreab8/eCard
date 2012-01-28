package com.giago.ecard.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.giago.ecard.activity.fragment.EcardsFragment;

public class Ecards extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            EcardsFragment list = new EcardsFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
        }
    }
  
}