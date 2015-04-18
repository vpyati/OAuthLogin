package com.vikram.openconnect.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikram.openconnect.login.input.ICredentialInput;
import com.vikram.openconnect.login.input.IOAuthCredentials;
import com.vikram.openconnect.login.providers.OAuthProvider;
import com.vikram.openconnect.login.util.AuthCodeResolver;
import com.vikram.openconnect.login.util.HttpClientUtil;

public class DefaultIdentityFetcher implements IIdentityFetcher {

	@Autowired
	private IOpenconnectDiscoveryFactory discoveryFactory;
	
	@Autowired
	private HttpClientUtil httpUtil;
	
	@Autowired
	private IOAuthCredentials oauthCredentials;
	
	
	@Override
	public JSONObject getProperties(String authCode) throws HttpException {

		OAuthProvider provider = AuthCodeResolver.resolveAuthCode(authCode);
		ICredentialInput credentials = oauthCredentials.getCredentialByProvider(provider);
		IOpenconnectDiscovery discovery = discoveryFactory.get(provider);

		GetTokenResponse tokenResponse = getTokenResponse(authCode, credentials, discovery);

		String userInfoEndpoint = discovery.getUserinfo_endpoint();

		String jsonResponse = httpUtil.getJSONResponse(userInfoEndpoint + "?access_token=" + tokenResponse.getAccess_token());
		return (JSONObject) JSONValue.parse(jsonResponse);
	}


	private GetTokenResponse getTokenResponse(String authCode, ICredentialInput credentials, IOpenconnectDiscovery discovery) {
		String tokenEndpoint = discovery.getToken_endpoint();
		
		Map<String, String> postParams = new HashMap<String, String>();
		postParams.put("code", authCode);
		postParams.put("client_id", credentials.getClientId());
		postParams.put("client_secret",credentials.getClientSecret());
		postParams.put("redirect_uri", credentials.getRedirectUri());
		postParams.put("grant_type", "authorization_code");
		postParams.put("access_type", "offline");
		
		String response = getResponse(tokenEndpoint, postParams);
		if(response == null){
			return null;
		}
		
		return getTokenResponse(response);
	}
	
	
	private GetTokenResponse getTokenResponse(String response)  {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(response,GetTokenResponse.class);
		} catch (IOException e) {
			return null;
		}
	}

	private String getResponse(String tokenEndpoint,Map<String, String> postParams) {
		try {
			return httpUtil.getJSONResponse(tokenEndpoint, postParams);
		} catch (HttpException e) {
			return null;
		}
	}

	
}
