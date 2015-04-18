package com.vikram.openconnect.login.util;

import com.vikram.openconnect.login.providers.OAuthProvider;

public class AuthCodeResolver {
	
	public static OAuthProvider resolveAuthCode(String authCode){
		return OAuthProvider.GOOGLE;
	}

}
