package com.vikram.openconnect.login;

import java.util.HashMap;
import java.util.Map;

import com.vikram.openconnect.login.google.GoogleOpenconnectDiscovery;
import com.vikram.openconnect.login.providers.OAuthProvider;

public class OpenconnectDiscoveryFactory implements IOpenconnectDiscoveryFactory {
	
	private Map<OAuthProvider, IOpenconnectDiscovery> instanceMap = new HashMap<OAuthProvider, IOpenconnectDiscovery>();
	
	// Instantiate in parallel if we encounter more providers
	public OpenconnectDiscoveryFactory(){
		instanceMap.put(OAuthProvider.GOOGLE, new GoogleOpenconnectDiscovery());
	}
	
	
	public IOpenconnectDiscovery get(OAuthProvider provider){
		return instanceMap.get(provider);		
	}
}
