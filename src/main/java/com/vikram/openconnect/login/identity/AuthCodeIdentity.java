package com.vikram.openconnect.login.identity;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;

import com.vikram.openconnect.login.DefaultIdentityFetcher;
import com.vikram.openconnect.login.IIdentityFetcher;
import com.vikram.openconnect.login.exception.UnableToFetchIdentityException;

public class AuthCodeIdentity implements Identity {


	private IIdentityFetcher tokenResponseFetcher = new DefaultIdentityFetcher();
	
	private JSONObject tokenResponse;
	
	public AuthCodeIdentity(String authCode) throws HttpException {
		this.tokenResponse = tokenResponseFetcher.getProperties(authCode);
		if(tokenResponse == null){
			throw new UnableToFetchIdentityException("Unable to convert authcode to identity");
		}		
	}
	
	@Override
	public boolean isValid() {
		return tokenResponse!=null;
	}

	@Override
	public String getName() {
		return (String) tokenResponse.get("name");
	}

	@Override
	public String getEmailAddress() {
		return (String) tokenResponse.get("email");
	}
}
