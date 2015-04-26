package com.vikram.openconnect.login.web.util;

import com.vikram.openconnect.login.core.providers.OAuthProvider;

public class AuthCodeResolver {
	
	public static OAuthProvider resolveState(String state){
		return OAuthProvider.GOOGLE;
	}

}
