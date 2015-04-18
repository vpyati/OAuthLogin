package com.vikram.openconnect.login;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;

public interface IIdentityFetcher {
	
	JSONObject getProperties(String authCode) throws HttpException;
}
