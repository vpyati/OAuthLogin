package com.vikram.openconnect.login;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;

import com.vikram.openconnect.login.providers.OAuthProvider;

public interface IIdentityFetcher {
	
	TokenResponse getTokenResponse(String authCode);
	
	JSONObject getPropertiesByAccessToken(String accessToken, OAuthProvider provider) throws HttpException;
}
