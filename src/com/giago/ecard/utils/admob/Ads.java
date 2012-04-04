package com.giago.ecard.utils.admob;

import com.google.ads.AdRequest;

public class Ads {
	
	public static final AdRequest getAdsRequest() {
		AdRequest r = new AdRequest();
		r.addTestDevice("FA5F9A70553CF8C36A4CF51D25EA7492");
		r.addTestDevice("3E71177E5F1D411D3C545BC61A3F5DA9");
		r.addTestDevice("767C8F360D089DB8B782BE594230C783");
		r.addTestDevice("647C993B1ACCD63736433785C7BBF337");
		r.addTestDevice("FB47B8A732F11E53157F609D8085DCCC");
		r.addTestDevice("D3AC8E44864F6C88711DC2B3D2ABA2C2");
		r.addTestDevice("D70EBE9ACE1685D1B8831F6543364338");
		r.addTestDevice("7B7C513F195CC6D32AEB0F0D39905A96");
		r.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB");
		r.addTestDevice("CF95DC53F383F9A836FD749F3EF439CD");
		r.addTestDevice("9774BB5E1003BC5B3C01570F6615CA4B");
		r.addTestDevice("BBD075A96C8E317944A71745B56F86E7");
		//add here new test devices
		
		return r;
	}
	
}
