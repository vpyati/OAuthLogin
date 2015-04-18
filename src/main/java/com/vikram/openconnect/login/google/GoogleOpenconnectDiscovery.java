package com.vikram.openconnect.login.google;

import com.vikram.openconnect.login.DefaultOpenconnectDiscovery;

public class GoogleOpenconnectDiscovery extends DefaultOpenconnectDiscovery {

	private static String URL = "https://accounts.google.com/.well-known/openid-configuration";

	public GoogleOpenconnectDiscovery() {
		super(URL);
	}	
}