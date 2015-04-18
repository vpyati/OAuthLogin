package com.vikram.openconnect.login.input;

import com.vikram.openconnect.login.providers.OAuthProvider;

public interface ICredentialInput {
	
	public String getClientId();
	
	public String getClientSecret();
	
	public String getRedirectUri();
	
	public OAuthProvider getProvider();
}
