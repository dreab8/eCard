package com.giago.ecard;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

import com.giago.ecard.utils.Environment;

@ReportsCrashes(formKey = "dE1UTUM0ekpkeXFCLWRFbklXb2VBYmc6MQ")
public class Ecard extends Application {
	
	private static boolean DEV_MODE = false;
	
	@Override
    public void onCreate() {
        super.onCreate();
        verifyEnvironment();
        if(isDevMode()) {
        	return;
        }
        setUpProductionTools();
    }

	private void setUpProductionTools() {
		ACRA.init(this);
	}
	
	public static boolean isDevMode() {
    	return DEV_MODE;
    }
    
    private void verifyEnvironment() {
  	  DEV_MODE = new Environment().isDevelopmentMode(getApplicationContext());
    }
}
