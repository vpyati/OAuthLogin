package com.vikram.openconnect.login.input;

import com.vikram.openconnect.login.providers.OAuthProvider;

public interface IOAuthCredentials {
	
	public ICredentialInput getCredentialByProvider(OAuthProvider provider);

}
