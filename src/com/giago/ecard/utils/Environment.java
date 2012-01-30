package com.giago.ecard.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class Environment {

	private static final String DEV_VERSION_INDICATOR = "SNAPSHOT";

	public String getApplicationVersionName(Context c) {
		String packageName = c.getPackageName();
		PackageInfo pInfo;
		try {
			pInfo = c.getPackageManager().getPackageInfo(packageName,
					PackageManager.GET_META_DATA);
			return "" + pInfo.versionName;
		} catch (NameNotFoundException e) {
			return "";
		}
	}

	public boolean isDevelopmentMode(Context c) {
		return getApplicationVersionName(c).contains(DEV_VERSION_INDICATOR);
	}

}
