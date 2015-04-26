package com.vikram.openidconnect.login.web.util;

import com.vikram.openidconnect.login.core.providers.OAuthProvider;

public class AuthCodeResolver {
	
	public static OAuthProvider resolveState(String state){
		return OAuthProvider.GOOGLE;
	}

}
