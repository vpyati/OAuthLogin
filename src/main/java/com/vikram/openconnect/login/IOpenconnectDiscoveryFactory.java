package com.vikram.openconnect.login;

import com.vikram.openconnect.login.providers.OAuthProvider;

public interface IOpenconnectDiscoveryFactory {
	
	IOpenconnectDiscovery get(OAuthProvider oauthProvider);
}
