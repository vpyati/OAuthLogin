package com.vikram.openconnect.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.vikram.openconnect.login.google.GoogleOpenconnectDiscovery;
import com.vikram.openconnect.login.providers.OAuthProvider;

public class OpenconnectDiscoveryFactory implements IOpenconnectDiscoveryFactory {
	
	@Autowired
	private GoogleOpenconnectDiscovery googleDiscovery;
	
	private Map<OAuthProvider, IOpenconnectDiscovery> instanceMap = new HashMap<OAuthProvider, IOpenconnectDiscovery>();
	
	public OpenconnectDiscoveryFactory(){
		instanceMap.put(OAuthProvider.GOOGLE, googleDiscovery);
	}
		
	public IOpenconnectDiscovery get(OAuthProvider provider){
		return googleDiscovery;		
	}
}
