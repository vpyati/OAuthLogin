package com.vikram.openconnect.login.identity;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;

import com.vikram.openconnect.login.IIdentityFetcher;
import com.vikram.openconnect.login.providers.OAuthProvider;

public class AuthCodeIdentity implements Identity {

	private JSONObject tokenResponse;
	private String accessToken;
	private OAuthProvider provider;
	
	public AuthCodeIdentity(String accessToken, IIdentityFetcher tokenResponseFetcher, OAuthProvider provider) throws HttpException {
		this.tokenResponse = tokenResponseFetcher.getPropertiesByAccessToken(accessToken, provider);
		this.accessToken = accessToken;
		this.provider = provider;
	}
	
	@Override
	public boolean isValid() {
		return tokenResponse!=null && getEmailAddress()!=null;
	}

	@Override
	public String getName() {
		return (String) tokenResponse.get("name");
	}

	@Override
	public String getEmailAddress() {
		return (String) tokenResponse.get("email");
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public OAuthProvider getProvider() {
		return provider;
	}
}
